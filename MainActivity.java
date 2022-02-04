package com.example.rubikscube;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Locale;

//AppCompatActivity

public class MainActivity extends Activity implements Runnable, View.OnClickListener{

    private int count;
    private long startTime;

    private Button startButton;//startボタンのインスタンスを生成
    private Button scButton;//scrambleボタンのインスタンスを生成
    private Button button3D;//3Dボタンのインスタンスの生成

    private TextView timerText;

    private final Handler handler = new Handler(Looper.getMainLooper());
    private volatile boolean stopRun = false;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss,SS", Locale.JAPAN);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText = findViewById(R.id.textTimer);
        timerText.setText(dateFormat.format(0));

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(this);

        scButton = findViewById(R.id.scrambleButton);
        scButton.setOnClickListener(this);

        button3D = findViewById(R.id.drawing3D);
        button3D.setOnClickListener((View v) ->{
            startActivity(new Intent(this,SubActivity.class));
        });


//        scButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Scrambleのインスタンス生成
//                Scramble s = new Scramble();
//
//                //strにscrambleSignから持ってきた文字列を代入
//                String str = s.scrambleSign();
//                str = str.replace(",", " ");
//                str = str.replace("[", "");
//                str = str.replace("]", "");
//
//
//                TextView tv = findViewById(R.id.scrambleText);
//                //scrambleTextにセットする
//                tv.setText(str);
//            }
//        });

    }

    public void button3D(View view){
        Intent intent = new Intent(this, SubActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v == startButton){
            Thread thread;
            TextView stv = findViewById(R.id.startButton);
            count++;
            if(count == 2){//startボタンを2回押したとき
                stopRun = true;
                timerText.setText(dateFormat.format(0));
                count = 0;
                stv.setText("START!");

            }else{
                stopRun = false;
                thread = new Thread(this);//スタート処理
                thread.start();
                startTime = System.currentTimeMillis();

                stv.setText("STOP!");
            }
        }else if(v == scButton){
            //Scrambleのインスタンス生成
            Scramble s = new Scramble();

            //strにscrambleSignから持ってきた文字列を代入
            String str = s.scrambleSign();
            str = str.replace(",", " ");
            str = str.replace("[", "");
            str = str.replace("]", "");


            TextView tv = findViewById(R.id.scrambleText);
            //scrambleTextにセットする
            tv.setText(str);
        }
    }

    @Override
    public void run(){
        //10msec order
        int period = 10;

        while(!stopRun){
            //sleep: period msec
            try{
                Thread.sleep(period);
            }catch (InterruptedException e){
                e.printStackTrace();
                stopRun = true;
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    long endTime = System.currentTimeMillis();
                    //カウント時間　＝　経過時間　-　開始時間
                    long diffTime = (endTime - startTime);

                    timerText.setText(dateFormat.format(diffTime));
                }
            });

        }

    }

}