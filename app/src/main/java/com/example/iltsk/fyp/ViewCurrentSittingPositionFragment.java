package com.example.iltsk.fyp;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import bluetoothlibrary.BluetoothLeService;

/**
 * Created by Iltsk on 9/12/2015.
 */
public class ViewCurrentSittingPositionFragment extends Fragment {

    BluetoothLeService mBluetoothLeService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_current_sitting_position, container, false);
        RelativeLayout relativeLayout = (RelativeLayout) view;
        relativeLayout.addView(new DrawShape(getActivity()));
        mBluetoothLeService = (BluetoothLeService)getArguments().getSerializable("btService");
        return view;
    }

    public int[] genIntArray(){
        int[] result = {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100};
        return result;
    }

    private class DrawShape extends View{
        Paint paint = new Paint();

        public DrawShape(Context context){
            super(context);
        }

        @Override
        public void onDraw(Canvas canvas){
            int[] dist = genIntArray();

            int colorArray[];
            float[] stopsArray = new float[4];
            stopsArray[0] = 0;
            stopsArray[1] = 0.5f;
            stopsArray[2] = 0.95f;
            stopsArray[3] = 1;

            int x = getHeight(),y=getWidth();
            float sx = x/3, sy = y/4;
            paint.setDither(true);
            colorArray = genColorArray(dist[6]);
            paint.setShader(new RadialGradient(sx, sy, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx, sy, 50, paint);//head left
            paint.setShader(new RadialGradient(sx + 120, sy, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx + 120, sy, 50, paint);//head right

            sy=y/5*2;

            colorArray = genColorArray(dist[0]);
            paint.setShader(new RadialGradient(sx-230,sy, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx - 230, sy, 50, paint);//leftWing up

            colorArray = genColorArray(dist[7]);
            paint.setShader(new RadialGradient(sx - 80, sy, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx - 80, sy, 50, paint);//up back left

            colorArray = genColorArray(dist[8]);
            paint.setShader(new RadialGradient(sx + 60, sy, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx + 60, sy, 50, paint);//up back middle

            colorArray = genColorArray(dist[9]);
            paint.setShader(new RadialGradient(sx + 210, sy, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx + 210, sy, 50, paint);//up back right

            colorArray = genColorArray(dist[12]);
            paint.setShader(new RadialGradient(sx + 360, sy, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx + 360, sy, 50, paint);//rightWing up

            sy = y/5*3;

            colorArray = genColorArray(dist[4]);
            paint.setShader(new RadialGradient(sx -80,sy, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx - 80, sy, 50, paint);//middle back left

            colorArray = genColorArray(dist[10]);
            paint.setShader(new RadialGradient(sx + 60, sy, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx + 60, sy, 50, paint);//middle back middle

            colorArray = genColorArray(dist[16]);
            paint.setShader(new RadialGradient(sx + 210, sy, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx + 210, sy, 50, paint);//middle back right

            colorArray = genColorArray(dist[13]);
            paint.setShader(new RadialGradient(sx + 400, sy - 70, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx + 400, sy - 70, 50, paint);//rightWing middle up

            colorArray = genColorArray(dist[14]);
            paint.setShader(new RadialGradient(sx + 400, sy + 70, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx + 400, sy + 70, 50, paint);//rightWing middle down

            colorArray = genColorArray(dist[1]);
            paint.setShader(new RadialGradient(sx - 270, sy - 70, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx - 270, sy - 70, 50, paint);//leftWing middle up

            colorArray = genColorArray(dist[2]);
            paint.setShader(new RadialGradient(sx - 270, sy + 70, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx - 270, sy + 70, 50, paint);//leftWing middle down

            sy = y/5*4;
            colorArray = genColorArray(dist[3]);
            paint.setShader(new RadialGradient(sx - 230,sy, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx - 230, sy, 50, paint);//leftWing

            colorArray = genColorArray(dist[5]);
            paint.setShader(new RadialGradient(sx - 80, sy, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx - 80, sy, 50, paint);//bottom back left

            colorArray = genColorArray(dist[11]);
            paint.setShader(new RadialGradient(sx + 60, sy, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx + 60, sy, 50, paint);//bottom back middle

            colorArray = genColorArray(dist[17]);
            paint.setShader(new RadialGradient(sx + 210, sy, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx + 210, sy, 50, paint);//bottom back right

            colorArray = genColorArray(dist[15]);
            paint.setShader(new RadialGradient(sx + 360, sy, 50, colorArray, stopsArray, Shader.TileMode.MIRROR));
            canvas.drawCircle(sx + 360, sy, 50, paint);


        }
    }

    public int[] genColorArray(int num) {
        int[] colorArray = new int[4];
        switch (num) {
            case 10:
                colorArray[0] = 0xff00ff00;
                colorArray[1] = 0xff00ee00;
                colorArray[2] = 0xee00dd00;
                colorArray[3] = 0xcc00cc00;
                break;
            case 9:
                colorArray[0] = 0xee00ee00;
                colorArray[1] = 0xee00dd00;
                colorArray[2] = 0xdd00cc00;
                colorArray[3] = 0xcc00bb00;
                break;
            case 8:
                colorArray[0] = 0xdd00bb00;
                colorArray[1] = 0xcc00cc00;
                colorArray[2] = 0xbb00bb00;
                colorArray[3] = 0xaa00aa00;
                break;
            case 7:
                colorArray[0] = 0xddccff33;
                colorArray[1] = 0xccccff33;
                colorArray[2] = 0xbbccff33;
                colorArray[3] = 0xaaccff33;
                break;
            case 6:
                colorArray[0] = 0xeeffff00;
                colorArray[1] = 0xddffcc00;
                colorArray[2] = 0xccffbb00;
                colorArray[3] = 0xbbffaa00;
                break;
            case 5:
                colorArray[0] = 0xddeecc00;
                colorArray[1] = 0xddeebb00;
                colorArray[2] = 0xcceeaa00;
                colorArray[3] = 0xbbee9900;
                break;
            case 4:
                colorArray[0] = 0xeeff7000;
                colorArray[1] = 0xeeee6000;
                colorArray[2] = 0xdddd5000;
                colorArray[3] = 0xddcc4000;
                break;
            case 3:
                colorArray[0] = 0xffff0000;
                colorArray[1] = 0xffee0000;
                colorArray[2] = 0xeedd0000;
                colorArray[3] = 0xddcc0000;
                break;
        }
        return colorArray;
    }
}