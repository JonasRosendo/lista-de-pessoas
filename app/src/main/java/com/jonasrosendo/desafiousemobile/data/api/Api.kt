package com.jonasrosendo.desafiousemobile.data.api

import com.jonasrosendo.desafiousemobile.domain.entities.User
import com.jonasrosendo.desafiousemobile.domain.entities.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("user")
    suspend fun getUsers(): UserResponse

    @GET("user/{id}")
    suspend fun getUserById(@Path("id") id: Long): User?
}