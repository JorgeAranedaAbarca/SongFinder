package com.jaranedaa.songfinder.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaranedaa.songfinder.domain.usecase.GetAlbumUseCase
import com.jaranedaa.songfinder.domain.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class AlbumViewModel : ViewModel() {

    private val getAlbumUseCase = GetAlbumUseCase()
    private val listResult = MutableLiveData<List<Result>>()


    fun getAlbumByCollectionId(collectionId : String){
        GlobalScope.launch(Dispatchers.Main) {
            listResult.value = getAlbumUseCase.getAlbumByCollectionId(collectionId)
        }
    }

    fun getAlbumLiveData() : LiveData<List<Result>>{
        return listResult
    }

}