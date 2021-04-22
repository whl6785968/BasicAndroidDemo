package com.sandalen.testapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private int[] mLayoutIds = {
        R.layout.view_first,
        R.layout.view_second,
        R.layout.view_third
    };
    private ViewPager viewPager;
    private List<View> views;
    private ViewGroup viewGroup;
    private List<ImageView> dotViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.view_pager);
        views = new ArrayList<>();
        viewGroup = findViewById(R.id.dot_layout);
        dotViews = new ArrayList<>();

        for (int i = 0; i < mLayoutIds.length; i++) {
//            View view = getLayoutInflater().inflate(mLayoutIds[i], null);
//            views.add(view);
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_launcher);
            views.add(imageView);

            ImageView dot = new ImageView(this);
            dot.setImageResource(R.mipmap.ic_launcher);
            dot.setMaxHeight(100);
            dot.setMaxWidth(100);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(80,80);
            layoutParams.leftMargin = 20;
            dot.setLayoutParams(layoutParams);
            dot.setEnabled(false);

            viewGroup.addView(dot);
            dotViews.add(dot);
        }

        viewPager.setAdapter(pagerAdapter);
        //显示左右几个页面
//        viewPager.setOffscreenPageLimit(4);
        //初始时显示第几页
        viewPager.setCurrentItem(0);
        setDotViews(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setDotViews(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mLayoutIds.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = views.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(views.get(position));
        }
    };

    public void setDotViews(int position){
        for (int i = 0; i < dotViews.size(); i++) {
            if(position == i){
                dotViews.get(i).setImageResource(R.mipmap.star);
            }
            else{
                dotViews.get(i).setImageResource(R.mipmap.ic_launcher);
            }
        }
    }
}