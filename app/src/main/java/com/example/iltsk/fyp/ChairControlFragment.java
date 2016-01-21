package com.example.iltsk.fyp;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import bluetoothlibrary.BluetoothLeService;

/**
 * Created by Iltsk on 25/11/2015.
 */
public class ChairControlFragment extends Fragment implements View.OnTouchListener{
    private final static String TAG = ChairControlFragment.class.getSimpleName();

    ImageView ivHead;
    ImageView ivLeftback;
    ImageView ivRightback;
    ImageView ivArmrestL;
    ImageView ivArmrestR;
    ImageView ivRotateL;
    ImageView ivRotateR;
    ImageView ivUpBack;
    ImageView ivBottomBack;
    ImageView ivUpArrow;
    ImageView ivBottomArrow;
    View fragView;

    BluetoothLeService mBluetoothLeService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragView = inflater.inflate(R.layout.activity_chair_control, container, false);
        ivHead=(ImageView)fragView.findViewById(R.id.ivHead);
        ivHead.setOnTouchListener(this);
        ivLeftback=(ImageView)fragView.findViewById(R.id.ivLeftback);
        ivLeftback.setOnTouchListener(this);
        ivRightback=(ImageView)fragView.findViewById(R.id.ivRightback);
        ivRightback.setOnTouchListener(this);
        ivRotateL=(ImageView)fragView.findViewById(R.id.ivRotateLeft);
        ivRotateL.setOnTouchListener(this);
        ivRotateR=(ImageView)fragView.findViewById(R.id.ivRotateRight);
        ivRotateR.setOnTouchListener(this);
        ivUpBack=(ImageView)fragView.findViewById(R.id.ivUpback);
        ivUpBack.setOnTouchListener(this);
        ivBottomBack=(ImageView)fragView.findViewById(R.id.ivBottomBack);
        ivBottomBack.setOnTouchListener(this);
        ivUpArrow=(ImageView)fragView.findViewById(R.id.ivUp);
        ivUpArrow.setOnTouchListener(this);
        ivBottomArrow=(ImageView)fragView.findViewById(R.id.ivDown);
        ivBottomArrow.setOnTouchListener(this);
        //ivArmrestL=(ImageView)fragView.findViewById(R.id.ivArmrestL);
        //ivArmrestL.setOnTouchListener(this);
        //ivArmrestR=(ImageView)fragView.findViewById(R.id.ivArmrestR);
        //ivArmrestR.setOnTouchListener(this);
        mBluetoothLeService = (BluetoothLeService)getArguments().getSerializable("btService");
        Log.i(TAG, mBluetoothLeService.readUntilNewLine());
        Boolean result = mBluetoothLeService.writeCharacteristic("hi, I am Fragment 1");
        Log.w(TAG, "write character request result=" + result);
        return fragView;
    }

    float x,y,alx,blx,arx,brx;
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
                    if(v==ivHead/*||v==ivArmrestL||v==ivArmrestR*/) {/*
                        if(v==ivArmrestL||v==ivArmrestR) {
                            ivArmrestL.setY(y);
                            ivArmrestR.setY(y);
                        }else{*/
                            if(y>200&&y<325) {
                                v.setY(y);
                                ivRotateL.setVisibility(View.GONE);
                                ivRotateR.setVisibility(View.GONE);
                                ivUpArrow.setVisibility(View.GONE);
                                ivBottomArrow.setVisibility(View.GONE);
                            }

                    }else if(v==ivLeftback){
                        ivRotateL.setVisibility(View.VISIBLE);
                        ivRotateR.setVisibility(View.VISIBLE);
                        ivUpArrow.setVisibility(View.GONE);
                        ivBottomArrow.setVisibility(View.GONE);
                        blx = ivLeftback.getX();
                        if(x>200&&x<293) {
                            ivLeftback.setX(x);
                        }
                        alx = ivLeftback.getX();
                        brx = ivRightback.getX();
                        ivRightback.setX((blx-alx)+brx);
                    }else if(v==ivRightback) {
                        ivRotateL.setVisibility(View.VISIBLE);
                        ivRotateR.setVisibility(View.VISIBLE);
                        ivUpArrow.setVisibility(View.GONE);
                        ivBottomArrow.setVisibility(View.GONE);
                        brx = ivRightback.getX();
                        if(x>627&&x<720) {
                            ivRightback.setX(x);
                        }
                        arx = ivRightback.getX();
                        blx = ivLeftback.getX();
                        ivLeftback.setX((brx-arx)+blx);
                    }else if(v==ivUpBack){
                        ivUpArrow.setVisibility(View.VISIBLE);
                        ivBottomArrow.setVisibility(View.VISIBLE);
                        ivRotateL.setVisibility(View.GONE);
                        ivRotateR.setVisibility(View.GONE);
                    }else if(v==ivBottomBack){
                        ivUpArrow.setVisibility(View.VISIBLE);
                        ivBottomArrow.setVisibility(View.VISIBLE);
                        ivRotateL.setVisibility(View.GONE);
                        ivRotateR.setVisibility(View.GONE);
                    }else if(v==ivBottomArrow){

                    }else if(v==ivUpArrow){

                    }else if(v==ivRotateL){

                    }else if(v==ivRotateR){

                    }
                    else{
                        ivUpArrow.setVisibility(View.GONE);
                        ivBottomArrow.setVisibility(View.GONE);
                        ivRotateL.setVisibility(View.GONE);
                        ivRotateR.setVisibility(View.GONE);
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
