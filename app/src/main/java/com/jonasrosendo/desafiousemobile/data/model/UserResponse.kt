package com.jonasrosendo.desafiousemobile.data.model


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("result")
    val response: List<User>
)