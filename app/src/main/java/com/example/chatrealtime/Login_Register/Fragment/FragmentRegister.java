package com.example.chatrealtime.Login_Register.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chatrealtime.Login_Register.Interface.IGetFragment;
import com.example.chatrealtime.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FragmentRegister extends Fragment {
    EditText etEmailRegister, etPasswordRegister, etRePassword;
    RelativeLayout btNext;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    IGetFragment listen2;
    public static FragmentRegister newInstance() {

        Bundle args = new Bundle();

        FragmentRegister fragment = new FragmentRegister();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        etEmailRegister= view.findViewById(R.id.etEmailRegister);
        etPasswordRegister= view.findViewById(R.id.etPasswordRegister);
        etRePassword= view.findViewById(R.id.etRePassword);
        btNext= view.findViewById(R.id.btNextRegister);
        mAuth= FirebaseAuth.getInstance();

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkDataEditText()){
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_LONG).show();
                }
                else{
                    register();
                }

            }
        });
        //setUser();
        return view;
    }
    private boolean checkDataEditText(){
        boolean check= false;
        if(etEmailRegister.getText().toString().equals("") || etPasswordRegister.getText().toString().equals("")
                || etRePassword.getText().toString().equals("")){
            check=true;
        }
        return check;
    }
    private void register(){
        final ProgressDialog dialog= new ProgressDialog(getContext());
        dialog.setTitle("Loading...");
        dialog.show();
        String email= etEmailRegister.getText().toString();
        String password= etPasswordRegister.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            dialog.dismiss();
                            listen2.call(2);
                        }
                        else{
                            Toast.makeText(getContext(), "Đăng kí thất bại", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    }
                });

    }

    /*private void setUser(){
        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if(user!=null){
                    Toast.makeText(getContext(), user.getUid(), Toast.LENGTH_LONG).show();
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
    }*/

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof IGetFragment){
            listen2 = (IGetFragment) context;
        }
        else {
            throw new RuntimeException(context.toString()+ "must implement");
        }
    }
}
