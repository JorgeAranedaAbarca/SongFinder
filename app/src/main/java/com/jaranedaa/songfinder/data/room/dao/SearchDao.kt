package com.jaranedaa.songfinder.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jaranedaa.songfinder.data.room.entities.Search

@Dao
interface SearchDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearch(search: Search): Long


    @Query("SELECT * FROM Search")
    fun getPreviousSearchs(): List<Search>

    //update and delete not necessary for now
}