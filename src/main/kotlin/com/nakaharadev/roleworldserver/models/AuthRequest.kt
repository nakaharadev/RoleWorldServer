package com.nakaharadev.roleworldserver.models


object AuthRequest {
    class SignInRequest {
        lateinit var email: String
        lateinit var password: String
    }

    class SignUpRequest {
        lateinit var nickname: String
        lateinit var email: String
        lateinit var password: String
    }
}