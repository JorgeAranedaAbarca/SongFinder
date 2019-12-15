package com.jaranedaa.songfinder.ui

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jaranedaa.songfinder.R
import com.jaranedaa.songfinder.domain.model.Artist
import com.jaranedaa.songfinder.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var tvCount : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCount = findViewById(R.id.tvCount)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val listArtist = Observer<List<Artist>>{
            setAdapter(it)
        }

        viewModel.getSongsLiveData().observe(this, listArtist)
        getInfo()
    }

    private fun setAdapter(list: List<Artist>?) {
        if (list != null) {
            tvCount.setText(list.size.toString())
        }
    }

    private fun getInfo() {
        viewModel.getSongByName("Aerials")
    }
}
