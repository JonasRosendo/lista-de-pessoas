package com.jonasrosendo.desafiousemobile.data.api

import com.jonasrosendo.desafiousemobile.BuildConfig
import com.jonasrosendo.desafiousemobile.domain.entities.User
import com.jonasrosendo.desafiousemobile.domain.entities.UserResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService : Api {

    private val api = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    override suspend fun getUsers(): UserResponse {
        return api.getUsers()
    }

    override suspend fun getUserById(id: Long): User? {
        return api.getUserById(id)
    }
}