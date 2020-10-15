package com.jonasrosendo.desafiousemobile.data.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("isVerified")
    val isVerified: Boolean?
)