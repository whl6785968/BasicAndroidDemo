package com.sandalen.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class ButtonActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        //自定义类
        Button btn = findViewById(R.id.btn1);
        btn.setOnClickListener(new MyClickListener());

        //匿名内部类
        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(v -> Log.e("TAG2","点击按钮2"));

        //本类
        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

        ProgressBar progressBar = findViewById(R.id.progress);
        progressBar.setMax(100);

        //只有progressBar能够在线程中操作，其他控件不行
        new Thread(){
            @Override
            public void run() {
                for(int i = 1;i <= 100;i++){
                    progressBar.setProgress(i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void onClick(View v) {
        Log.e("TAG3","点击按钮3");
    }

    class MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.e("TAG","点击按钮1");
        }
    }

    public void myClick(View v){
        Log.e("TAG","点击按钮4");
    }
}