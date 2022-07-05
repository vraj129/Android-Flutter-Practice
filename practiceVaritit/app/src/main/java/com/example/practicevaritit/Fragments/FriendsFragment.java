package com.example.practicevaritit.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.practicevaritit.Adapter.FriendRecyclerAdapter;
import com.example.practicevaritit.Adapter.UserRecyclerAdapter;
import com.example.practicevaritit.Model.User;
import com.example.practicevaritit.R;

import java.util.ArrayList;


public class FriendsFragment extends Fragment {

    private ArrayList<User> demo;
    private Button btn;
    private RecyclerView recyclerView;
    private FriendRecyclerAdapter friendRecyclerAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_friends, container, false);
        btn = v.findViewById(R.id.btn);
        recyclerView = v.findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getParentFragmentManager().setFragmentResultListener("userfragment", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                    demo = (ArrayList<User>) result.getSerializable("friendlist");
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(demo.size() == 0){
                    Toast.makeText(getActivity(),"No Friends",Toast.LENGTH_SHORT).show();
                }
                friendRecyclerAdapter = new FriendRecyclerAdapter(getActivity(),demo);
                recyclerView.setAdapter(friendRecyclerAdapter);
                friendRecyclerAdapter.notifyDataSetChanged();
            }
        });
        return v;
    }
}