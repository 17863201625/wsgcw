package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import java.text.*;
import java.util.Date;


public class MusicPlayActivity extends Activity implements OnClickListener {

    private static ImageView play;   //定义播放按钮控件
    private static ImageView stop;  //定义停止按钮控件
    public static SeekBar seekbar;   //定义进度条控件

    private static final String PATH=new String("/sdcard/WSGCW/");      //定义sd卡的 路径

    public static int flag=0;          //定义标志常量
    public static int tag=0;            //定义标志常量
    public static int num=0;            //定义播放第几首歌
    public static int seekTime = 0;    //定义从什么位置开始播放
    public static String setTime;      //设定好的播放时间
    public static long maxTime;       //歌单整体的时长
    public static String text;
    private TextView GCWM;
    private MediaPlayer myMediaPlayer;
    private List<String> myMusicList=new ArrayList<String>(); //实例化歌曲列表表单
    private List<Long> myMusicTimeList=new ArrayList<Long>(); //实例化歌曲时长表单


    public  String saoMajieGuo;
public TextView tess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);


        //System.out.println("sdafasdfsadfa" + load());
        saoMajieGuo =load();
        //System.out.println("sdafasdfsadfa" + saoMajieGuo);

        setContentView(R.layout.musicplaylayout);   //设置Music控件的视图为music
        tess=(TextView)findViewById(R.id.resultture) ;
        GCWM=(TextView)findViewById(R.id.duilieming);
        myMediaPlayer=new MediaPlayer();      //实例化播放功能
        //MusicList();                   //调用歌曲列表的方法
        saoMajieGuoCount();
        FindImage();                   //调用控件的实例化方法
        //SeekBarChange();               //调用进度条改变的的方法
        GCWM.setText("你已加入" + text + "广场舞队列！");
    }

    //读取扫码信息
    public String load(){
        FileInputStream in=null;
        BufferedReader reader=null;
        StringBuilder content =new StringBuilder();
        try {
            in =openFileInput("SaoMajieGuo");
            reader =new BufferedReader(new InputStreamReader(in));
            String line="";
            while ((line=reader.readLine())!=null)
            {
                content.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }



    Handler handler=new Handler();         //定义一个handler，主要是用于更新进度条
    Runnable runnable=new Runnable(){
        public void run(){                //重写run方法
            seekbar.setProgress(myMediaPlayer.getCurrentPosition());   //获取音乐播放的时间来设定进度条的位置
            handler.postDelayed(runnable, 100);       //每隔1秒钟更新一次
        }

    };

//    控件的实例化方法
    private void FindImage() {
        // TODO Auto-generated method stub


        play=(ImageView)findViewById(R.id.play); //实例化播放的按钮控件
        play.setOnClickListener(this);         //设置播放按钮的监听器

        stop=(ImageView)findViewById(R.id.stop); //实例化停止的按钮控件
        stop.setOnClickListener(this);           //设置停止按钮的监听器


        seekbar=(SeekBar)findViewById(R.id.sb); //实例化进度条的按钮控件

    }



    //按键接收
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId()){     //获得点击按钮的ID值

            case R.id.play:       //如果ID为play，则调用PlayMusic来播放上一首歌
                PlayMusic(PATH+myMusicList.get(num));
                break;

            case R.id.stop:         //如果ID为stop，则调用StopMusic来播放上一首歌
                StopMusic();
                break;


        }
    }

    private void NextMusic() {           //下一首歌的方法实现
        // TODO Auto-generated method stub
        flag=0;
        tag=0;
        ++num;
        if(num>=myMusicList.size()){ //如果num的值大于等于列表歌曲的总数，那么就播放第一首歌，
            //也就是说当歌曲播放到最后一首后，接下来就是播放第一首歌
            num=0;
            PlayMusic(PATH+myMusicList.get(num));      //播放第num首歌
        }
        else{
            PlayMusic(PATH+myMusicList.get(num));         //播放第num首歌
        }

    }

    //暂停播放
    private void StopMusic() {           //音乐暂停的方法实现
        // TODO Auto-generated method stub
        if(myMediaPlayer.isPlaying()){   // 如果歌曲正在播放，那么就暂停
            //myMediaPlayer.reset();
            myMediaPlayer.stop();    //音乐暂停
            flag=1;
            tag=0;
        }
    }

    //开始播放
    private void PlayMusic(String path) {
        // TODO Auto-generated method stub
        if(timeCount() == 1) {
            if (flag == 0 && tag == 0) {    //当第一次点击时，从头开始
                try {
                    //System.out.println("sdafasdfsadfa" + "1");
                    myMediaPlayer.reset();   //设置reset状态，处于Idle状态
                    //System.out.println("sdafasdfsadfa" + "2");
                    myMediaPlayer.setDataSource(path);//设置哪一首歌曲
                    //System.out.println("sdafasdfsadfa" + "3");
                    myMediaPlayer.prepare();  //歌曲准备
                    //System.out.println("sdafasdfsadfa" + "4");
                    myMediaPlayer.start();  //歌曲开始
                    //System.out.println("sdafasdfsadfa" + "5");
                    myMediaPlayer.seekTo(seekTime);
                    System.out.println("sdafasdfsadfa" + myMusicList.get(num));
                   this.tess.setText(myMusicList.get(num));


                    tag = 1;
                    seekbar.setMax(myMediaPlayer.getDuration()); //进度条获取当前音乐播放的位置
                    handler.post(runnable);    //条用进程，每一秒更新一次位置
                    myMediaPlayer.setOnCompletionListener(new OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            // TODO Auto-generated method stub
                            NextMusic();//当播放一首歌结束后自动下一首
                        }
                    });
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (flag != 0 && tag == 0) {
                try {
                    myMediaPlayer.prepare();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                myMediaPlayer.start();
                myMediaPlayer.seekTo(seekTime);
                handler.post(runnable);
                tag = 1;
                myMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        // TODO Auto-generated method stub
                        NextMusic();
                    }
                });
            }
        }
    }

    //上一曲







    //分割扫码结果  计算时间  添加歌曲列表
    public void saoMajieGuoCount() {
        int i, j;

        i = saoMajieGuo.indexOf("%^%");
         text = saoMajieGuo.substring(0, i);  //广场舞队列名


        //计算时间差（毫秒）
        i += 3;
        j = saoMajieGuo.indexOf("&", i);
        setTime = saoMajieGuo.substring(i, j);

        //生成歌曲列表
        int saoMajieGuoLength = saoMajieGuo.length();
        i = ++j;
        while (j != saoMajieGuoLength) {
            j = saoMajieGuo.indexOf("&", i);
            System.out.println(j + "sdafasdfsadfa  " + saoMajieGuo.substring(i, j));
            myMusicList.add(saoMajieGuo.substring(i, j));
            i = ++j;
        }

        //生成歌曲时长列表
        maxTime = 0;
        int timeSize = 0;
        long time;
        while (timeSize < myMusicList.size())
        {
            try{
                myMediaPlayer.reset();
                myMediaPlayer.setDataSource(PATH+myMusicList.get(timeSize));
                myMediaPlayer.prepare();
                time=myMediaPlayer.getDuration();
                maxTime += time;
                myMusicTimeList.add(time);
                System.out.println(PATH+myMusicList.get(timeSize) + "sdafasdfsadfa" + time);
                ++timeSize;
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }



    }

    //计算与系统时间的差值
    public long timeDifference(String setTime){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            Date d1 = df.parse(setTime);
            Date   d2 = new   Date(System.currentTimeMillis());//你也可以获取当前时间
            long diff = d2.getTime() - d1.getTime();//这样得到的差值是微秒级别
            /*long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);*/

            System.out.println(d2 + "sdafasdfsadfa" + diff);

            return diff;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    //计算第几首歌 第几毫秒
    public int timeCount() {
        long differenceTime = timeDifference(setTime);     //计算时间差（毫秒）

        if (differenceTime < 0){
            differenceTimeErro();
            return 0;
        }

        long startTime = differenceTime % maxTime;
        num = 0;
        while ((startTime - myMusicTimeList.get(num)) >= 0) {
            startTime -= myMusicTimeList.get(num);
            ++num;
            if (num >= myMusicList.size()){
                num = 0;
            }
        }
        seekTime = (int)startTime;
        System.out.println(num + "sdafasdfsadfa" + seekTime);
        return 1;
    }

    //
    public void differenceTimeErro(){
        Toast.makeText(MusicPlayActivity.this, "还没有到广场舞时间！", Toast.LENGTH_LONG).show();
    }
}

