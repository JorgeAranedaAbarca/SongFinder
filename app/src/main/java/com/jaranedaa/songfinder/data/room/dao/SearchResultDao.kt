package com.jaranedaa.songfinder.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jaranedaa.songfinder.data.room.entities.SearchResult

@Dao
interface SearchResultDao {

    @Insert
    fun insertSearchResult(searchResult: SearchResult) : Long

    @Query("SELECT * FROM SearchResult where searchId == :searchId")
    fun getSearchResultByIdSearch(searchId : Int) : List<SearchResult>
}