package com.example.chatrealtime;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.chatrealtime.Fragment.FragmentLogin;
import com.example.chatrealtime.Fragment.FragmentRegister;
import com.example.chatrealtime.Interface.IGetFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements IGetFragment {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(FirebaseAuth.getInstance().getCurrentUser()==null){

        }
        getFragment(FragmentLogin.newInstance());
    }

    public void getFragment(Fragment fragment){
        try {
            getSupportFragmentManager().beginTransaction().replace(R.id.contain, fragment)
                    .addToBackStack(null).commit();
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "getFragment: "+e.getMessage());
        }


    }

    @Override
    public void call() {
        getFragment(FragmentRegister.newInstance());
    }
}
