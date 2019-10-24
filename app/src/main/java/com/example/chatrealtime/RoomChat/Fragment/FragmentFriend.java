package com.example.chatrealtime.RoomChat.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatrealtime.Model.UserProfile;
import com.example.chatrealtime.R;
import com.example.chatrealtime.RoomChat.Adapter.AShowMFriend;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentFriend extends Fragment {
    RecyclerView rcvFriend;
    List<UserProfile> userList;
    AShowMFriend adapter;
    public static FragmentFriend newInstance() {

        Bundle args = new Bundle();

        FragmentFriend fragment = new FragmentFriend();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_friend, container, false);
        rcvFriend= view.findViewById(R.id.rcvFriend);
        rcvFriend.setHasFixedSize(true);
        rcvFriend.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        userList= new ArrayList<>();
        getAllUser();
        return view;
    }

    private void getAllUser(){
        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("UserProfile");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    UserProfile userProfile= snapshot.getValue(UserProfile.class);
                    if(!userProfile.getUserID().equals(firebaseUser.getUid())){
                        userList.add(userProfile);
                    }
                }
                adapter= new AShowMFriend(userList, getContext());
                rcvFriend.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
