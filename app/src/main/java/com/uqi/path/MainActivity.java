package com.uqi.path;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    Matrix m;
    ChinaMapView lView;
    float i=1;
    float x = 0;
    float y = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lView = (ChinaMapView)findViewById(R.id.vp);
        Matrix newMatrix  = lView.getMatrix();
        m = new Matrix();
        m.set(newMatrix);

//        lView.setPaintColor(MyView.Area.ShanXi, Color.RED,true);
        findViewById(R.id.fangda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
//                m.setScale(100,100);
               i+=0.1;
//                lView.setScale(i,i);
                lView.zoomIn();
            }
        });
        findViewById(R.id.suoxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                i-=0.1;
                lView.zoomOut();
            }
        });
        findViewById(R.id.xiangshang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                y=y-10;
                lView.up();
            }
        });
        findViewById(R.id.xiangxia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                y=y+10;
                lView.down();
            }
        });
        findViewById(R.id.xiangzuo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                x=x+10;
                lView.left();
            }
        });
        findViewById(R.id.xiangyou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                x=x-10;
                lView.right();
            }
        });
        findViewById(R.id.fuwei).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.restPosition();
            }
        });
        findViewById(R.id.yaunshidaxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.restScale();
            }
        });
    }
}
