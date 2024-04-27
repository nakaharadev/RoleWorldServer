package com.nakaharadev.roleworldserver.models

data class AuthResponse(
        var status: Int,
        var userId: String,
        var showId: String,
        var nickname: String,
        var characters: String
)