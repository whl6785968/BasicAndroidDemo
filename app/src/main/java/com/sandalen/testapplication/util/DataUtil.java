package com.sandalen.testapplication.util;

import android.content.Context;
import android.media.Image;
import android.widget.ImageView;

import com.sandalen.testapplication.entity.Menu;

import java.util.ArrayList;
import java.util.List;

public class DataUtil {

    public static List<ImageView> getHeaderInfo(Context context,int[] icons){
        List<ImageView> datas = new ArrayList<>();

        for (int i = 0; i < icons.length; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(icons[i]);
            //均衡的缩放图像（保持图像原始比例），如果是center表示不缩放
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            datas.add(imageView);
        }

        return datas;
    }

    public static List<Menu> getMenus(int[] icons,String[] names){
        List<Menu> datas = new ArrayList<>();

        for (int i = 0; i < icons.length; i++) {
            Menu menu = new Menu(icons[i], names[i]);
            datas.add(menu);
        }

        return datas;
    }
}
