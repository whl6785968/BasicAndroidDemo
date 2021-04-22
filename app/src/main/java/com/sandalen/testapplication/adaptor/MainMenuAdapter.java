package com.sandalen.testapplication.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sandalen.testapplication.R;
import com.sandalen.testapplication.entity.Menu;

import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuHolder> {

    private Context context;
    private List<Menu> datas;

    public MainMenuAdapter(Context context, List<Menu> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public MainMenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainMenuHolder(LayoutInflater.from(context).inflate(R.layout.item_main_menu,null));
    }

    @Override
    public void onBindViewHolder(@NonNull MainMenuHolder holder, int position) {
        Menu menu = datas.get(position);
        holder.imageView.setImageResource(menu.getIcon());
        holder.textView.setText(menu.getName());
    }



    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.size();
    }
}

class MainMenuHolder extends RecyclerView.ViewHolder{

    public ImageView imageView;
    public TextView textView;

    public MainMenuHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.img_menu_icon);
        textView = itemView.findViewById(R.id.txt_menu_name);
    }
}
