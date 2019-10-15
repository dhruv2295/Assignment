package com.firebot.assignment.service.repository

import com.firebot.assignment.service.model.Project
import retrofit2.Call
import retrofit2.http.GET

interface APIService {


    @GET("/repositories")
    fun getRepositories(): Call<List<Project>>

}