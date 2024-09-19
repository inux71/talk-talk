package com.grabieckacper.talktalk.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Long,
    val email: String,
    @SerializedName("profile_id")
    val profileId: Long
)
