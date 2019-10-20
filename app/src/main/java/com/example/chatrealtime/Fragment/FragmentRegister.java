package com.example.chatrealtime.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chatrealtime.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FragmentRegister extends Fragment {
    EditText etFullnameRegister, etEmailRegister, etPasswordRegister, etRePassword;
    RelativeLayout btNext;
    FirebaseAuth mAuth;
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
        etFullnameRegister= view.findViewById(R.id.etFullName);
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
                else
                    register();
            }
        });

        return view;
    }
    private boolean checkDataEditText(){
        boolean check= false;
        if(etFullnameRegister.getText().toString().equals("") || etEmailRegister.getText().toString().equals("")
            || etPasswordRegister.getText().toString().equals("") || etRePassword.getText().toString().equals("")){
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
                            Toast.makeText(getContext(), "Đăng kí thành công", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            getActivity().onBackPressed();
                        }
                        else
                            Toast.makeText(getContext(), "Đăng kí thất bại", Toast.LENGTH_LONG).show();
                    }
                });
    }


}
