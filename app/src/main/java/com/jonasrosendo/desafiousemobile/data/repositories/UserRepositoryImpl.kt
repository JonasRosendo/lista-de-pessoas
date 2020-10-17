package com.jonasrosendo.desafiousemobile.data.repositories

import com.jonasrosendo.desafiousemobile.domain.datasources.DataSource
import com.jonasrosendo.desafiousemobile.domain.repositories.UserRepository

class UserRepositoryImpl(private val dataSource: DataSource): UserRepository {
    override suspend fun getUsers() = dataSource.getUsers()
    override suspend fun getUserById(id: Long) = dataSource.getUserById(id)
}