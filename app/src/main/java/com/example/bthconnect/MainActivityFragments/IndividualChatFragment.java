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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bthconnect.MainActivity;
import com.example.bthconnect.MapsActivity;
import com.example.bthconnect.R;
import com.firebase.ui.auth.AuthUI;

public class IndividualChatFragment extends Fragment {

    TextView personTalkingTo;
    EditText chatInput;
    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.individual_chatting_fragment, container, false);

        linearLayout = (LinearLayout)view.findViewById(R.id.xmlIndividualLinearLayout);

        personTalkingTo = (TextView)view.findViewById(R.id.xmlIndividualChatPerson);
        chatInput = (EditText)view.findViewById(R.id.xmlIndividualChatInput);
        chatInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView temp = new TextView(getContext());
                temp.setText(chatInput.getText());
                chatInput.setText("");
                linearLayout.addView(temp);
                ((MainActivity)getActivity()).hideKeyboardFrom(getContext(), view);
            }
        });


        return view;
    }

    public void setTextField(String input){
        personTalkingTo.setText(input);
    }
}
