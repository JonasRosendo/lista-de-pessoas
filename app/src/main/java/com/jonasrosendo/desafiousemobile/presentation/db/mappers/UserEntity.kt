package com.jonasrosendo.desafiousemobile.presentation.db.mappers

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String,
    var email: String,
    var photo: String,
    var isVerified: Boolean = false
)