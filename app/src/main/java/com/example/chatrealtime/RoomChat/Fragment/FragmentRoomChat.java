package com.example.chatrealtime.RoomChat.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatrealtime.Model.ChatMessage;
import com.example.chatrealtime.R;
import com.example.chatrealtime.RoomChat.Adapter.AShowMessage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FragmentRoomChat extends Fragment {
    FirebaseUser user;
    EditText etMessage;
    ImageView btSend;
    DatabaseReference mdata;
    RecyclerView rcvChatMessage;
    AShowMessage adapter;
    List<ChatMessage> messageList;
    public static FragmentRoomChat newInstance() {

        Bundle args = new Bundle();

        FragmentRoomChat fragment = new FragmentRoomChat();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_room_chat, container, false);
        user= FirebaseAuth.getInstance().getCurrentUser();
        mdata= FirebaseDatabase.getInstance().getReference();
        btSend= view.findViewById(R.id.btSend);
        etMessage= view.findViewById(R.id.etMessage);
        rcvChatMessage= view.findViewById(R.id.rcvMessage);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message= etMessage.getText().toString();
                String userName= user.getDisplayName();

                ChatMessage chatMessage= new ChatMessage(message, userName);
                mdata.child("Room Chat").push().setValue(chatMessage);
                etMessage.setText("");
            }
        });

        //hien thi message
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcvChatMessage.setLayoutManager(layoutManager);
        messageList= new ArrayList<>();
        adapter= new AShowMessage(messageList, getContext());
        rcvChatMessage.setAdapter(adapter);
        rcvChatMessage.setHasFixedSize(true);
        rcvChatMessage.setItemViewCacheSize(5);
        getMessage();
        return view;
    }
    private void getMessage(){
        mdata.child("Room Chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ChatMessage getChatMessage= dataSnapshot.getValue(ChatMessage.class);
                messageList.add(getChatMessage);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
