package com.jonasrosendo.desafiousemobile.domain.datasources

import com.jonasrosendo.desafiousemobile.domain.entities.User
import com.jonasrosendo.desafiousemobile.domain.entities.UserResponse

interface DataSource {
    suspend fun getUsers(): UserResponse
    suspend fun getUserById(id: Long): User?
}