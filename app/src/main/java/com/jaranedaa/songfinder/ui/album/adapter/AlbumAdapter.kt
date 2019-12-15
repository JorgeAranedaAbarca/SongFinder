package com.jaranedaa.songfinder.ui.album.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jaranedaa.songfinder.R
import com.jaranedaa.songfinder.domain.model.Result
import com.jaranedaa.songfinder.util.Util.Companion.convertTimeMilesInHoursAndMinutes

class AlbumAdapter(val context: Context, val resultList: List<Result>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(viewType == 0) {
            //album
            return AlbumViewHolder(LayoutInflater.from(context).inflate(R.layout.item_album, parent, false))
        }else{
            //songs
            return SongViewHolder(LayoutInflater.from(context).inflate(R.layout.item_album_song, parent, false))

        }

    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val song = resultList.get(position)

        if(holder.itemViewType == 0){
            //album
            val viewHolder :  AlbumViewHolder = holder as AlbumViewHolder
            viewHolder.tvBandName.text = song.artistName
            viewHolder.tvAlbumName.text = song.collectionName
        }else{
            //songs
            val viewHolder :  SongViewHolder = holder as SongViewHolder
            viewHolder.tvSongName.text = song.trackName
            viewHolder.tvTime.text = convertTimeMilesInHoursAndMinutes(song.trackTimeMillis)

        }
    }

    override fun getItemViewType(position: Int): Int {
        if(resultList.get(position).wrapperType.equals("collection")){
            return 0
        }else{
            return 1
        }
    }


    class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvAlbumName = view.findViewById(R.id.tvAlbumName) as TextView
        val tvBandName = view.findViewById(R.id.tvBandName) as TextView
        val imgAlbum = view.findViewById(R.id.imgAlbum) as ImageView

    }
    class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvSongName = view.findViewById(R.id.tvSongName) as TextView
        val tvTime = view.findViewById(R.id.tvTime) as TextView

    }
}