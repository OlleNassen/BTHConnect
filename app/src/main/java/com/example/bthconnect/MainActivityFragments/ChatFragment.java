package com.example.bthconnect.MainActivityFragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bthconnect.DatabaseConnectivity.DatabaseController;
import com.example.bthconnect.MainActivity;
import com.example.bthconnect.MapsActivity;
import com.example.bthconnect.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static com.firebase.ui.auth.AuthUI.TAG;

public class ChatFragment extends Fragment {
    FirebaseDatabase database;
    DatabaseReference myRef;
    List<TextView>textViews;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.chat_fragment, container, false);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("globalChat");
       // (MainActivity)getActivity()).localUser
        post("HappyFace");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Toast.makeText(getActivity(), "Value is: " + value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getActivity(), "Failed to read value." + error.toException(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    void post(String input)
    {
        myRef.setValue(input);
    }


}
