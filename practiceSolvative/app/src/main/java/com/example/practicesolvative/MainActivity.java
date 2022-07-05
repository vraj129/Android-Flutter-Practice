package com.example.practicesolvative;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.practicesolvative.adapter.Adapter;
import com.example.practicesolvative.model.ContactModel;
import com.example.practicesolvative.model.model;
import com.example.practicesolvative.viewmodel.ContactListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ContactModel> contactModelList;
    private Adapter adapter;
    private ContactListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager  layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(this, contactModelList);
        viewModel = ViewModelProviders.of(this).get(ContactListViewModel.class);
//        viewModel.getContactList().observe(this, new Observer<List<ContactModel>>() {
//            @Override
//            public void onChanged(List<ContactModel> contactModels) {
//                if(contactModels != null) {
//                    contactModelList = contactModels;
//                    adapter.setContactModelList(contactModels);
//                }
//            }
//        });
        viewModel.getContactList().observe(this, new Observer<model>() {
            @Override
            public void onChanged(model model) {
                if(model != null){
                    contactModelList = model.getContacts();
                    adapter.setContactModelList(model.getContacts());
                }
            }
        });
        viewModel.makeCall();
    }
}