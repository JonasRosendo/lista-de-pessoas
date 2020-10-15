package com.jonasrosendo.desafiousemobile.usecases

import com.jonasrosendo.desafiousemobile.data.repository.UserRepository

class InteractorGetUsers(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.getUsers()
}