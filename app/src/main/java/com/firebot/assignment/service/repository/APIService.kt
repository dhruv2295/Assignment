package com.firebot.assignment.service.repository

import android.util.Log
import com.firebot.assignment.service.model.Project
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val TAG = "APIService"

fun searchRepos(
    service: APIService,
    onSuccess: (repos: List<Project>) -> Unit,
    onError: (error: String) -> Unit
) {
//    Log.d(TAG, "query: $query, page: $page, itemsPerPage: $itemsPerPage")

    service.getRepositories().enqueue(
        object : Callback<List<Project>> {
            override fun onFailure(call: Call<List<Project>>?, t: Throwable) {
                Log.d(TAG, "fail to get data")
                onError(t.message ?: "unknown error")
            }

            override fun onResponse(
                call: Call<List<Project>>?,
                response: Response<List<Project>>
            ) {
                Log.d(TAG, "got a response $response")
                if (response.isSuccessful) {
                    val repos = response.body() ?: emptyList()
                    onSuccess(repos)
                } else {
                    onError(response.errorBody()?.string() ?: "Unknown error")
                }
            }
        }
    )
}

interface APIService {


    @GET("/repositories")
    fun getRepositories(): Call<List<Project>>

    companion object {
        private const val BASE_URL = "https://github-trending-api.now.sh/"

        fun create(): APIService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        }
    }
}