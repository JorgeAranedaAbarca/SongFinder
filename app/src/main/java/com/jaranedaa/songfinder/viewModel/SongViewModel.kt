package com.jaranedaa.songfinder.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jaranedaa.songfinder.data.room.entities.Search
import com.jaranedaa.songfinder.data.room.entities.SearchResult
import com.jaranedaa.songfinder.domain.model.Result
import com.jaranedaa.songfinder.domain.usecase.GetSongUseCase
import com.jaranedaa.songfinder.domain.usecase.SearchLocalUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SongViewModel : ViewModel() {

    private val getSongUseCase = GetSongUseCase()
    private lateinit var searchUseCase: SearchLocalUseCase
    private val listArtist = MutableLiveData<List<Result>>()


    fun setContext(context: Context) {
        searchUseCase = SearchLocalUseCase(context)
    }

    fun getSongByName(name: String) {
        GlobalScope.launch(Dispatchers.Main) {
            listArtist.value = getSongUseCase.getSongByName(name)
        }
    }

    fun getSongsLiveData(): LiveData<List<Result>> {
        return listArtist
    }


    fun saveResults(results: List<Result>, name: String) {
        GlobalScope.launch(Dispatchers.IO) {
            var idSearch = searchUseCase.saveSearch(Search(null, name))
            var listSearchResult: MutableList<SearchResult> = mutableListOf()

            for (result: Result in results) {
                listSearchResult.add(createSearchResult(result, idSearch))
            }

            searchUseCase.saveAllResultSearch(listSearchResult)
        }
    }

    private fun createSearchResult(result: Result, idSearch: Long): SearchResult {//dummy
        return SearchResult(null, idSearch.toInt(),0,result.artistName,"","","","",
            "","",result.collectionId,"",0.0,"","","",0,0,true,
        "","","","","",0,"",0,result.trackName,0,0.0,0,"","")
    }

    fun getResultByIdSearch(idSearch: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            listArtist.postValue(transformListToResult(searchUseCase.getAllResultByIdSearch(idSearch)))
        }
    }

    private fun transformListToResult(allResultByIdSearch: List<SearchResult>): List<Result>? {
        var listResult : MutableList<Result> = mutableListOf()

        for (search : SearchResult in allResultByIdSearch){

            listResult.add(Result(0,search.artistName,"","","","",
                "","",search.collectionId,"",0.0,"","","",0,0,true,
                "","","","","",0,"",0,search.trackName,0,0.0,0,"",""))
        }
        return listResult
    }

}