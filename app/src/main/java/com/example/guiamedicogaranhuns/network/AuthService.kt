package com.example.guiamedicogaranhuns.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {

    @POST("users/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}
