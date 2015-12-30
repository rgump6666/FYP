package com.example.iltsk.fyp;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Iltsk on 9/12/2015.
 */
public class AutoAdjustFragment extends Fragment {
    TextView msg;
    Button btnStart;
    View fragView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragView = inflater.inflate(R.layout.activity_auto_adjust, container, false);
        msg = (TextView)fragView.findViewById(R.id.txtMsg);
        btnStart = (Button)fragView.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                msg.setVisibility(View.VISIBLE);
            }
        });
        return fragView;
    }
}