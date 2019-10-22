package com.example.chatrealtime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.chatrealtime.Login_Register.Fragment.FragmentLogin;
import com.example.chatrealtime.Login_Register.Fragment.FragmentRegister;
import com.example.chatrealtime.Login_Register.Fragment.FragmentUpdateProfile;
import com.example.chatrealtime.Login_Register.Interface.IGetFragment;

public class MainActivity extends AppCompatActivity implements IGetFragment {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    public void call(int id) {
        if(id==1)
            getFragment(FragmentRegister.newInstance());
        else if(id==2)
            getFragment(FragmentUpdateProfile.newInstance());
        else if(id==3)
            reLoadAct();
    }

    private void reLoadAct(){
        Intent reLoad= new Intent(MainActivity.this, MainActivity.class);
        finish();
        startActivity(reLoad);
    }
}
