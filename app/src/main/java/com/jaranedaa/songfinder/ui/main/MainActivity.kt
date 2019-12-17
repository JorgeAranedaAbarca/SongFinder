package com.jaranedaa.songfinder.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.jaranedaa.songfinder.R
import com.jaranedaa.songfinder.ui.songs.SongActivity
import com.jaranedaa.songfinder.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var etSearch: TextView

    private lateinit var btnSearch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etSearch = findViewById(R.id.etSearch)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.setContext(this)
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

    }

    private fun searchSongs() {
        if (etSearch.text.isNotEmpty() && etSearch.text.isNotBlank()) {
            viewModel.saveSearch(etSearch.text.toString())
            goToNextActivity()
        }else{
            etSearch.requestFocus()
            etSearch.error = getString(R.string.err_search)
        }
    }

    private fun goToNextActivity() {
        val intent = Intent(this, SongActivity::class.java)
        intent.putExtra(ARG_NAME_SONG, etSearch.text.toString())
        startActivity(intent)
    }

    companion object {
        const val ARG_NAME_SONG: String = "ARG_NAME_SONG"
    }
}
