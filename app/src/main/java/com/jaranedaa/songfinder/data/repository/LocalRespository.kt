package com.jaranedaa.songfinder.data.repository

import android.content.Context
import android.util.Log
import com.jaranedaa.songfinder.data.room.SongFinderDatabase
import com.jaranedaa.songfinder.data.room.dao.SearchDao
import com.jaranedaa.songfinder.data.room.entities.Search
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalRespository(val context: Context) {

    private var db: SongFinderDatabase? = null
    private var searchDao: SearchDao? = null

    fun saveSearch(search: Search) {
        db = SongFinderDatabase.getAppDataBase(context)
        searchDao = db?.SearchDao()

        val id = searchDao?.insertSearch(search)
        Log.i(LocalRespository::class.simpleName, id.toString())

    }
}