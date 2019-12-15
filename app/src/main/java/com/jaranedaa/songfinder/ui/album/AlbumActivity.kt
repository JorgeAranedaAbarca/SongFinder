package com.jaranedaa.songfinder.ui.album

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaranedaa.songfinder.R
import com.jaranedaa.songfinder.domain.model.Result
import com.jaranedaa.songfinder.ui.album.adapter.AlbumAdapter
import com.jaranedaa.songfinder.viewModel.AlbumViewModel

class AlbumActivity : AppCompatActivity() {


    private lateinit var collectionId: String
    private lateinit var albumViewModel: AlbumViewModel

    private lateinit var albumAdapter: AlbumAdapter
    private lateinit var mRecyclerView: RecyclerView

    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)


        progressBar = findViewById(R.id.progressBar)
        collectionId = intent.getStringExtra(ARG_COLLECTION_ID)

        setUpRecyclerView()


        albumViewModel = ViewModelProviders.of(this).get(AlbumViewModel::class.java)
        val listResult = Observer<List<Result>> {
            setAdapter(it)
        }
        albumViewModel.getAlbumLiveData().observe(this, listResult)

    }

    override fun onResume() {
        super.onResume()
        showLoading()
        albumViewModel.getAlbumByCollectionId(collectionId)
    }

    fun setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.rvAlbum) as RecyclerView
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


    private fun setAdapter(list: List<Result>) {
        albumAdapter = AlbumAdapter(this, list)
        mRecyclerView.adapter = albumAdapter
        albumAdapter.notifyDataSetChanged()
        hideLoading()
    }


    companion object {
        const val ARG_COLLECTION_ID: String = "ARG_COLLECTION_ID"

    }

}

