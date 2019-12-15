package com.jaranedaa.songfinder.ui.songs.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaranedaa.songfinder.R
import com.jaranedaa.songfinder.domain.model.Song

class SongAdapter(val songList: List<Song>, val context: Context) :
    RecyclerView.Adapter<SongAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_song, parent, false))
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songList.get(position)

        holder.tvArtistName.text = song.artistName
        holder.tvSongName.text = song.trackName
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvArtistName = view.findViewById(R.id.tvArtistName) as TextView
        val tvSongName = view.findViewById(R.id.tvSongName) as TextView
    }
}