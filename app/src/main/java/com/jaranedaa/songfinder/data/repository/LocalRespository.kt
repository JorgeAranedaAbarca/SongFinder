package com.jaranedaa.songfinder.data.repository

import com.jaranedaa.songfinder.domain.model.Result

class LocalRespository {

    private var listSerchs: MutableList<String> = mutableListOf()
    private var listSongResults : MutableList<Result> = mutableListOf()

    fun saveSearchResults(result: List<Result>) {
        listSongResults.addAll(result)
    }

    fun getAllSongResult(): MutableList<Result> {
        return listSongResults
    }

    fun saveSearch(search: String) {
        listSerchs.add(search)
    }

    fun getAllSearchs(): MutableList<String> {
        return listSerchs
    }
}