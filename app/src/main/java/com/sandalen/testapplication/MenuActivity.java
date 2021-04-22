package com.sandalen.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.sandalen.testapplication.model.Food;
import com.sandalen.testapplication.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private EditText mNameEditText;
    private RadioGroup mSexRadioGroup;
    private CheckBox mHotCheckBox, mFishCheckBox, mSourCheckBox;
    private SeekBar mSeekBar;
    private Button mSearchButton;
    private ImageView mFoodImageView;
    private ToggleButton mToggleButton;
    private List<Food> mFoods;
    private Person mPerson;
    private List<Food> mFoodResult;
    private boolean mIsFish;
    private boolean mIsSour;
    private boolean mIsHot;
    private int mPrice;
    private int mCurrentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //1.初始化控件
        findViews();
        //2.初始化数据
        initDatas();
        //3.添加监听
        setListener();
    }

    private void setListener() {
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(mPerson != null){
                    mPerson.setName(s.toString());
                }
            }
        });
        mSexRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.maleButton:
                        mPerson.setSex("男");
                        break;
                    case R.id.femaleButton:
                        mPerson.setSex("女");
                        break;
                }
            }
        });

        mHotCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> mIsHot = isChecked);
        mFishCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> mIsFish = isChecked);
        mSourCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> mIsSour = isChecked);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPrice = seekBar.getProgress();
                Toast.makeText(MenuActivity.this,"价格： " + mPrice,Toast.LENGTH_SHORT).show();
            }
        });

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mToggleButton.isChecked()){
                    mCurrentIndex++;
                    if(mCurrentIndex < mFoodResult.size()){
                        mFoodImageView.setImageResource(mFoodResult.get(mCurrentIndex).getPic());
                    }
                    else{
                        Toast.makeText(MenuActivity.this, "没有啦", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    if(mCurrentIndex < mFoodResult.size()){
                        String personName = mPerson.getName();
                        String personSex = mPerson.getSex();
                        Toast.makeText(MenuActivity.this,
                                "菜名： " + mFoodResult.get(mCurrentIndex).getName() +",人名："+personName+",性别"+personSex,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MenuActivity.this, "没有啦", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private void search(){
        if(mFoodResult == null){
            mFoodResult = new ArrayList<>();
        }

        mFoodResult.clear();
        mCurrentIndex = 0;

        for(Food food : mFoods){
            if(food.getPrice() <= mPrice && (food.isHot() == mIsHot ||
                    food.isFish() == mIsFish || food.isSour() == mIsSour)){
                mFoodResult.add(food);
            }
        }

        if(mCurrentIndex < mFoodResult.size()){
            mFoodImageView.setImageResource(mFoodResult.get(mCurrentIndex).getPic());
        }
        else{
            mFoodImageView.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }

    private void initDatas() {
        mFoods = new ArrayList<>();
        mFoods.add(new Food("麻辣香锅", 55, R.drawable.malaxiangguo, true, false, false));
        mFoods.add(new Food("水煮鱼", 48, R.drawable.shuizhuyu, true, true, false));
        mFoods.add(new Food("麻辣火锅", 80, R.drawable.malahuoguo, true, true, false));
        mFoods.add(new Food("清蒸鲈鱼", 68, R.drawable.qingzhengluyu, false, true, false));
        mFoods.add(new Food("桂林米粉", 15, R.drawable.guilin, false, false, false));
        mFoods.add(new Food("上汤娃娃菜", 28, R.drawable.wawacai, false, false, false));
        mFoods.add(new Food("红烧肉", 60, R.drawable.hongshaorou, false, false, false));
        mFoods.add(new Food("木须肉", 40, R.drawable.muxurou, false, false, false));
        mFoods.add(new Food("酸菜牛肉面", 35, R.drawable.suncainiuroumian, false, false, true));
        mFoods.add(new Food("西芹炒百合", 38, R.drawable.xiqin, false, false, false));
        mFoods.add(new Food("酸辣汤", 40, R.drawable.suanlatang, true, false, true));

        mPerson = new Person();
        mFoodResult = new ArrayList<>();
    }

    private void findViews() {
        mNameEditText = findViewById(R.id.editText);
        mNameEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mNameEditText.setInputType(InputType.TYPE_NULL); // 关闭软键盘

                return false;
            }
        });
        mSexRadioGroup = findViewById(R.id.sexRadioGroup);
        mHotCheckBox = findViewById(R.id.hotCheckBox);
        mFishCheckBox = findViewById(R.id.fishCheckBox);
        mSourCheckBox = findViewById(R.id.sourCheckBox);
        mSeekBar = findViewById(R.id.seekBar);
        mSeekBar.setProgress(30);
        mSearchButton = findViewById(R.id.searchButton);
        mToggleButton = findViewById(R.id.showToggleButton);
        mToggleButton.setChecked(true);
        mFoodImageView = findViewById(R.id.foodImageView);
    }




}