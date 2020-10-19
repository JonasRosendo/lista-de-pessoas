package com.jonasrosendo.desafiousemobile.presentation.db.mappers

import com.jonasrosendo.desafiousemobile.domain.entities.User
import com.jonasrosendo.desafiousemobile.presentation.db.UserDao

class RoomRepository(private val dao: UserDao) {
    suspend fun getAllUsersOnLocalDatabase() = dao.getAllUsers()
    suspend fun searchUsersByName(name: String) = dao.searchUsersByName("%$name%")
    suspend fun insertUsers(users: List<User>) = dao.insertUsers(UserMapper.fromUsers(users))
}