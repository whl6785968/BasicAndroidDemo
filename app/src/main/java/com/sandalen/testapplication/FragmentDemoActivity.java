package com.sandalen.testapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sandalen.testapplication.fragment.ListFragment;

public class FragmentDemoActivity extends AppCompatActivity implements ListFragment.OnTitleClickListenr {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);

        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FragmentDemoActivity.this,StaticLoadFragmentActivity.class));
            }
        });

        //fragment动态加载
        //1.container 2.fragment 3.fragment ->> container
        //remove、add、replace
        FragmentManager manager = getSupportFragmentManager();
        ListFragment listFragment = ListFragment.newInstance("喜喜");
        listFragment.setOnTitleClickListenr(this);
        manager.beginTransaction().add(R.id.listContainer,listFragment).commit();

        ListFragment detailFragment = ListFragment.newInstance("哈哈");
        detailFragment.setOnTitleClickListenr(this);
        manager.beginTransaction().add(R.id.detailContainer,detailFragment).commit();
    }

    @Override
    public void onClick(String title) {
        setTitle(title);
    }
}