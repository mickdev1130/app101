package com.example.app101.core.data

import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}