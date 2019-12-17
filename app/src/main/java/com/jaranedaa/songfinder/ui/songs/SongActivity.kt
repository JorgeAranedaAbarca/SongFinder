package com.jaranedaa.songfinder.ui.songs

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaranedaa.songfinder.R
import com.jaranedaa.songfinder.domain.model.Result
import com.jaranedaa.songfinder.ui.album.AlbumActivity
import com.jaranedaa.songfinder.ui.songs.adapter.SongAdapter
import com.jaranedaa.songfinder.viewModel.SongViewModel


class SongActivity : AppCompatActivity(), SongAdapter.SongAdapterLister {

    private lateinit var name: String
    private var idSearch: Int? = null
    private lateinit var songViewModel: SongViewModel

    private lateinit var songAdapter: SongAdapter
    private lateinit var mRecyclerView: RecyclerView

    private lateinit var progressBar: ProgressBar


    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
        setUpRecyclerView()
        progressBar = findViewById(R.id.progressBar)
        name = intent.getStringExtra(ARG_NAME_SONG)
        idSearch = intent.getIntExtra(ARG_ID_SEARCH, 0)


        songViewModel = ViewModelProviders.of(this).get(SongViewModel::class.java)
        songViewModel.setContext(this)
        val listArtist = Observer<List<Result>> {
            setAdapter(it)

        }
        songViewModel.getSongsLiveData().observe(this, listArtist)

        if (isNetworkAvailable()) {
            if (name.isNotEmpty()) {
                songViewModel.getSongByName(name)
            } else {
                songViewModel.getResultByIdSearch(idSearch!!)
            }
        } else if(idSearch != null && idSearch != 0){
            songViewModel.getResultByIdSearch(idSearch!!)
        }else{
            Toast.makeText(this, "Revisa tu conexiòn a internet ...", Toast.LENGTH_LONG).show()
            finish()
        }

    }

    fun setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.rvSongs) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        mRecyclerView.layoutManager = linearLayoutManager

    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        mRecyclerView.visibility = View.GONE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
        mRecyclerView.visibility = View.VISIBLE

    }

    private fun setAdapter(list: List<Result>) {
        hideLoading()
        if (name.isNotEmpty()) {
            songViewModel.saveResults(list, name)
        }
        songAdapter = SongAdapter(list, this, this)
        mRecyclerView.adapter = songAdapter
        songAdapter.notifyDataSetChanged()
    }

    companion object {
        const val ARG_NAME_SONG: String = "ARG_NAME_SONG"
        const val ARG_ID_SEARCH: String = "ARG_ID_SEARCH"
        const val ARG_COLLECTION_ID: String = "ARG_COLLECTION_ID"

    }

    override fun onClickSong(song: Result) {
        val intent = Intent(this, AlbumActivity::class.java)
        intent.putExtra(ARG_COLLECTION_ID, song.collectionId.toString())
        startActivity(intent)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
