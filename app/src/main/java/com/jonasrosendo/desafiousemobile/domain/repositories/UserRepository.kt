package com.jonasrosendo.desafiousemobile.domain.repositories

import com.jonasrosendo.desafiousemobile.domain.entities.User
import com.jonasrosendo.desafiousemobile.domain.entities.UserResponse

interface UserRepository {
    suspend fun getUsers(): UserResponse
    suspend fun getUserById(id: Long): User?
}