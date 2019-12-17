package com.jaranedaa.songfinder.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaranedaa.songfinder.R
import com.jaranedaa.songfinder.data.room.entities.Search
import com.jaranedaa.songfinder.ui.main.adapter.MainAdapter
import com.jaranedaa.songfinder.ui.songs.SongActivity
import com.jaranedaa.songfinder.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), MainAdapter.MainListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter
    private lateinit var mRecyclerView: RecyclerView

    private lateinit var etSearch: TextView

    private lateinit var btnSearch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        etSearch = findViewById(R.id.etSearch)


        etSearch.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                searchSongs()
                return@OnKeyListener true
            }
            false
        })
        btnSearch = findViewById(R.id.btnSearch)

        btnSearch.setOnClickListener { searchSongs() }
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.setContext(this)
        val listResult = Observer<List<Search>> {
            setAdapter(it)
        }
        viewModel.getSearchsLiveData().observe(this, listResult)



    }

    private fun setAdapter(list: List<Search>) {
        mainAdapter = MainAdapter(this, list, this)
        mRecyclerView.adapter = mainAdapter
        mainAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllPreviousSearchs()

    }
    fun setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.rvSearchs) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }
    private fun searchSongs() {
        if (etSearch.text.isNotEmpty() && etSearch.text.isNotBlank()) {
            goToNextActivity(null)
        }else{
            etSearch.requestFocus()
            etSearch.error = getString(R.string.err_search)
        }
    }

    private fun goToNextActivity(idSearch : Int?) {
        val intent = Intent(this, SongActivity::class.java)
        intent.putExtra(ARG_NAME_SONG, etSearch.text.toString())
        intent.putExtra(ARG_ID_SEARCH, idSearch)
        startActivity(intent)
        etSearch.text = null

    }

    companion object {
        const val ARG_NAME_SONG: String = "ARG_NAME_SONG"
        const val ARG_ID_SEARCH: String = "ARG_ID_SEARCH"
    }

    override fun onClickListener(idSearch: Int) {
        goToNextActivity(idSearch)
    }
}
