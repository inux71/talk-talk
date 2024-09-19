package com.grabieckacper.talktalk.service

import com.grabieckacper.talktalk.request.LoginRequest
import com.grabieckacper.talktalk.response.LoginResponse

interface AuthenticationService {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
}
