package com.example.chatrealtime.RoomChat.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chatrealtime.Login_Register.Interface.IGetFragment;
import com.example.chatrealtime.R;
import com.example.chatrealtime.RoomChat.Interface.ILogOutAcc;
import com.google.firebase.auth.FirebaseAuth;

public class FragmentProfile extends Fragment {
    Button btLogout;
    ILogOutAcc listenerLogOut;
    public static FragmentProfile newInstance() {

        Bundle args = new Bundle();

        FragmentProfile fragment = new FragmentProfile();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        btLogout= view.findViewById(R.id.btLogout);
        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                listenerLogOut.logOut();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ILogOutAcc){
            listenerLogOut = (ILogOutAcc) context;
        }
        else {
            throw new RuntimeException(context.toString()+ "must implement");
        }
    }
}
