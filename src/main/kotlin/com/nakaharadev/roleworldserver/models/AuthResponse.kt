package com.nakaharadev.roleworldserver.models

data class AuthResponse(
        var status: Int,
        var userId: String,
        var nickname: String
)