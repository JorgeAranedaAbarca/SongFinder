package com.jaranedaa.songfinder.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jaranedaa.songfinder.data.room.dao.SearchDao
import com.jaranedaa.songfinder.data.room.dao.SearchResultDao
import com.jaranedaa.songfinder.data.room.entities.Search
import com.jaranedaa.songfinder.data.room.entities.SearchResult

@Database(entities = [Search::class, SearchResult::class], version = 1, exportSchema = false)
abstract class SongFinderDatabase : RoomDatabase() {


    abstract fun SearchDao() : SearchDao
    abstract fun SearchResultDao() : SearchResultDao

    companion object {
        var INSTANCE: SongFinderDatabase? = null

        fun getAppDataBase(context: Context): SongFinderDatabase? {
            if (INSTANCE == null){
                synchronized(SongFinderDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        SongFinderDatabase::class.java, "SongFinder").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }

}