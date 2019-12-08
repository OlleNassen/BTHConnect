package com.example.bthconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.bthconnect.FragmentAdapter.FragmentAdapter;
import com.example.bthconnect.MainActivityFragments.ChatFragment;
import com.example.bthconnect.MainActivityFragments.CreateEventFragment;
import com.example.bthconnect.MainActivityFragments.EventsFragment;
import com.example.bthconnect.MainActivityFragments.HomeFragment;
import com.example.bthconnect.MainActivityFragments.IndividualChatFragment;
import com.example.bthconnect.MainActivityFragments.LoginFragment;
import com.example.bthconnect.MainActivityFragments.MenuFragment;
import com.example.bthconnect.MainActivityFragments.StudentListFragment;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentAdapter fragAdapter;
    private ViewPager viewPager;
    public FirebaseUser localUser; // public for convenience

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        fragAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager = (ViewPager)findViewById(R.id.container);
        initViewPager();
        setViewPager(0);

        /*
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(textTitle)
                .setContentText(textContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        */
    }

    private void initViewPager(){
        fragAdapter.addFragment(new LoginFragment()); // 0
        fragAdapter.addFragment(new MenuFragment()); // 1
        fragAdapter.addFragment(new ChatFragment()); // 2
        fragAdapter.addFragment(new HomeFragment()); // 3
        fragAdapter.addFragment(new EventsFragment()); // 4
        fragAdapter.addFragment(new CreateEventFragment()); // 5
        fragAdapter.addFragment(new StudentListFragment()); // 6
        fragAdapter.addFragment(new IndividualChatFragment()); // 7

        viewPager.setAdapter((fragAdapter));
    }
    public void setIndividualChat(String person){
        IndividualChatFragment ptr = (IndividualChatFragment)fragAdapter.getItem(7);
        ptr.initializeIndividualChat(person);
    }
    public void setViewPager(int fragment){
        viewPager.setCurrentItem(fragment);
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }
}
