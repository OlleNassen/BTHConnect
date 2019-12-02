package com.example.bthconnect.DatabaseConnectivity;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseController {
    public FirebaseDatabase database;
    public DatabaseReference myRef;

    DatabaseController(String pathReference){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(pathReference);
    }

}
