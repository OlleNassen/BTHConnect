package com.example.bthconnect.MainActivityFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bthconnect.MainActivity;
import com.example.bthconnect.R;

public class PollVoteFragment extends Fragment {

    private String pollID;

    //These needs to be saved because of getContext()
    private String s_name;
    private String s_time;
    private String s_date;
    private String s_loc;

    private TextView name;
    private TextView time;
    private TextView date;
    private TextView loc;

    private Button btn_back;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity3_update, container, false);

        name = (TextView)view.findViewById(R.id.xmlActivity3Name);
        name.setText(s_name);
        time = (TextView)view.findViewById(R.id.xmlActivity3Time);
        time.setText(s_time);
        date = (TextView)view.findViewById(R.id.xmlActivity3Date);
        date.setText(s_date);
        loc = (TextView)view.findViewById(R.id.xmlActivity3Location);
        loc.setText(s_loc);

        btn_back = (Button)view.findViewById(R.id.activity3_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setViewPager(4);

            }
        });
        return view;
    }

    public void initPollFragment(String pollID, String eventName, String eventTime, String eventDate, String eventLocation){
        this.pollID = pollID;
        s_name = eventName;
        s_time = eventTime;
        s_date = eventDate;
        s_loc = eventLocation;
    }
}