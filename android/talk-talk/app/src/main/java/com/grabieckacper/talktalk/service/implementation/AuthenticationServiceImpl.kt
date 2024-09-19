package com.grabieckacper.talktalk.service.implementation

import com.grabieckacper.talktalk.common.UrlConstants
import com.grabieckacper.talktalk.request.LoginRequest
import com.grabieckacper.talktalk.response.LoginResponse
import com.grabieckacper.talktalk.service.AuthenticationService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class AuthenticationServiceImpl @Inject constructor(
    private val _httpClient: HttpClient
) : AuthenticationService {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
       return _httpClient.post(UrlConstants.LOGIN) {
           contentType(ContentType.Application.Json)
           setBody(loginRequest)
       }.body()
    }
}
