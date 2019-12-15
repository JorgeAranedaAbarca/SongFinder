package com.jaranedaa.songfinder.ui.songs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaranedaa.songfinder.R
import com.jaranedaa.songfinder.domain.model.Song
import com.jaranedaa.songfinder.ui.songs.adapter.SongAdapter
import com.jaranedaa.songfinder.viewModel.SongViewModel

class SongActivity : AppCompatActivity() {

    private lateinit var name: String
    private lateinit var songViewModel: SongViewModel

    private lateinit var songAdapter: SongAdapter
    private lateinit var mRecyclerView: RecyclerView

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        progressBar = findViewById(R.id.progressBar)
        setUpRecyclerView()
        name = intent.getStringExtra(ARG_NAME_SONG)


        songViewModel = ViewModelProviders.of(this).get(SongViewModel::class.java)
        val listArtist = Observer<List<Song>> {
            setAdapter(it)
        }
        songViewModel.getSongsLiveData().observe(this, listArtist)

    }

    override fun onResume() {
        super.onResume()
        showLoading()
        songViewModel.getSongByName(name)
    }

    fun setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.rvSongs) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        mRecyclerView.visibility = View.GONE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
        mRecyclerView.visibility = View.VISIBLE

    }

    private fun setAdapter(list: List<Song>) {
        songAdapter = SongAdapter(list, this)
        mRecyclerView.adapter = songAdapter
        songAdapter.notifyDataSetChanged()
        hideLoading()
    }

    companion object {
        const val ARG_NAME_SONG: String = "ARG_NAME_SONG"
    }
}
