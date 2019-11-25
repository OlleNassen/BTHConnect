package com.example.bthconnect.MainActivityFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bthconnect.MainActivity;
import com.example.bthconnect.MapsActivity;
import com.example.bthconnect.R;
import com.firebase.ui.auth.AuthUI;

public class MenuFragment extends Fragment {
    Button btn_events;
    Button btn_signOut;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.main_menu_fragment, container, false);

        btn_signOut = (Button)view.findViewById(R.id.xmlSignout);
        btn_signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance().signOut(getActivity());
                Toast.makeText(getActivity(), "You have signed out\n", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });

        btn_events = (Button)view.findViewById(R.id.xmlEvents);
        btn_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}