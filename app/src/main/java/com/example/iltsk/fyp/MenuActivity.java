package com.example.iltsk.fyp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Iltsk on 20/11/2015.
 */
public class MenuActivity extends FragmentActivity implements View.OnClickListener {

    Button btnChairControl;
    Button btnAutoAdjust;
    Button btnReviewRecord;
    Button btnViewCurrentSittingPosition;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setView();
        setFragment(new ChairControlFragment());
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnChairControl:
            {
                setFragment(new ChairControlFragment());
                break;
            }
            case R.id.btnAutoAdjust:
            {
                setFragment(new AutoAdjustFragment());
                break;
            }
            case R.id.btnReviewRecord:
            {
                setFragment(new ReviewRecordFragment());
                break;
            }
            case R.id.btnViewCurrentSittingPosition:
            {
                setFragment(new ViewCurrentSittingPositionFragment());
                break;
            }
        }
    }

    private void setFragment(Fragment fragment){
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.function, fragment);
        fragmentTransaction.commit();
    }

    private void setView(){
        btnChairControl=(Button)findViewById(R.id.btnChairControl);
        btnAutoAdjust=(Button)findViewById(R.id.btnAutoAdjust);
        btnReviewRecord=(Button)findViewById(R.id.btnReviewRecord);
        btnViewCurrentSittingPosition=(Button)findViewById(R.id.btnViewCurrentSittingPosition);
        btnChairControl.setOnClickListener(this);
        btnAutoAdjust.setOnClickListener(this);
        btnReviewRecord.setOnClickListener(this);
        btnViewCurrentSittingPosition.setOnClickListener(this);
    }
}
