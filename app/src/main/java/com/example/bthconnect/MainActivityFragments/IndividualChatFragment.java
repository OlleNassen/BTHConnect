package com.example.bthconnect.MainActivityFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class IndividualChatFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference myRef;

    ChildEventListener childEventListener;
    TextView personTalkingTo;
    Button btn_back;
    Button btn_send;
    EditText textInput;
    LinearLayout linearLayout;
    Button back_but_;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.individual_chatting_fragment, container, false);

        back_but_ = (Button)view.findViewById(R.id.individual_activity_back);
        back_but_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setViewPager(6);
            }
        });

        linearLayout = (LinearLayout)view.findViewById(R.id.xmlIndividualLinearLayout);

        personTalkingTo = (TextView)view.findViewById(R.id.xmlIndividualChatPerson);
        textInput = (EditText)view.findViewById(R.id.xmlIndividualChatInput);

        btn_send = (Button)view.findViewById(R.id.xmlIndividualSend);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post(textInput.getText().toString());
                textInput.setText("");
                ((MainActivity)getActivity()).hideKeyboardFrom(getContext(), view);
            }
        });

        btn_back = (Button)view.findViewById(R.id.xmlIndividualBack);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setViewPager(3);
            }
        });

        return view;
    }

    void post(String input){
        myRef.push().setValue(((MainActivity)getActivity()).localUser.getDisplayName() + ": " + input);
    }
    public void initializeIndividualChat(String input){
        personTalkingTo.setText(input);
        database = FirebaseDatabase.getInstance();
        int hash0 = input.hashCode();
        int hash1 = ((MainActivity)getActivity()).localUser.getDisplayName().hashCode();
        int combinedHash = hash0 + hash1;
        myRef = database.getReference("privateMessages/" + combinedHash);

        if(childEventListener != null)
        {
            myRef.removeEventListener(childEventListener);
        }

        childEventListener = myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(String.class);
                TextView temp = new TextView(getContext());
                temp.setText(value);
                linearLayout.addView(temp);
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
    }
}
