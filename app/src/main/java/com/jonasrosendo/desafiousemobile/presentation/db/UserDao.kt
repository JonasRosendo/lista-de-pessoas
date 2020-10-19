package com.jonasrosendo.desafiousemobile.presentation.db

import androidx.room.*
import com.jonasrosendo.desafiousemobile.presentation.db.mappers.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers() : List<UserEntity>

    @Query("SELECT * FROM users WHERE name LIKE :name")
    suspend fun searchUsersByName(name: String) : List<UserEntity>

    @Query("DELETE FROM users")
    suspend fun clearUserTable()
}