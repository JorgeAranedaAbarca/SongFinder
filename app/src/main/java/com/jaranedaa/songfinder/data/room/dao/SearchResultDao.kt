package com.jaranedaa.songfinder.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jaranedaa.songfinder.data.room.entities.SearchResult

@Dao
interface SearchResultDao {

    @Insert
    fun insertAllSearchResult(searchResult: List<SearchResult>)

    @Query("SELECT * FROM SearchResult where searchId == :searchId")
    fun getSearchResultByIdSearch(searchId : Int) : List<SearchResult>
}