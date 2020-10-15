package com.jonasrosendo.desafiousemobile.data.datasource

import com.jonasrosendo.desafiousemobile.data.api.Api

class UserDataSource(private val api: Api): DataSource {
    override suspend fun getUsers() = api.getUsers()
}