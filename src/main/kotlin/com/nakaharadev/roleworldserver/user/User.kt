package com.nakaharadev.roleworldserver.user

data class User(
        var id: String?,
        var nickname: String?,
        var email: String,
        var password: String
)