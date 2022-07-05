package com.example.demo_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.demo_app.R;
import com.example.demo_app.model.Other_mandi;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private ArrayList<Other_mandi> list;

    public Adapter(Context context, ArrayList<Other_mandi> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recylerview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.crop_id.setText(this.list.get(position).getCrop_id().toString());
        holder.district.setText(this.list.get(position).getDistrict().toString());
        holder.district_id.setText(this.list.get(position).getDistrict_id().toString());
        holder.hindi_name.setText(this.list.get(position).getHindi_name().toString());
        holder.id.setText(this.list.get(position).getId().toString());
        holder.km.setText(this.list.get(position).getKm().toString());
        holder.last_date.setText(this.list.get(position).getLast_date().toString());
        holder.lat.setText(this.list.get(position).getLat().toString());
        holder.lng.setText(this.list.get(position).getLng().toString());
        holder.market.setText(this.list.get(position).getMarket().toString());
        holder.meters.setText(this.list.get(position).getMeters().toString());
        holder.state.setText(this.list.get(position).getState().toString());

        Glide.with(context).load(this.list.get(position).
                getImage()).
                apply(RequestOptions.centerCropTransform()).
                into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView crop_id,district,district_id,hindi_name,id,km,last_date,lat,lng,market,meters,state;
        private ImageView imageView;
        public ViewHolder(View itemView)
        {
            super(itemView);
            crop_id = itemView.findViewById(R.id.crop_id);
            district = itemView.findViewById(R.id.district);
            district_id = itemView.findViewById(R.id.district_id);
            hindi_name = itemView.findViewById(R.id.hindi_name);
            id = itemView.findViewById(R.id.id);
            km = itemView.findViewById(R.id.km);
            last_date = itemView.findViewById(R.id.last_date);
            lat = itemView.findViewById(R.id.lat);
            lng = itemView.findViewById(R.id.lng);
            market = itemView.findViewById(R.id.market);
            meters = itemView.findViewById(R.id.meters);
            state = itemView.findViewById(R.id.state);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
