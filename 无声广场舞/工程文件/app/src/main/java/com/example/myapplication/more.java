package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 齐南 on 2016/9/4 0004.
 */
public class more extends Activity {
    ImageView IM1;
    TextView TM1;
    /*ImageView IM2;
    TextView TM2;*/
    ImageView IM12;
    ImageView IM13;
    ImageView IM14;
    TextView IntentA;
    ImageView IntentB;
    TextView zcwma;
    ImageView zcwmb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        zcwma=(TextView)findViewById(R.id.zcwma) ;
        zcwmb=(ImageView)findViewById(R.id.zcwmb) ;

        IM1=(ImageView)findViewById(R.id.go1);
        TM1=(TextView)findViewById(R.id.music_name1);
        IntentA=(TextView)findViewById(R.id.music);
        IntentB=(ImageView) findViewById(R.id.go);
        zcwma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentzcA=new Intent(more.this,ZCWM.class);
                startActivity(intentzcA);

            }
        });
        zcwmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentzcB=new Intent(more.this,ZCWM.class);
                startActivity(intentzcB);

            }
        });
        IntentA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentA=new Intent(more.this,GYWM.class);
                startActivity(intentA);

            }
        });
        IntentB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentB=new Intent(more.this,GYWM.class);
                startActivity(intentB);

            }
        });
        TM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(more.this,FQGCW.class);
                startActivity(intent);
            }
        });
        IM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(more.this,FQGCW.class);
                startActivity(intent);
            }
        });
        IM12 = (ImageView) findViewById(R.id.BI3);
        IM12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(more.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        IM13 = (ImageView) findViewById(R.id.BI1);
        IM13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2= new Intent(more.this, scan.class);
                startActivity(intent2);
                finish();
            }
        });

        IM14 = (ImageView) findViewById(R.id.BI2);
        IM14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent13= new Intent(more.this, more.class);
                startActivity(intent13);
                finish();
            }
        });


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            //透明状态栏

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

}
