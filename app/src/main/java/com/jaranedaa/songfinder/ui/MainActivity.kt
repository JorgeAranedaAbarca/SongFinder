package com.jaranedaa.songfinder.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
        btnSearch = findViewById(R.id.btnSearch)

        btnSearch.setOnClickListener { searchSongs() }

    }

    private fun searchSongs() {
        if (!etSearch.text.equals("") && etSearch.text.length > 0) {
            goToNextActivity()
        }else{
            etSearch.error = "Por favor escribe una canción"
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
