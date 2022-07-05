package com.example.practicevaritit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practicevaritit.Model.User;
import com.example.practicevaritit.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendRecyclerAdapter extends RecyclerView.Adapter<FriendRecyclerAdapter.FriendViewHolder> {
    private Context context;
    public ArrayList<User> friendList;

    public FriendRecyclerAdapter(Context context, ArrayList<User> friendList) {
        this.context = context;
        this.friendList = friendList;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FriendViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row_friends, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        User user = friendList.get(position);
        holder.fname.setText(user.getName().getFirst());
        holder.lname.setText(new StringBuilder().append(" ").append(user.getName().getLast()).toString());
        holder.email.setText(user.getEmail());
        String[] arr = user.getDob().getDate().split("T",2);
        holder.dob.setText(arr[0]);
        Glide.with(context)
                .load(user.getPicture().getThumbnail())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        if(friendList == null){
            return 0;
        }
        return friendList.size();
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder{
        public TextView fname;
        public TextView dob;
        public TextView email;
        public TextView lname;
        public CircleImageView image;
        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            email = itemView.findViewById(R.id.email);
            dob = itemView.findViewById(R.id.dob);
            image = itemView.findViewById(R.id.image);
        }
    }
}
