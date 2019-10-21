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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class FragmentUpdateProfile extends Fragment {
    EditText etFullname;
    RelativeLayout btSuccessful;
    FirebaseUser user;
    IGetFragment listen3;
    public static FragmentUpdateProfile newInstance() {

        Bundle args = new Bundle();

        FragmentUpdateProfile fragment = new FragmentUpdateProfile();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_update_profile, container, false);
        etFullname= view.findViewById(R.id.etFullName);
        btSuccessful= view.findViewById(R.id.btSuccessful);
        btSuccessful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etFullname.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_LONG).show();
                }
                else {
                    updateProfile();
                }
            }
        });
        return view;
    }

    private void updateProfile(){
        final ProgressDialog mdialog= new ProgressDialog(getContext());
        mdialog.setTitle("Loading...");
        mdialog.show();
        String fullname= etFullname.getText().toString();
        user= FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest profileUpdate= new UserProfileChangeRequest.Builder()
                .setDisplayName(fullname)
                //.setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();
        user.updateProfile(profileUpdate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "Đăng kí thành công", Toast.LENGTH_LONG).show();
                            mdialog.dismiss();
                            listen3.call(3);
                        }
                        else {
                            Toast.makeText(getContext(), "Đăng kí thất bại", Toast.LENGTH_LONG).show();
                            mdialog.dismiss();
                        }
                    }
                });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof IGetFragment){
            listen3 = (IGetFragment) context;
        }
        else {
            throw new RuntimeException(context.toString()+ "must implement");
        }
    }
}
