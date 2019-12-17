package com.jaranedaa.songfinder.domain.usecase

import android.content.Context
import com.jaranedaa.songfinder.data.repository.LocalRespository
import com.jaranedaa.songfinder.data.room.entities.Search
import com.jaranedaa.songfinder.domain.model.SearchModel

class SaveSearchLocalUseCase(val context: Context) {

    private val localRespository = LocalRespository(context)


    fun saveSearch(searchModel: SearchModel){
        val search = Search(searchModel.id, searchModel.text)
        localRespository.saveSearch(search)
    }


}