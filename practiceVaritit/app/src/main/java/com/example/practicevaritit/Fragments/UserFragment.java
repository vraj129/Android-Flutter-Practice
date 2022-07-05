package com.example.practicevaritit.Fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.practicevaritit.Adapter.UserRecyclerAdapter;
import com.example.practicevaritit.Model.JsonResponse;
import com.example.practicevaritit.Model.User;
import com.example.practicevaritit.Network.ApiService;
import com.example.practicevaritit.R;
import com.example.practicevaritit.Repository.UserRepository;
import com.example.practicevaritit.ViewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserFragment extends Fragment {
    private UserViewModel userViewModel;
    private RecyclerView recyclerView;
    private UserRecyclerAdapter userRecyclerAdapter;
    private List<User> userList;
    private UserRepository userRepository;
    private MenuItem menuItem;
    private SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_user, container, false);

        recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        userRepository = new UserRepository(getActivity().getApplication());
        userList= new ArrayList<>();
        userRecyclerAdapter= new UserRecyclerAdapter(getActivity(),userList);
        userViewModel= new ViewModelProvider(getActivity()).get(UserViewModel.class);

        userViewModel.getAllUsers().observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userRecyclerAdapter.getAllUsers(users);
                recyclerView.setAdapter(userRecyclerAdapter);
                userRecyclerAdapter.notifyDataSetChanged();
                Bundle friend = new Bundle();
                friend.putSerializable("friendlist",userRecyclerAdapter.getFriendLists());
                getParentFragmentManager().setFragmentResult("userfragment",friend);
            }
        });
        networkRequest();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu,menu);

        menuItem = menu.findItem(R.id.search_button);
        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setIconified(true);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query != null){
                    searchDatabase(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != null){
                    searchDatabase(newText);
                }
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
    private void searchDatabase(String query){
        String query1 = "%"+query+"%";
        userViewModel.searchDatabase(query1).observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> userList) {
                userRecyclerAdapter.getAllUsers(userList);
                userRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }



    private void networkRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        Call<JsonResponse> call = service.getUsers();
        call.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
                if(response.isSuccessful()){
                    userRepository.deletAllUser();
                    for (User user :response.body().getResults()){
                        userRepository.insert(user);
                    }
                }
            }
            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
            }
        });
    }

}