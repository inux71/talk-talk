package com.grabieckacper.talktalk.request

import com.google.gson.annotations.SerializedName

data class CreateUserRequest(
    val email: String,
    val password: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String
)
