package com.jonasrosendo.desafiousemobile.domain.entities


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("result")
    val response: List<User>
)