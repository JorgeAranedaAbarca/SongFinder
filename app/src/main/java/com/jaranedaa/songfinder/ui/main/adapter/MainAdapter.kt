package com.jaranedaa.songfinder.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.jaranedaa.songfinder.R
import com.jaranedaa.songfinder.data.room.entities.Search

class MainAdapter (val context: Context, val resultList : List<Search>, val listener : MainAdapter.MainListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.item_search, parent, false))
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val search = resultList.get(position)
        val viewHolder :  MainViewHolder = holder as MainViewHolder
        viewHolder.tvSearchText.text = search.text
        viewHolder.cvSearch.setOnClickListener { listener.onClickListener(search.id!!) }

    }

    class MainViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val tvSearchText = view.findViewById(R.id.tvSearchText) as TextView
        val cvSearch = view.findViewById(R.id.cvSearch) as CardView

    }

    interface MainListener{
        fun onClickListener(idSearch : Int)
    }
}