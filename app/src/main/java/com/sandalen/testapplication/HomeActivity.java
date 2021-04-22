package com.sandalen.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.sandalen.testapplication.fragment.FindFragment;
import com.sandalen.testapplication.fragment.HomeFragment;
import com.sandalen.testapplication.fragment.ListFragment;
import com.sandalen.testapplication.fragment.MeFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    protected HomeFragment homeFragment = new HomeFragment();
    protected FindFragment findFragment = new FindFragment();
    protected MeFragment meFragment = new MeFragment();

    protected LinearLayout homeLayout;
    protected LinearLayout findLayout;
    protected LinearLayout meLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //初始化控件，绑定点击事件
        initView();

        //动态加载fragment
        //获取管理类
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_content,homeFragment)
                .add(R.id.container_content,findFragment)
                .hide(findFragment)
                .add(R.id.container_content,meFragment)
                .hide(meFragment)
                .commit();
    }

    protected void initView(){
        homeLayout = findViewById(R.id.menu_main);
        findLayout = findViewById(R.id.menu_find);
        meLayout = findViewById(R.id.menu_me);

        homeLayout.setOnClickListener(this);
        findLayout.setOnClickListener(this);
        meLayout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_main:
                getSupportFragmentManager().beginTransaction()
                        .show(homeFragment)
                        .hide(findFragment)
                        .hide(meFragment)
                        .commit();
                break;
            case R.id.menu_find:
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .show(findFragment)
                        .hide(meFragment)
                        .commit();
                break;
            case R.id.menu_me:
                getSupportFragmentManager().beginTransaction()
                        .hide(homeFragment)
                        .hide(findFragment)
                        .show(meFragment)
                        .commit();
                break;
        }
    }
}