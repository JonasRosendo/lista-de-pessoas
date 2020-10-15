package com.jonasrosendo.desafiousemobile.data.repository

import com.jonasrosendo.desafiousemobile.data.datasource.DataSource

class UserRepository(private val dataSource: DataSource) {
    suspend fun getUsers() = dataSource.getUsers()
}