package com.jonasrosendo.desafiousemobile.data.datasource

import com.jonasrosendo.desafiousemobile.data.api.Api
import com.jonasrosendo.desafiousemobile.domain.datasources.DataSource

class UserDataSource(private val api: Api): DataSource {
    override suspend fun getUsers() = api.getUsers()
    override suspend fun getUserById(id: Long) = api.getUserById(id)
}