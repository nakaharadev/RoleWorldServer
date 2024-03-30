package com.nakaharadev.roleworldserver

import com.nakaharadev.roleworldserver.models.AuthRequest
import com.nakaharadev.roleworldserver.models.AuthResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/app"], produces = ["application/json"])
class AppController {
    @PostMapping("/auth/sign_in")
    fun signIn(@RequestBody body: AuthRequest.SignInRequest): AuthResponse? {
        return AuthResponse(200, "12345ff43d")
    }

    @PostMapping("/auth/sign_up")
    fun singUp(@RequestBody body: AuthRequest.SignUpRequest): AuthResponse? {
        return AuthResponse(200, "12345ff43d");
    }
}