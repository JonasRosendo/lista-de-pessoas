package com.jonasrosendo.desafiousemobile.data.mocked

import com.jonasrosendo.desafiousemobile.data.api.UseMobileApi
import com.jonasrosendo.desafiousemobile.data.model.User
import com.jonasrosendo.desafiousemobile.data.model.UserResponse

object MockApiService : UseMobileApi {

    override suspend fun getUsers(): UserResponse {
        return UserResponse(generateUsers())
    }

    override suspend fun getUserById(id: Long): User? {
        return searchUserById(id)
    }

    private fun generateUsers(): List<User> {
        return arrayListOf(
            User(
                1,
                "Amanda Campos",
                "amanda@gmail.com",
                "https://lh3.googleusercontent.com/proxy/bvYesA4SRTF0FC0UC2uDX0whxjjfDWJEqMmd188TXqqr_fGfa_e5f7DNc5gLCmfYNCHaPB1vlkmoszpoUvkY7A",
                true
            ),
            User(
                2,
                "Fernanda Almeida",
                "fernanda@gmail.com",
                "https://www.w3schools.com/w3images/avatar6.png",
                false
            )
        )
    }

    private fun searchUserById(id: Long): User? {
        return generateUsers().first { it.id == id }
    }
}