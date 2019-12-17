package com.jaranedaa.songfinder.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaranedaa.songfinder.data.room.entities.Search
import com.jaranedaa.songfinder.domain.usecase.SearchLocalUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val listResult = MutableLiveData<List<Search>>()
    private lateinit var searchLocalUseCase: SearchLocalUseCase


    fun setContext(context: Context) {
        searchLocalUseCase = SearchLocalUseCase(context)
    }

    fun getAllPreviousSearchs() {
        GlobalScope.launch(Dispatchers.IO) {
            listResult.postValue(searchLocalUseCase.getAllPreviousSearchs())
        }
    }

    fun getSearchsLiveData(): LiveData<List<Search>> {
        return listResult
    }


}