package com.jaranedaa.songfinder.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaranedaa.songfinder.domain.model.Result
import com.jaranedaa.songfinder.domain.usecase.GetSongUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SongViewModel: ViewModel() {

    private val getSongUseCase = GetSongUseCase()
    private val listArtist = MutableLiveData<List<Result>>()

    fun getSongByName(name: String){
        GlobalScope.launch(Dispatchers.Main) {
            listArtist.value = getSongUseCase.getSongByName(name)
        }
    }

    fun getSongsLiveData(): LiveData<List<Result>> {
        return listArtist
    }


}