package com.jaranedaa.songfinder.domain.usecase

import android.content.Context
import com.jaranedaa.songfinder.data.repository.LocalRespository
import com.jaranedaa.songfinder.data.room.entities.Search
import com.jaranedaa.songfinder.data.room.entities.SearchResult

class SearchLocalUseCase(val context: Context) {

    private val localRespository = LocalRespository(context)


    fun saveSearch(search: Search): Long {
        return localRespository.saveSearch(search)
    }

    fun getAllPreviousSearchs(): List<Search> {
        return localRespository.getAllPreviousSearchs()
    }

    fun saveAllResultSearch(listResult: List<SearchResult>) {
        localRespository.saveAllResult(listResult)
    }

    fun getAllResultByIdSearch(idSearch: Int): List<SearchResult> {
        return localRespository.getAllResultByIdSearch(idSearch)
    }
}