package com.sandalen.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.sandalen.testapplication.model.Food;
import com.sandalen.testapplication.model.Person;

import java.util.List;

//Activity：可视化界面
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //R：为每一个资源文件按类别分配一个索引
        //使得程序员k可以通过R.类别名.资源名去操作对应资源
        setContentView(R.layout.viewlayout_test);

    }

    public void register(View v){
        System.out.println("eme");
        EditText nameEdt = findViewById(R.id.name);
        EditText pwdEdt = findViewById(R.id.pwd);

        String name = nameEdt.getText().toString();
        String pwd = pwdEdt.getText().toString();

        if(name.equals("") || pwd.equals("")){
            Toast.makeText(this,"用户或密码不能为空",Toast.LENGTH_SHORT).show();
        }
        else {
            ProgressBar progressBar = findViewById(R.id.pro_bar);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(100);
            new Thread(){
                @Override
                public void run() {
                    for(int i = 0;i <= 100;i++){
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
    }
}