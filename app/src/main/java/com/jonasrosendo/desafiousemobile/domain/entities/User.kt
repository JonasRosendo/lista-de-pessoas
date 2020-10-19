package com.jonasrosendo.desafiousemobile.domain.entities

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
    val isVerified: Boolean?,
    @SerializedName("about")
    val about: String? = ""
)