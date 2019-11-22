package com.example.bthconnect;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    private static final int MY_REQUEST_CODE = 15; // random number, needed for whatever reason
    List<AuthUI.IdpConfig> providers;
    Button btn_connect;
    Button btn_events;
    Button btn_signOut;
    FirebaseUser localUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        btn_connect = (Button)findViewById(R.id.xmlConnect);
        btn_connect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AuthUI.getInstance().signOut(MainActivity.this).addOnCompleteListener(new OnCompleteListener<Void>(){
                    @Override
                    public void onComplete(@NonNull Task<Void> task){
                        showSignInOptions();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

       //btn_signOut = (Button)findViewById(R.id.xmlSignout);
       //btn_signOut.setOnClickListener(new View.OnClickListener() {
       //    @Override
       //    public void onClick(View view) {
       //        AuthUI.getInstance().signOut(MainActivity.this);
       //        setContentView(R.layout.login_activity);
       //    }
       //});

       //tn_events = (Button)findViewById(R.id.events);
       //tn_events.setOnClickListener(new View.OnClickListener() {
       //   @Override
       //   public void onClick(View view){
       //       Intent intent = new Intent(getBaseContext(), MapsActivity.class);
       //       startActivity(intent);
       //   }
       //);

        providers = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());
                //new AuthUI.IdpConfig.FacebookBuilder().build());
    }

    private void showSignInOptions(){
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(), MY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MY_REQUEST_CODE){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK){
                localUser = FirebaseAuth.getInstance().getCurrentUser();
                Toast.makeText(this, "Welcome " + localUser.getDisplayName(), Toast.LENGTH_SHORT).show();
                setContentView(R.layout.activity_menu);
            }
            else
            {
                Toast.makeText(this, "" + response.getError().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
