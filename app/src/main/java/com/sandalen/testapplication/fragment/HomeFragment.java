package com.sandalen.testapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.sandalen.testapplication.R;
import com.sandalen.testapplication.adaptor.MainHeaderAdapter;
import com.sandalen.testapplication.adaptor.MainMenuAdapter;
import com.sandalen.testapplication.adaptor.SecondMenuAdapter;
import com.sandalen.testapplication.util.DataUtil;

public class HomeFragment extends Fragment {

    protected int[] adIds = {
            R.mipmap.header_pic_ad1,
            R.mipmap.header_pic_ad2,
            R.mipmap.header_pic_ad1
    };

    protected int[] menuIcons = {
            R.mipmap.menu_airport,R.mipmap.menu_car,R.mipmap.menu_course,
            R.mipmap.menu_hatol,R.mipmap.menu_nearby,R.mipmap.me_menu_go,
            R.mipmap.menu_ticket,R.mipmap.menu_train
    };

    protected int[] secondMenuIcons = {
            R.mipmap.menu_second_ticket,
            R.mipmap.menu_second_service,
            R.mipmap.menu_second_airport
    };

    protected String[] menus;
    protected RecyclerView recyclerView;

    protected String[] secondMenus;
    protected RecyclerView secondRecycleView;

    protected ViewPager adViewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //viewPager
        adViewPager = getView().findViewById(R.id.vpager_main_header_ad);
        MainHeaderAdapter adapter = new MainHeaderAdapter(getActivity(), DataUtil.getHeaderInfo(getActivity(),adIds));
        adViewPager.setAdapter(adapter);

        //menus
        menus = this.getActivity().getResources().getStringArray(R.array.main_menu);
        recyclerView = getView().findViewById(R.id.recyclevie_main_menu);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        MainMenuAdapter menuAdapter = new MainMenuAdapter(getActivity(),DataUtil.getMenus(menuIcons,menus));
        recyclerView.setAdapter(menuAdapter);

        //second menu
        secondMenus = this.getActivity().getResources().getStringArray(R.array.second_menu);
        secondRecycleView = getView().findViewById(R.id.recycleview_second_menu);
        secondRecycleView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        SecondMenuAdapter secondAdapter = new SecondMenuAdapter(getActivity(),DataUtil.getMenus(secondMenuIcons,secondMenus));
        secondRecycleView.setAdapter(secondAdapter);
    }
}
