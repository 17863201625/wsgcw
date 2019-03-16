package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxing.activity.CaptureActivity;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by 齐南 on 2016/9/3 0003.
 */
public class scan extends Activity {

    public String scanResult;
    //public int zs;
    private TextView resultTextView;

//    ImageView IM11;
    ImageView IM12;
    ImageView IM13;
    ImageView IM14;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doubled);

        resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);
        ImageButton scanBarCodeButton = (ImageButton) this.findViewById(R.id.btn_scan_barcode);
        scanBarCodeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(scan.this,CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });


        IM12 = (ImageView) findViewById(R.id.BI3);
        IM12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(scan.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        IM13 = (ImageView) findViewById(R.id.BI1);
        IM13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2= new Intent(scan.this, scan.class);
                startActivity(intent2);
                finish();
            }
        });

        IM14 = (ImageView) findViewById(R.id.BI2);
        IM14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent13= new Intent(scan.this, more.class);
                startActivity(intent13);
                finish();
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            //透明状态栏

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理扫描结果（在界面上显示）
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            scanResult = bundle.getString("result");
//            zs=(int)Integer.parseInt(scanResult);
            resultTextView.setText("扫码成功！");
            try {
                save(scanResult);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //存储扫码信息
    public void save(String a) throws IOException {

        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("SaoMajieGuo", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(a);
        }catch (IOException e){
            e.printStackTrace();
            }finally {
            try{
                if (writer!=null)
                {
                    writer.close();
                }
            }catch(IOException e)
                {
                    e.printStackTrace();
                }

        }


    }



}
