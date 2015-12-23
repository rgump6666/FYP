package com.example.iltsk.fyp;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Iltsk on 25/11/2015.
 */
public class ChairControlFragment extends Fragment implements View.OnTouchListener{
    ImageView ivHead;
    ImageView ivUpback;
    ImageView ivBottomback;
    ImageView ivLeftback;
    ImageView ivRightback;
    ImageView ivArmrest;
    ImageView ivChairstand;
    private View fragView;
    ImageView[] iv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragView = inflater.inflate(R.layout.activity_chair_control, container, false);
        ivHead=(ImageView)fragView.findViewById(R.id.ivHead);
        ivHead.setOnTouchListener(this);
        ivUpback=(ImageView)fragView.findViewById(R.id.ivUpback);
        ivUpback.setOnTouchListener(this);
        ivBottomback=(ImageView)fragView.findViewById(R.id.ivBottomBack);
        ivBottomback.setOnTouchListener(this);
        ivLeftback=(ImageView)fragView.findViewById(R.id.ivLeftback);
        ivLeftback.setOnTouchListener(this);
        ivRightback=(ImageView)fragView.findViewById(R.id.ivRightback);
        ivRightback.setOnTouchListener(this);
        ivArmrest=(ImageView)fragView.findViewById(R.id.ivArmrest);
        ivArmrest.setOnTouchListener(this);
        ivChairstand=(ImageView)fragView.findViewById(R.id.ivChairstand);
        ivChairstand.setOnTouchListener(this);
        return fragView;
    }
    float x,y=0.0f;
    boolean moving=false;
    @Override
    public boolean onTouch(View v,MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                moving=true;
                break;
            case MotionEvent.ACTION_MOVE:
                if(moving){
                    x=event.getRawX()-v.getWidth()/2;
                    y=event.getRawY()-v.getHeight()*3/2;
                    v.setX(x);
                    v.setY(y);
                }
                break;
            case MotionEvent.ACTION_UP:
                moving=false;
                break;
        }
        return true;
    }
}
