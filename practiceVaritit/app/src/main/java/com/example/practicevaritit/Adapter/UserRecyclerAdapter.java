package com.example.practicevaritit.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practicevaritit.Model.User;
import com.example.practicevaritit.R;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>  {

    private Context context;
    private List<User> userList;
    List<User> userListAll;
    public ArrayList<User> friendList = new ArrayList<>();


    public UserRecyclerAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        userListAll = new ArrayList<>(userList);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new UserViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User user = userList.get(position);

        holder.fname.setText(user.getName().getFirst());
        holder.lname.setText(new StringBuilder().append(" ").append(user.getName().getLast()).toString());
        holder.email.setText(user.getEmail());
        String[] arr = user.getDob().getDate().split("T",2);
        holder.dob.setText(arr[0]);
        Glide.with(context)
                .load(user.getPicture().getThumbnail())
                .into(holder.image);

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.checkBox.isChecked()){
                    friendList.add(user);
                }else {
                    friendList.remove(user);
                }
            }
        });
    }

    public void setUserListAll(List<User> userListAll) {
        this.userListAll = userListAll;
    }

    public void getAllUsers(List<User> userList){
        this.userList = userList;
    }
    @Override
    public int getItemCount() {
        return userList.size();
    }

    public ArrayList<User> getFriendLists(){
        return friendList;
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder{
        public TextView fname;
        public TextView dob;
        public TextView email;
        public TextView lname;
        public CheckBox checkBox;
        public CircleImageView image;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            email = itemView.findViewById(R.id.email);
            dob = itemView.findViewById(R.id.dob);
            image = itemView.findViewById(R.id.image);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
