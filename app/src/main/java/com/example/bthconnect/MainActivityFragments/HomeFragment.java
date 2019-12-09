package com.example.bthconnect.MainActivityFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bthconnect.MainActivity;
import com.example.bthconnect.MapsActivity;
import com.example.bthconnect.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference myRef;
    Button btn_menu;
    Button btn_students;
    Button btn_live_chat;
    Button btn_sponsor;


    final int NUM_NOTIFICATION_VIEWS = 10;
    TextView textViews[] = new TextView[NUM_NOTIFICATION_VIEWS];
    int textViewOffset = NUM_NOTIFICATION_VIEWS - 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        initializeTextViews(view);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(String.class);
                updateTextViews();
                textViews[0].setText(value + " has joined BTHConnect!");
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_menu = (Button)view.findViewById(R.id.xmlHomeMenu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });

        btn_live_chat = (Button)view.findViewById(R.id.xmlHomeLiveChat);
        btn_live_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setViewPager(2);
            }
        });

        btn_students = (Button)view.findViewById(R.id.xmlHomeStudents);
        btn_students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setViewPager(6);
            }
        });

        btn_sponsor = (Button)view.findViewById(R.id.xmlHomeSponsor);
        btn_sponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).initSponsorFragment();
                ((MainActivity)getActivity()).setViewPager(8);
            }
        });

        return view;
    }

    void updateTextViews(){
        for(int i = NUM_NOTIFICATION_VIEWS - 1; i > 0; --i){
            textViews[i].setText(textViews[i - 1].getText());
        }
    }

    void initializeTextViews(View view){
        TextView tv = (TextView)view.findViewById(R.id.xmlHomeNotification0);
        textViews[0] = tv;
        tv = (TextView)view.findViewById(R.id.xmlHomeNotification1);
        textViews[1] = tv;
        tv = (TextView)view.findViewById(R.id.xmlHomeNotification2);
        textViews[2] = tv;
        tv = (TextView)view.findViewById(R.id.xmlHomeNotification3);
        textViews[3] = tv;
        tv = (TextView)view.findViewById(R.id.xmlHomeNotification4);
        textViews[4] = tv;
        tv = (TextView)view.findViewById(R.id.xmlHomeNotification5);
        textViews[5] = tv;
        tv = (TextView)view.findViewById(R.id.xmlHomeNotification6);
        textViews[6] = tv;
        tv = (TextView)view.findViewById(R.id.xmlHomeNotification7);
        textViews[7] = tv;
        tv = (TextView)view.findViewById(R.id.xmlHomeNotification8);
        textViews[8] = tv;
        tv = (TextView)view.findViewById(R.id.xmlHomeNotification9);
        textViews[9] = tv;
    }
}
