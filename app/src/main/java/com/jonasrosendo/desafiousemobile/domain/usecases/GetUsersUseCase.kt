package com.jonasrosendo.desafiousemobile.domain.usecases

import com.jonasrosendo.desafiousemobile.domain.repositories.UserRepository

class GetUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.getUsers()
}