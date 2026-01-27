package com.example.guiamedicogaranhuns.network

data class LoginRequest(
    val login: String,
    val senha: String
)

data class LoginResponse(
    val mensagem: String,
    val user: User?
)

data class User(
    val id: Int,
    val login: String
)
