package com.grabieckacper.talktalk.module

import com.grabieckacper.talktalk.service.AuthenticationService
import com.grabieckacper.talktalk.service.UserService
import com.grabieckacper.talktalk.service.implementation.AuthenticationServiceImpl
import com.grabieckacper.talktalk.service.implementation.UserServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideUserService(httpClient: HttpClient): UserService {
        return UserServiceImpl(httpClient)
    }

    @Provides
    @Singleton
    fun provideAuthenticationService(httpClient: HttpClient): AuthenticationService {
        return AuthenticationServiceImpl(httpClient)
    }
}