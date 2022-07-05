package com.example.practicesolvative.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicesolvative.R;
import com.example.practicesolvative.model.ContactModel;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ContactViewHolder>{
    private Context context;
    private List<ContactModel> contactModelList;

    public Adapter(Context context, List<ContactModel> contactModelList) {
        this.context = context;
        this.contactModelList = contactModelList;
    }

    public void setContactModelList(List<ContactModel> contactModelList) {
        this.contactModelList = contactModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.name.setText(this.contactModelList.get(position).getName());
        holder.email.setText(this.contactModelList.get(position).getEmail());
        holder.gender.setText(this.contactModelList.get(position).getGender());
        //holder.mobile.setText(this.contactModelList.get(position).getPhoneModel().getMobile());
    }

    @Override
    public int getItemCount() {
        if(contactModelList != null){
            return contactModelList.size();
        }
        return 0;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView email;
        TextView gender;
        TextView mobile;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textview_name);
            email = (TextView) itemView.findViewById(R.id.textview_email);
            gender = (TextView) itemView.findViewById(R.id.textview_gender);
            mobile = (TextView) itemView.findViewById(R.id.textview_mobile);

        }
    }
}
