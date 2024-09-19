package com.grabieckacper.talktalk.common

object UrlConstants {
    private const val BASE_URL: String = "http://10.0.2.2:8080/api"
    const val REGISTER: String = "$BASE_URL/user/register"
    const val LOGIN: String = "$BASE_URL/auth/login"
}