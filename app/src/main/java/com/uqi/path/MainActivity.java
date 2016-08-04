package com.uqi.path;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ChinaMapView lView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lView = (ChinaMapView)findViewById(R.id.vp);
        lView.setOnProvinceSelectedListener(new ChinaMapView.OnProvinceSelectedListener() {
            @Override
            public void onprovinceSelected(ChinaMapView.Area pArea) {
                Toast.makeText(MainActivity.this,"您选择了-->"+pArea.name(),Toast.LENGTH_SHORT).show();
            }
        });


        lView.setMapColor(Color.BLUE);
        findViewById(R.id.fangda).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.zoomIn();
            }
        });
        findViewById(R.id.suoxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.zoomOut();
            }
        });
        findViewById(R.id.xiangshang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.up();
            }
        });
        findViewById(R.id.xiangxia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.down();
            }
        });
        findViewById(R.id.xiangzuo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.left();
            }
        });
        findViewById(R.id.xiangyou).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
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
        findViewById(R.id.sichuan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.setPaintColor(ChinaMapView.Area.SiChuan, Color.rgb(0x5c,0xad,0xad),true);
            }
        });
        findViewById(R.id.neimeng).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.setPaintColor(ChinaMapView.Area.NeiMengGu, Color.rgb(0x8f,0x45,0x86),true);
            }
        });
        findViewById(R.id.xinjiang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                lView.selectAProvince(ChinaMapView.Area.XinJiang);
            }
        });
    }
}
