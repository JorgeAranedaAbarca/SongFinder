package com.jaranedaa.songfinder.data.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jaranedaa.songfinder.BuildConfig
import com.jaranedaa.songfinder.domain.model.SongResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface iTunesApi {

    @GET("search")
    fun getSongByName(@Query("term")term: String, @Query("limit") limit : String) : Deferred<Response<SongResponse>>



    companion object ApiBuilder{
        private val URL = BuildConfig.URL_ITUNES

        fun create() : iTunesApi{
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()

            return retrofit.create(iTunesApi::class.java)
        }
    }
}