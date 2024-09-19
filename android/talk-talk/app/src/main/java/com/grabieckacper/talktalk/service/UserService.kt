package com.grabieckacper.talktalk.service

import com.grabieckacper.talktalk.model.User
import com.grabieckacper.talktalk.request.CreateUserRequest

interface UserService {
    suspend fun register(createUserRequest: CreateUserRequest): User
}
