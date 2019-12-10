package com.example.bthconnect.MainActivityFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bthconnect.MainActivity;
import com.example.bthconnect.R;

public class CreateEventFragment extends Fragment {
    private Button button2;
    Button backBtn;
    Button pollbtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity2_update, container, false);

        backBtn = (Button)view.findViewById(R.id.activity1_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setViewPager(4);

            }
        });
        pollbtn = (Button)view.findViewById(R.id.activity2_create_poll);
        pollbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setViewPager(9);
            }
        });

        //button3 = (Button) view.findViewById(R.id.button2);
        /*
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //New Activity
            }
        });

        */

        return view;
    }
}
