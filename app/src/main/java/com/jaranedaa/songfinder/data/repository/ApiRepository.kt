package com.jaranedaa.songfinder.data.repository

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jaranedaa.songfinder.BuildConfig
import kotlinx.coroutines.Deferred
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

open class ApiRepository {
    suspend fun <T> makeRequest(request: () -> Deferred<Response<T>>): T {
        try {
            val response = request().await()
            if (response.isSuccessful) {
                return response.body()!!
            } else {
                throw Exception("HttpError: ${response.errorBody()!!.string()}")
            }
        } catch (ex: HttpException) {
            throw Exception("HttpException: ${ex.message}")
        } catch (ex: Exception) {
            throw Exception("${ex.message}")
        }
    }


}