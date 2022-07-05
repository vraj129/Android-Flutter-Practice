package com.example.sqlitedemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {

    public List<model> list;
    public adapter(List<model> list)
    {
        this.list = list;
    }


    @NonNull
    @Override
    public adapter.MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull  adapter.MyViewHolder holder, int position) {
        String name = list.get(position).getName();
        holder.item.setText(name);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView item;
        public MyViewHolder(final View view) {
            super(view);
            item = view.findViewById(R.id.item_name);
        }
    }
}
