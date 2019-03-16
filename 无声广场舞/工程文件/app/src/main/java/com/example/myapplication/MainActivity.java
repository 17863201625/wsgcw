package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity extends Activity {

    ImageView IM1;
     ImageView IM2;
     ImageView IM3;
    ImageView IM4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IM1 = (ImageView) findViewById(R.id.tod);
        IM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent11 = new Intent(MainActivity.this, MusicPlayActivity.class);
                startActivity(intent11);
                //finish();
            }
        });
        IM2 = (ImageView) findViewById(R.id.BI3);
        IM2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent111 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent111);
                finish();
            }
        });
        IM3 = (ImageView) findViewById(R.id.BI1);
        IM3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent12= new Intent(MainActivity.this, scan.class);
                startActivity(intent12);
                finish();
            }
        });
        IM4 = (ImageView) findViewById(R.id.BI2);
        IM4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent33= new Intent(MainActivity.this, more.class);
                startActivity(intent33);
                finish();
            }
        });
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            //透明状态栏

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

    }
}


