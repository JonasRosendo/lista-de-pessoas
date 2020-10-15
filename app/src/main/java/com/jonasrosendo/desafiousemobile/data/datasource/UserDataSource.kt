package com.jonasrosendo.desafiousemobile.data.datasource

import com.jonasrosendo.desafiousemobile.data.api.UseMobileApi

class UserDataSource(private val api: UseMobileApi): DataSource {
    override suspend fun getUsers() = api.getUsers()
}