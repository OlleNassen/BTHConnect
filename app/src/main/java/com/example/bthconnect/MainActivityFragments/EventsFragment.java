package com.example.bthconnect.MainActivityFragments;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bthconnect.MainActivity;
import com.example.bthconnect.R;

public class EventsFragment extends Fragment{

    private Button backBtn;
    Button createEventBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity1_update, container, false);

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

