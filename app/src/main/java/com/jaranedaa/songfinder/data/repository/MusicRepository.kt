package com.jaranedaa.songfinder.data.repository

import android.util.Log
import com.jaranedaa.songfinder.data.api.iTunesApi
import com.jaranedaa.songfinder.domain.model.Song
import java.lang.Exception

class MusicRepository : ApiRepository() {

    private val api = iTunesApi.create()

    suspend fun getSongsByName(name: String): List<Song> {
        var listArtist = listOf<Song>()
        try {
            var songResponse = api.getSongByName(name, "20").await()
            if (songResponse.isSuccessful) {
                if (songResponse.body() != null) {
                    if (songResponse.body()!!.resultCount > 0) {
                        listArtist = songResponse.body()!!.results
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("Err: ", e.toString())
        }

        return listArtist
    }
}