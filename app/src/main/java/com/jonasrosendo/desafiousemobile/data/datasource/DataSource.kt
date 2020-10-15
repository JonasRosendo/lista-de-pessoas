package com.jonasrosendo.desafiousemobile.data.datasource

import com.jonasrosendo.desafiousemobile.data.model.UserResponse

interface DataSource {
    suspend fun getUsers(): UserResponse
}