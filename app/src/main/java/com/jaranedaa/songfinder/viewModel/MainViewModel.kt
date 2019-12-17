package com.jaranedaa.songfinder.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaranedaa.songfinder.domain.model.SearchModel
import com.jaranedaa.songfinder.domain.usecase.SaveSearchLocalUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private lateinit var saveSearchLocal: SaveSearchLocalUseCase
    private val listSearchs = MutableLiveData<List<String>>()


    fun setContext(context: Context) {
        saveSearchLocal = SaveSearchLocalUseCase(context)
    }

    fun saveSearch(searchText: String) {
        GlobalScope.launch(Dispatchers.IO) {
            var searchModel = SearchModel()
            searchModel.text = searchText
            saveSearchLocal.saveSearch(searchModel)
        }
    }


    fun getSearchsLiveData(): LiveData<List<String>> {
        return listSearchs
    }


}