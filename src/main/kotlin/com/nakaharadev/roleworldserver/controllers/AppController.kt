package com.nakaharadev.roleworldserver.controllers

import com.nakaharadev.roleworldserver.database.entities.UserEntity
import com.nakaharadev.roleworldserver.database.services.UserService
import com.nakaharadev.roleworldserver.models.AuthRequest
import com.nakaharadev.roleworldserver.models.AuthResponse
import com.nakaharadev.roleworldserver.models.UpdateRequest
import com.nakaharadev.roleworldserver.models.UpdateResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.UUID

@RestController
@RequestMapping(value = ["/app"], produces = ["application/json"])
class AppController(val service: UserService) {
    @OptIn(ExperimentalStdlibApi::class)
    @PostMapping("/auth/sign_in")
    fun signIn(@RequestBody body: AuthRequest.SignInRequest): AuthResponse? {
        val entity = service.findByEmail(body.email) ?: return AuthResponse(404, "", "")

        if (body.password.hashCode().toString() == entity.password) {
            return AuthResponse(200, entity.id.toHexString(), entity.nickname)
        } else {
            return AuthResponse(506, "", "")
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    @PostMapping("/auth/sign_up")
    fun singUp(@RequestBody body: AuthRequest.SignUpRequest): AuthResponse? {
        for (elem: UserEntity in service.getAll()) {
            if (body.email == elem.email) {
                return AuthResponse(506, "", "")
            }
        }

        val entity = UserEntity(
            0,
            body.nickname,
            body.email,
            body.password.hashCode().toString(),
            ""
        )

        service.save(entity)

        return AuthResponse(200, entity.id.toHexString(), entity.nickname)
    }

    @PostMapping("/update_user/{user_id}/avatar")
    fun updateAvatar(
        @PathVariable("user_id") id: String,
        @RequestParam("avatar") file: MultipartFile
    ): UpdateResponse {
        val path = "C:\\Users\\user\\Desktop\\RoleWorldServer_rewrite\\src\\main\\resources\\users_avatars"
        val avatarsDir = File(path)
        if (!avatarsDir.exists()) {
            avatarsDir.mkdir()
        }

        val filename = UUID.randomUUID().toString() + ".png"

        file.transferTo(File("$path\\$filename"))

        File("$path\\${service.findById(id.toInt()).get().avatar}").delete()

        service.updateAvatar(id.toInt(), filename)

        return UpdateResponse(200)
    }

    @PostMapping("/update_user/{user_id}/nickname")
    fun updateNickname(
        @PathVariable("user_id") id: String,
        @RequestBody body: UpdateRequest
    ): UpdateResponse {
        service.updateNickname(id.toInt(), body.value)

        return UpdateResponse(200)
    }
}