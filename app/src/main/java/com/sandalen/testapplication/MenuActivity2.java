package com.sandalen.testapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MenuActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        //ctx_menu:
        //1.注册
//        registerForContextMenu(findViewById(R.id.ctx_btn));
        //2.创建
        //3.菜单项操作
        //4.为按钮设置上下文操作模式
        // - 实现 ActionModel Callback

        // - 在view的长按事件启动上下文操作模式
        findViewById(R.id.ctx_btn).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActionMode(cb);
                return false;
            }
        });

        Button pop_btn = findViewById(R.id.pop_btn);
        pop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //实例化PopupMenu对象
                PopupMenu popupMenu = new PopupMenu(MenuActivity2.this,pop_btn);
                //加载菜单资源
                popupMenu.getMenuInflater().inflate(R.menu.context,popupMenu.getMenu());
                //设置监听事件
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                //展示
                popupMenu.show();
            }
        });

        //普通弹出框
        Button normal_btn = findViewById(R.id.normal_btn);
        normal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity2.this);
                builder.setTitle("prompt");
                builder.setMessage("Are you sure exit system");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("cancel",null);
                builder.show();
            }
        });

        //自定义弹出框
        Button custom_btn = findViewById(R.id.custom_btn);
        custom_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog md = new MyDialog(MenuActivity2.this,R.style.mydialog);
                md.show();
            }
        });

        //pop弹出
        Button popup_btn = findViewById(R.id.popup_btn);
        popup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
            }
        });

        Button array_adapter = findViewById(R.id.array_adpter);
        array_adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showArrayAdapterWindow(v);
            }


        });
    }

    ActionMode.Callback cb = new ActionMode.Callback(){

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            Log.e("Tag1","create");
            getMenuInflater().inflate(R.menu.context,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            Log.e("Tag2","prepare");
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            Log.e("Tag3","click");
            switch (item.getItemId()){
                case R.id.delete:
                    Toast.makeText(MenuActivity2.this,"删除",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rename:
                    Toast.makeText(MenuActivity2.this,"重命名",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    MenuActivity2.super.onOptionsItemSelected(item);
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            Log.e("Tag4","destroy");
        }
    };

    private void showArrayAdapterWindow(View view) {
        final String[] items = {"Java","Mysql","Android","HTML","C","JavaScript"};

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,items);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.array_item_layout,R.id.item_txt,items);
        AlertDialog.Builder window = new AlertDialog.Builder(this)
                .setTitle("请选择")
                .setAdapter(adapter,null);
        window.show();
    }

    public void showPopupWindow(View view){
        //1.准备弹窗所需的布局文件，将其转化为view
        View v = LayoutInflater.from(this).inflate(R.layout.popup_layout,null);
        final PopupWindow window = new PopupWindow(v,190,35,true);
        //设置背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //响应外部点击事件
        window.setOutsideTouchable(true);
        //响应点击事件
        window.setTouchable(true);

        window.showAsDropDown(view,100,50);
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        getMenuInflater().inflate(R.menu.context,menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.delete:
//                Toast.makeText(this,"删除",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.rename:
//                Toast.makeText(this,"重命名",Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                Toast.makeText(this,"保存",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this,"设置",Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}