package com.example.bthconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bthconnect.FragmentAdapter.FragmentAdapter;
import com.example.bthconnect.MainActivityFragments.ChatFragment;
import com.example.bthconnect.MainActivityFragments.HomeFragment;
import com.example.bthconnect.MainActivityFragments.LoginFragment;
import com.example.bthconnect.MainActivityFragments.MenuFragment;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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
    }

    private void initViewPager(){
        fragAdapter.addFragment(new LoginFragment());
        fragAdapter.addFragment(new MenuFragment());
        fragAdapter.addFragment(new ChatFragment());
        fragAdapter.addFragment(new HomeFragment());
        viewPager.setAdapter((fragAdapter));
    }

    public void setViewPager(int fragment){
        viewPager.setCurrentItem(fragment);
    }
}
