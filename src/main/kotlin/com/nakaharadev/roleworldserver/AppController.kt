package com.nakaharadev.roleworldserver

import com.nakaharadev.roleworldserver.database.entities.UserEntity
import com.nakaharadev.roleworldserver.database.services.UserService
import com.nakaharadev.roleworldserver.models.AuthRequest
import com.nakaharadev.roleworldserver.models.AuthResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/app"], produces = ["application/json"])
class AppController(val service: UserService) {


    @PostMapping("/auth/sign_in")
    fun signIn(@RequestBody body: AuthRequest.SignInRequest): AuthResponse? {
        return AuthResponse(200, "")
    }

    @OptIn(ExperimentalStdlibApi::class)
    @PostMapping("/auth/sign_up")
    fun singUp(@RequestBody body: AuthRequest.SignUpRequest): AuthResponse? {
        for (elem: UserEntity in service.getAll()) {
            if (body.email == elem.email) {
                return AuthResponse(506, "")
            }
        }

        val entity = UserEntity(
            0,
            body.nickname,
            body.email,
            body.password.hashCode().toString()
        )

        service.save(entity)

        return AuthResponse(200, entity.id.toHexString())
    }
}