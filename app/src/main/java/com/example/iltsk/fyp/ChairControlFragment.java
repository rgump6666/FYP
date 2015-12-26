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
    ImageView ivLeftback;
    ImageView ivRightback;
    ImageView ivArmrestL;
    ImageView ivArmrestR;
    View fragView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragView = inflater.inflate(R.layout.activity_chair_control, container, false);
        ivHead=(ImageView)fragView.findViewById(R.id.ivHead);
        ivHead.setOnTouchListener(this);
        ivLeftback=(ImageView)fragView.findViewById(R.id.ivLeftback);
        ivLeftback.setOnTouchListener(this);
        ivRightback=(ImageView)fragView.findViewById(R.id.ivRightback);
        ivRightback.setOnTouchListener(this);
        ivArmrestL=(ImageView)fragView.findViewById(R.id.ivArmrestL);
        ivArmrestL.setOnTouchListener(this);
        ivArmrestR=(ImageView)fragView.findViewById(R.id.ivArmrestR);
        ivArmrestR.setOnTouchListener(this);
        return fragView;
    }
    float x,y,alx,blx,arx,brx=0.0f;
    boolean moving=false;
    @Override
    public boolean onTouch(View v,MotionEvent event){
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                moving=true;
                break;
            case MotionEvent.ACTION_MOVE:
                if(moving){
                    x = event.getRawX() - v.getWidth() / 2;
                    y = event.getRawY() - v.getHeight()*5/ 2;
                    if(v==ivHead||v==ivArmrestL||v==ivArmrestR) {
                        if(v==ivArmrestL||v==ivArmrestR) {
                            ivArmrestL.setY(y);
                            ivArmrestR.setY(y);
                        }else{
                            v.setY(y);
                        }
                    }else if(v==ivLeftback){
                        blx = ivLeftback.getX();
                        ivLeftback.setX(x);
                        alx = ivLeftback.getX();
                        brx = ivRightback.getX();
                        ivRightback.setX((blx-alx)+brx);
                    }else if(v==ivRightback) {
                        brx = ivRightback.getX();
                        ivRightback.setX(x);
                        arx = ivRightback.getX();
                        blx = ivLeftback.getX();
                        ivLeftback.setX((brx-arx)+blx);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                moving=false;
                break;
        }
        return true;
    }
}
