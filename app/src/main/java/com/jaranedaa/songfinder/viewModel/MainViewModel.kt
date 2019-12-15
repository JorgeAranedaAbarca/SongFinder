package com.jaranedaa.songfinder.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaranedaa.songfinder.domain.model.Result
import com.jaranedaa.songfinder.domain.usecase.GetSongUseCase
import com.jaranedaa.songfinder.domain.usecase.SearchUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    private val searchUseCase = SearchUseCase()
    private val listSearchs = MutableLiveData<List<String>>()


    fun saveSearch(search : String){
        searchUseCase.saveSearch(search)
    }


    fun getSearchsLiveData(): LiveData<List<String>> {
        return listSearchs
    }


}