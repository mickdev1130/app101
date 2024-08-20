package com.example.app101.core.repository

import com.example.app101.core.data.LoginRequest
import com.example.app101.core.data.LoginResponse
import com.example.app101.core.data.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiService: LoginService) {

    suspend fun loginUser(email: String?, password: String?): LoginResponse {
        return if (email == "hello@gmail.com" && password == "world123") {
            LoginResponse(success = true, message = "Login successful")
        } else {
            LoginResponse(success = false, message = "Invalid email or password")
        }

        /*return try {
            apiService.login(LoginRequest(email ?: "", password  ?: ""))
        } catch (e: Exception) {
            LoginResponse(success = false, message = "Login failed: ${e.message}")
        }*/
    }


}
