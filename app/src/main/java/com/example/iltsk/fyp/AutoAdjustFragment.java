package com.example.iltsk.fyp;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import bluetoothlibrary.BluetoothLeService;

/**
 * Created by Iltsk on 9/12/2015.
 */
public class AutoAdjustFragment extends Fragment {
    TextView msg;
    Button btnStart;
    ImageView ivHead;
    ImageView ivLeftback;
    ImageView ivRightback;
    ImageView ivArmrestL;
    ImageView ivArmrestR;
    ImageView ivUpBack;
    ImageView ivBottomBack;
    ImageView ivChairstand;
    View fragView;

    BluetoothLeService mBluetoothLeService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragView = inflater.inflate(R.layout.activity_auto_adjust, container, false);
        msg = (TextView)fragView.findViewById(R.id.txtMsg);
        ivHead=(ImageView)fragView.findViewById(R.id.ivHead);
        ivUpBack=(ImageView)fragView.findViewById(R.id.ivUpback);
        ivBottomBack=(ImageView)fragView.findViewById(R.id.ivBottomBack);
        ivLeftback=(ImageView)fragView.findViewById(R.id.ivLeftback);
        ivRightback=(ImageView)fragView.findViewById(R.id.ivRightback);
        //ivArmrestL=(ImageView)fragView.findViewById(R.id.ivArmrestL);
        //ivArmrestR=(ImageView)fragView.findViewById(R.id.ivArmrestR);
        ivChairstand=(ImageView)fragView.findViewById(R.id.ivChairstand);
        btnStart = (Button)fragView.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                btnStart.setVisibility(View.GONE);
                ivHead.setVisibility(View.VISIBLE);
                ivUpBack.setVisibility(View.VISIBLE);
                ivBottomBack.setVisibility(View.VISIBLE);
                //ivArmrestL.setVisibility(View.VISIBLE);
                //ivArmrestR.setVisibility(View.VISIBLE);
                ivLeftback.setVisibility(View.VISIBLE);
                ivRightback.setVisibility(View.VISIBLE);
                ivChairstand.setVisibility(View.VISIBLE);
                msg.setVisibility(View.VISIBLE);
                autoAdjusting();
            }
        });
        mBluetoothLeService = (BluetoothLeService)getArguments().getSerializable("btService");
        return fragView;
    }
    public void autoAdjusting(){
        TranslateAnimation anim1 = new TranslateAnimation(0,0,30,-30);
        anim1.setDuration(10000);
        TranslateAnimation anim2 = new TranslateAnimation(0,0,-30,30);
        anim2.setDuration(10000);
        anim2.setStartOffset(10000);

        AnimationSet anim = new AnimationSet(true);
        anim.addAnimation(anim1);
        anim.addAnimation(anim2);

        ivHead.startAnimation(anim);

        anim1 = new TranslateAnimation(30,-30,0,0);
        anim1.setDuration(30000);
        anim1.setStartOffset(20000);
        anim2 = new TranslateAnimation(-30,30,0,0);
        anim2.setDuration(30000);
        anim2.setStartOffset(50000);

        anim = new AnimationSet(true);
        anim.addAnimation(anim1);
        anim.addAnimation(anim2);

        ivLeftback.startAnimation(anim);

        anim1 = new TranslateAnimation(-30,30,0,0);
        anim1.setDuration(30000);
        anim1.setStartOffset(20000);
        anim2 = new TranslateAnimation(30,-30,0,0);
        anim2.setDuration(30000);
        anim2.setStartOffset(50000);

        anim = new AnimationSet(true);
        anim.addAnimation(anim1);
        anim.addAnimation(anim2);

        ivRightback.startAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                btnStart.setVisibility(View.VISIBLE);
                ivHead.setVisibility(View.GONE);
                ivUpBack.setVisibility(View.GONE);
                ivBottomBack.setVisibility(View.GONE);
                //ivArmrestL.setVisibility(View.GONE);
                //ivArmrestR.setVisibility(View.GONE);
                ivLeftback.setVisibility(View.GONE);
                ivRightback.setVisibility(View.GONE);
                ivChairstand.setVisibility(View.GONE);
                msg.setVisibility(View.GONE);
            }
        });
    }
}