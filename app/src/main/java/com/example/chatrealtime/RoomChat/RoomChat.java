package com.example.chatrealtime.RoomChat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

public class RoomChat extends AppCompatActivity {
    Button btLogout;
    FirebaseUser user;
    EditText etMessage;
    ImageView btSend;
    DatabaseReference mdata;
    RecyclerView rcvChatMessage;
    AShowMessage adapter;
    List<ChatMessage> messageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_chat);
        user= FirebaseAuth.getInstance().getCurrentUser();
        mdata= FirebaseDatabase.getInstance().getReference();
        btLogout= findViewById(R.id.btLogout);
        btSend= findViewById(R.id.btSend);
        etMessage= findViewById(R.id.etMessage);
        rcvChatMessage = findViewById(R.id.rcvMessage);
        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });
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
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false);
        rcvChatMessage.setLayoutManager(layoutManager);
        messageList= new ArrayList<>();
        adapter= new AShowMessage(messageList, getBaseContext());
        rcvChatMessage.setAdapter(adapter);
        getMessage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_out, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_sign_out:
                FirebaseAuth.getInstance().signOut();
                finish();
        }
        return super.onOptionsItemSelected(item);
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
