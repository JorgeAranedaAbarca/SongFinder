package com.jaranedaa.songfinder.data.repository

import android.content.Context
import android.util.Log
import com.jaranedaa.songfinder.data.room.SongFinderDatabase
import com.jaranedaa.songfinder.data.room.dao.SearchDao
import com.jaranedaa.songfinder.data.room.dao.SearchResultDao
import com.jaranedaa.songfinder.data.room.entities.Search
import com.jaranedaa.songfinder.data.room.entities.SearchResult

class LocalRespository(val context: Context) {

    private var db: SongFinderDatabase? = null
    private var searchDao: SearchDao? = null
    private var searchResultDao: SearchResultDao? = null

    fun saveSearch(search: Search) : Long {
        db = SongFinderDatabase.getAppDataBase(context)
        searchDao = db?.SearchDao()

        return searchDao?.insertSearch(search)!!

    }

    fun getAllPreviousSearchs(): List<Search> {
        db = SongFinderDatabase.getAppDataBase(context)
        searchDao = db?.SearchDao()
        return searchDao?.getAllPreviousSearchs()!!
    }

    fun saveAllResult(listResult : List<SearchResult>){
        db = SongFinderDatabase.getAppDataBase(context)
        searchResultDao = db?.SearchResultDao()
        searchResultDao!!.insertAllSearchResult(listResult)
    }

    fun getAllResultByIdSearch(idSearch : Int) : List<SearchResult>{
        db = SongFinderDatabase.getAppDataBase(context)
        searchResultDao = db?.SearchResultDao()
        return searchResultDao!!.getSearchResultByIdSearch(idSearch)
    }
}