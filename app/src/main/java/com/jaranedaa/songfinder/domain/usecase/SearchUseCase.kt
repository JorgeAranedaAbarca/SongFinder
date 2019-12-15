package com.jaranedaa.songfinder.domain.usecase

import com.jaranedaa.songfinder.data.repository.LocalRespository
import com.jaranedaa.songfinder.domain.model.Result

class SearchUseCase {

    val localRespository = LocalRespository()

    fun saveSearch(search: String) {
        localRespository.saveSearch(search)
    }

    fun getAllSearchs(): MutableList<String> {
        return localRespository.getAllSearchs()
    }

    fun saveSearchResults(results : List<Result>) {
        localRespository.saveSearchResults(results)
    }

    fun getAllResults(): MutableList<Result> {
        return localRespository.getAllSongResult()
    }
}