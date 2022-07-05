package com.example.kotlinpractice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinpractice.R
import com.example.kotlinpractice.model.RepositoryData

class RecyclerViewAdapter(): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var listDate: List<RepositoryData>? = null
    fun setListData(listData: List<RepositoryData>?){
        this.listDate = listData
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(listDate?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listDate == null ) return 0
        else
            return listDate?.size!!
    }

    class MyViewHolder(view : View):RecyclerView.ViewHolder(view){
        val image : ImageView = view.findViewById(R.id.image)
        val name : TextView = view.findViewById(R.id.country_name)
        fun bind(data : RepositoryData){
            name.text = data.country
            Glide.with(image)
                .load(data.countryInfo?.flag)
                .into(image)
        }
    }

}