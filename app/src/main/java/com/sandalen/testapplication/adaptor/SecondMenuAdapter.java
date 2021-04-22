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

public class SecondMenuAdapter extends RecyclerView.Adapter<SecondMenuHolder>{

    private List<Menu> datas;
    private Context context;

    public SecondMenuAdapter(Context context,List<Menu> datas) {
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public SecondMenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SecondMenuHolder(LayoutInflater.from(context).inflate(R.layout.item_second_menu,null));
    }

    @Override
    public void onBindViewHolder(@NonNull SecondMenuHolder holder, int position) {
        Menu menu = datas.get(position);
        holder.imageView.setImageResource(menu.getIcon());
        holder.textView.setText(menu.getName());
    }

    @Override
    public int getItemCount() {
        return null == datas ? 0 : datas.size();
    }
}

class SecondMenuHolder extends RecyclerView.ViewHolder{

    public ImageView imageView;
    public TextView textView;

    public SecondMenuHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.second_menu_icon);
        textView = itemView.findViewById(R.id.second_menu_txt);
    }
}
