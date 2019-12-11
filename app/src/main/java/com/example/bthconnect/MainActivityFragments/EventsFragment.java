package com.example.bthconnect.MainActivityFragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bthconnect.MainActivity;
import com.example.bthconnect.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventsFragment extends Fragment{
    FirebaseDatabase database;
    DatabaseReference myRef;

    ChildEventListener childEventListener;
    private Button backBtn;
    Button createEventBtn;
    LinearLayout linearLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity1_update, container, false);
        final Context context = getContext();
        if(context == null)return view;


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("events");

        if(childEventListener != null)
        {
            myRef.removeEventListener(childEventListener);
        }

        childEventListener = myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final String id = dataSnapshot.getKey();
                final String eventName = dataSnapshot.child("event_name").getValue(String.class);
                final String eventTime = dataSnapshot.child("event_time").getValue(String.class);
                final String eventDate = dataSnapshot.child("event_date").getValue(String.class);
                final String eventLocation = dataSnapshot.child("event_location").getValue(String.class);

                Button eventBtn = new Button(context);
                eventBtn.setText(eventName);
                eventBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((MainActivity)getActivity()).initPollVoteFragment(id, eventName, eventTime, eventDate, eventLocation);
                        ((MainActivity)getActivity()).setViewPager(9);
                    }
                });
                linearLayout.addView(eventBtn);
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });

        linearLayout = (LinearLayout) view.findViewById(R.id.xmlActivity1Linear);

        backBtn = (Button) view.findViewById(R.id.activity1_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setViewPager(3);
            }
        });

        createEventBtn = (Button) view.findViewById(R.id.activity1_create_event);
        createEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setViewPager(5);
            }
        });

        return view;
    }
}

