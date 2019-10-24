package com.example.chatrealtime.RoomChat;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.chatrealtime.R;
import com.example.chatrealtime.RoomChat.Fragment.FragmentChat;
import com.example.chatrealtime.RoomChat.Fragment.FragmentFriend;
import com.example.chatrealtime.RoomChat.Fragment.FragmentProfile;
import com.example.chatrealtime.RoomChat.Fragment.FragmentRoomChat;
import com.example.chatrealtime.RoomChat.Interface.ILogOutAcc;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements ILogOutAcc {
    private static final String TAG = "HomeActivity";
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getFragment(FragmentRoomChat.newInstance());

        navigationView= findViewById(R.id.bottomNavigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.itRoomChat:{
                        getFragment(FragmentRoomChat.newInstance());
                        break;
                    }
                    case R.id.itProfile:{
                        getFragment(FragmentProfile.newInstance());
                        Toast.makeText(getBaseContext(), "Profile", Toast.LENGTH_LONG).show();
                        break;
                    }
                    case R.id.itChat:{
                        getFragment(FragmentChat.newInstance());
                        break;
                    }
                    case R.id.itFriend:{
                        getFragment(FragmentFriend.newInstance());
                        break;
                    }
                }
                return false;
            }
        });

    }
    private void getFragment(Fragment fragment){
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.containHomeAct, fragment).commit();
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "getFragment: "+e.getMessage());
        }
    }


    @Override
    public void logOut() {
        finish();
    }
}
