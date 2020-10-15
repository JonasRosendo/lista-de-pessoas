package com.jonasrosendo.desafiousemobile.data.api

import com.jonasrosendo.desafiousemobile.data.model.User
import com.jonasrosendo.desafiousemobile.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface UseMobileApi {

    @GET("user")
    suspend fun getUsers(): UserResponse

    @GET("user/{id}")
    suspend fun getUserById(@Path("id") id: Long): User?
}