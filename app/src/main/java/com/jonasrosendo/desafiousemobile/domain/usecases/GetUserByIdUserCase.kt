package com.jonasrosendo.desafiousemobile.domain.usecases

import com.jonasrosendo.desafiousemobile.domain.repositories.UserRepository

class GetUserByIdUserCase(private val repository: UserRepository) {
    suspend operator fun invoke(id: Long) = repository.getUserById(id)
}