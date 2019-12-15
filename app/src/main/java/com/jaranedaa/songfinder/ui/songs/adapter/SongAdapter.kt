package com.jaranedaa.songfinder.ui.songs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.jaranedaa.songfinder.R
import com.jaranedaa.songfinder.domain.model.Result
import com.squareup.picasso.Picasso

class SongAdapter(val resultList: List<Result>, val context: Context, val listener: SongAdapterLister) :
    RecyclerView.Adapter<SongAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_song, parent, false))
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = resultList.get(position)

        holder.tvArtistName.text = song.artistName
        holder.tvSongName.text = song.trackName
        Picasso.get().load(song.artworkUrl100).into(holder.imgAlbumSmall)

        holder.cvSong.setOnClickListener { listener.onClickSong(song) }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvArtistName = view.findViewById(R.id.tvArtistName) as TextView
        val tvSongName = view.findViewById(R.id.tvSongName) as TextView
        val cvSong = view.findViewById(R.id.cvSong) as CardView
        val imgAlbumSmall = view.findViewById(R.id.imgAlbumSmall) as ImageView
    }

    interface SongAdapterLister{
        fun onClickSong(song : Result)
    }
}