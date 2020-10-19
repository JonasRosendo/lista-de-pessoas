package com.jonasrosendo.desafiousemobile.presentation.db.mappers

import com.jonasrosendo.desafiousemobile.domain.entities.User
import com.jonasrosendo.desafiousemobile.domain.entities.UserResponse

object UserMapper {

    fun fromUsers(users: List<User>): ArrayList<UserEntity> {
        val usersEntity = arrayListOf<UserEntity>()
        users.forEach {
            usersEntity.add(
                UserEntity(
                    id = it.id,
                    name = it.name.toString(),
                    email = it.email.toString(),
                    photo = it.photo.toString(),
                    isVerified = it.isVerified!!
                )
            )
        }
        return usersEntity
    }

    fun toUsers(usersEntity: List<UserEntity>): UserResponse {
        val users = arrayListOf<User>()
        usersEntity.forEach {
            users.add(
                User(
                    id = it.id,
                    name = it.name,
                    email = it.email,
                    photo = it.photo,
                    isVerified = it.isVerified
                )
            )
        }
        return UserResponse(users)
    }
}