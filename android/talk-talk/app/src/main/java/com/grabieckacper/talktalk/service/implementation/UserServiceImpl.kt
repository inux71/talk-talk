package com.grabieckacper.talktalk.service.implementation

import com.grabieckacper.talktalk.common.UrlConstants
import com.grabieckacper.talktalk.model.User
import com.grabieckacper.talktalk.request.CreateUserRequest
import com.grabieckacper.talktalk.service.UserService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class UserServiceImpl @Inject constructor(private val _httpClient: HttpClient) : UserService {
    override suspend fun register(createUserRequest: CreateUserRequest): User {
        return _httpClient.post(UrlConstants.REGISTER) {
            contentType(ContentType.Application.Json)
            setBody(createUserRequest)
        }.body()
    }
}