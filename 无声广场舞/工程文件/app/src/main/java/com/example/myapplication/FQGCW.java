package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.zxing.encoding.EncodingHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FQGCW extends Activity {

    private EditText qrStrEditText;
    private ImageView qrImgImageView;
    private TextView resultTextView;
    private Button geQuXuanZe;
    private ListView tv1;
    //public String Slectced_track;
    private EditText Year;
    private EditText Month;
    private EditText Day;
    private EditText Hour;
    private EditText Minute;
    private EditText Seconed;
    private String contentString;

    private static final String PATH=new String("/sdcard/WSGCW/");      //定义sd卡的 路径
    private List<String> selfMusicList=new ArrayList<String>(); //实例化歌曲列表表单
    private List<String> Slectced_track=new ArrayList<String>(); //实例化歌曲列表表单

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sc);

      init();

        MusicList();


        Button generateQRCodeButton = (Button) this.findViewById(R.id.btn_add_qrcode);
        generateQRCodeButton.setOnClickListener(new OnClickListener() {



            @Override
            public void onClick(View v) {
                try {

                    if(!qrStrEditText.getText().toString().equals("")){
                        contentString = qrStrEditText.getText().toString();
                    }else {
                        contentString = null;
                    }

                    contentString += "%^%" + Year.getText().toString()+"-"+Month.getText().toString()+"-"+Day.getText().toString()+" "+Hour.getText().toString()+":"+Minute.getText().toString()+":"+Seconed.getText().toString();

                    if(Slectced_track.size() > 0){
                        contentString += "&";
                        int i = 0;
                        while (i < Slectced_track.size()){
                            contentString += Slectced_track.get(i) + "&";
                            i++;
                        }
                    }else {
                        contentString = null;
                    }

                    System.out.println(contentString + "sdafasdfsadfa" + contentString);




                    if (contentString != null) {
                        //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
                        Bitmap qrCodeBitmap = EncodingHandler.createQRCode(contentString, 900);
                        qrImgImageView.setImageBitmap(qrCodeBitmap);
                    }else {
                        Toast.makeText(FQGCW.this, "你没有输入字符", Toast.LENGTH_SHORT).show();
                    }

                } catch (WriterException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });


        //歌曲列表
        geQuXuanZe.setOnClickListener(new OnClickListener() {



            public void onClick(View view) {
                final String[] toBeStored = selfMusicList.toArray(new String[selfMusicList.size()]);
                new AlertDialog.Builder(FQGCW.this)
                        .setTitle("歌曲目录")
                        .setItems(toBeStored, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Slectced_track.add(toBeStored[i]);


                                ArrayAdapter<String> fileList = new ArrayAdapter<String>
                                (FQGCW.this, android.R.layout.simple_list_item_1, Slectced_track);

                                  tv1.setAdapter(fileList);


                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                // TODO Auto-generated method stub

                                Toast.makeText(FQGCW.this, "已选定", Toast.LENGTH_LONG).show();
                            }
                        }).show();

            }
        });
    }

    //歌曲列表的方法
    private void MusicList() {
        // TODO Auto-generated method stub
        File home=new File(PATH);        //创建一个路径为PATH的文件夹
        if(home.listFiles(new MusicFilter()).length>0){        //如果文件夹下的歌曲数目大于0，则执行下面的方法
            for(File file:home.listFiles(new MusicFilter())){       //遍历home文件夹下面的歌曲
                selfMusicList.add(file.getName());        //把每一次遍历到的歌曲名字添加到myMusicList表中
            }
        }
    }
    public void init(){
        geQuXuanZe=(Button)findViewById(R.id.result1);
        tv1=(ListView)findViewById(R.id.result);

        Year=(EditText)findViewById(R.id.Year_string);
        Month=(EditText)findViewById(R.id.Month_string);
        Day=(EditText)findViewById(R.id.Day_string);
        Hour=(EditText)findViewById(R.id.Hour_string);
        Minute=(EditText)findViewById(R.id.Minute_string);
        Seconed=(EditText)findViewById(R.id.Seconed_string);
        qrStrEditText = (EditText) this.findViewById(R.id.et_qr_string);
        qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理扫描结果（在界面上显示）
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");

            resultTextView.setText(scanResult);
        }
    }
}
