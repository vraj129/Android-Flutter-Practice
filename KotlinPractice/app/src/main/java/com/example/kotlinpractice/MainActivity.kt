package com.example.kotlinpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpractice.adapter.RecyclerViewAdapter
import com.example.kotlinpractice.model.RepositoryData
import com.example.kotlinpractice.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter:RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initMainViewModel()

    }
    private fun initViewModel(){
        val recyclerview = findViewById<RecyclerView>(R.id.recycler)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(applicationContext,DividerItemDecoration.VERTICAL)
        recyclerview.addItemDecoration(decoration)
        recyclerViewAdapter = RecyclerViewAdapter()
        val adapter = recyclerViewAdapter
        recyclerview.adapter = adapter
    }

    private fun initMainViewModel(){
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAllRepositoryList().observe(this, Observer<List<RepositoryData>> {
            recyclerViewAdapter.setListData(it)
            recyclerViewAdapter.notifyDataSetChanged()
        })
        viewModel.makeAPICall()
    }
}