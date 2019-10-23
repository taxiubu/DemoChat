package com.example.chatrealtime.Login_Register.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chatrealtime.Login_Register.Interface.IGetFragment;
import com.example.chatrealtime.R;
import com.example.chatrealtime.RoomChat.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class FragmentLogin extends Fragment {
    FirebaseAuth mAuth;
    EditText etEmail, etPassword;
    Button btLogin, btRegister;
    IGetFragment listen;
    TextView showOrHide;
    int checkEditText=0;
    Intent login;
    FirebaseAuth.AuthStateListener mAuthListener;
    public static FragmentLogin newInstance() {

        Bundle args = new Bundle();

        FragmentLogin fragment = new FragmentLogin();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        login= new Intent(getContext(), HomeActivity.class);
        mAuth= FirebaseAuth.getInstance();
        etEmail= view.findViewById(R.id.etEmail);
        etPassword= view.findViewById(R.id.etPassword);
        btLogin= view.findViewById(R.id.btLogin);
        btRegister= view.findViewById(R.id.btRegister);
        showOrHide= view.findViewById(R.id.showOrHide);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etEmail.getText().toString().equals("")&& etPassword.getText().toString().equals(""))
                    Toast.makeText(getContext(), "Tài khoản không được để trống", Toast.LENGTH_SHORT).show();
                else
                    logIn();
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listen.call(1);
            }
        });

        showOrHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkEditText== 0) {
                    //show
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    showOrHide.setText(R.string.show);
                    checkEditText = 1;
                }
                else {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showOrHide.setText(R.string.hide);
                    checkEditText= 0;
                }

            }
        });

        getCurrentUser();
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof IGetFragment){
            listen= (IGetFragment) context;
        }
        else {
            throw new RuntimeException(context.toString()+ "must implement");
        }
    }
    private void logIn() {
        final ProgressDialog dialog= new ProgressDialog(getContext());
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        dialog.setTitle("Loading...");
        dialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) { if(task.isSuccessful()){
                            dialog.dismiss();
                            startActivity(login);
                        }
                        else {
                            Toast.makeText(getContext(), "Tài khoản không đúng", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                 }
        });
    }

    private void getCurrentUser(){
        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if(user!=null){
                    startActivity(login);
                }
                else
                    Log.d(TAG, "onAuthStateChanged:signed_out");
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mAuthListener!=null)
            mAuth.removeAuthStateListener(mAuthListener);
    }
}



