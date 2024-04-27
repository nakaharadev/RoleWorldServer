package com.nakaharadev.roleworldserver.controllers

import com.nakaharadev.roleworldserver.database.entities.CharacterEntity
import com.nakaharadev.roleworldserver.database.entities.UserEntity
import com.nakaharadev.roleworldserver.database.services.CharacterService
import com.nakaharadev.roleworldserver.database.services.UserService
import com.nakaharadev.roleworldserver.models.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileInputStream
import java.util.*


@RestController
@RequestMapping(value = ["/app"], produces = ["application/json"])
class AppController(val userService: UserService, val characterService: CharacterService) {
    @PostMapping("/auth/sign_in")
    fun signIn(@RequestBody body: AuthRequest.SignInRequest): AuthResponse? {
        val entity = userService.findByEmail(body.email) ?: return AuthResponse(404, "", "", "", "")

        return if (body.password.hashCode().toString() == entity.password) {
            AuthResponse(200, entity.id, entity.showId, entity.nickname, entity.characters)
        } else {
            AuthResponse(506, "", "", "", "")
        }
    }

    @PostMapping("/auth/sign_up")
    fun singUp(@RequestBody body: AuthRequest.SignUpRequest): AuthResponse? {
        for (elem: UserEntity in userService.getAll()) {
            if (body.email == elem.email) {
                return AuthResponse(506, "", "", "", "")
            }
        }

        var entity = UserEntity(
            "",
            "",
            body.nickname,
            body.email,
            body.password.hashCode().toString(),
            "",
            ""
        )

        userService.save(entity)

        entity = userService.findByEmail(body.email)!!

        return AuthResponse(200, entity.id, entity.showId, entity.nickname, entity.characters)
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

        File("$path\\${userService.findById(id).get().avatar}").delete()

        userService.updateAvatar(id, filename)

        return UpdateResponse(200)
    }

    @PostMapping("/update_user/{user_id}/show_id")
    fun updateShowId(@PathVariable("user_id") id: String, @RequestBody value: UpdateRequest): UpdateResponse {
        val users = userService.getAll()
        val newShowId = value.value

        for (user in users) {
            if (user.showId == newShowId || user.id == newShowId) {
                return UpdateResponse(409)
            }
        }

        userService.updateShowId(id, value.value)
        return UpdateResponse(200)
    }


    @PostMapping("/update_user/{user_id}/add_character/{name}")
    fun addCharacter(
        @PathVariable("user_id") id: String,
        @PathVariable("name") characterName: String,
        @RequestParam("character_avatar") file: MultipartFile
    ): AddResponse {
        val path = "C:\\Users\\user\\Desktop\\RoleWorldServer_rewrite\\src\\main\\resources\\characters_avatars"
        val avatarsDir = File(path)
        if (!avatarsDir.exists()) {
            avatarsDir.mkdir()
        }

        val filename = UUID.randomUUID().toString() + ".png"

        file.transferTo(File("$path\\$filename"))

        var entity = CharacterEntity(
            "",
            characterName,
            filename,
            "",
            "",
            "",
        )

        characterService.save(entity)

        entity = characterService.findByName(characterName)

        val user = userService.findById(id)
        var characters = user.get().characters
        if (characters.isEmpty()) {
            characters = entity.id
        } else {
            characters += " ${entity.id}"
        }

        userService.setCharacters(user.get().id, characters)

        return AddResponse(200, entity.id)
    }

    @PostMapping("/update_user/{user_id}/nickname")
    fun updateNickname(
        @PathVariable("user_id") id: String,
        @RequestBody body: UpdateRequest
    ): UpdateResponse {
        userService.updateNickname(id, body.value)

        return UpdateResponse(200)
    }

    @PostMapping("/update_character/{id}/name")
    fun updateCharacterName(
        @PathVariable("id") id: String,
        @RequestBody body: UpdateRequest
    ): UpdateResponse {
        characterService.updateName(id, body.value)

        return UpdateResponse(200)
    }

    @PostMapping("/update_character/{id}/sex")
    fun updateCharacterSex(
        @PathVariable("id") id: String,
        @RequestBody body: UpdateRequest
    ): UpdateResponse {
        characterService.updateSex(id, body.value)

        return UpdateResponse(200)
    }

    @PostMapping("/update_character/{id}/desc")
    fun updateCharacterDesc(
        @PathVariable("id") id: String,
        @RequestBody body: UpdateRequest
    ): UpdateResponse {
        characterService.updateDescription(id, body.value)

        return UpdateResponse(200)
    }

    @PostMapping("/update_character/{id}/bio")
    fun updateCharacterBio(
        @PathVariable("id") id: String,
        @RequestBody body: UpdateRequest
    ): UpdateResponse {
        characterService.updateBio(id, body.value)

        return UpdateResponse(200)
    }

    @PostMapping("/update_character/{id}/avatar")
    fun updateCharacterAvatar(
        @PathVariable("id") id: String,
        @RequestParam("avatar") file: MultipartFile
    ): UpdateResponse {
        val path = "C:\\Users\\user\\Desktop\\RoleWorldServer_rewrite\\src\\main\\resources\\characters_avatars"
        val avatarsDir = File(path)
        if (!avatarsDir.exists()) {
            avatarsDir.mkdir()
        }

        val filename = UUID.randomUUID().toString() + ".png"

        file.transferTo(File("$path\\$filename"))

        File("$path\\${characterService.findById(id).get().avatar}").delete()

        characterService.updateAvatar(id, filename)

        return UpdateResponse(200)
    }

    @GetMapping("/get_user_data/{user_id}/avatar")
    fun getAvatar(@PathVariable("user_id") id: String): ByteArray {
        val path = "C:\\Users\\user\\Desktop\\RoleWorldServer_rewrite\\src\\main\\resources\\users_avatars"

        val user = userService.findById(id)

        val reader = FileInputStream(File("${path}\\${user.get().avatar}"))
        val byteArray = reader.readAllBytes()

        reader.close()

        return byteArray
    }

    @GetMapping("/get_user_data/{user_id}/characters")
    fun getCharacters(@PathVariable("user_id") id: String): GetCharactersResponse {
        val user = userService.findById(id)

        val characters = user.get().characters

        return GetCharactersResponse(characters)
    }

    @GetMapping("/get_character/{id}")
    fun getCharacter(@PathVariable("id") id: String): GetCharacterResponse {
        val character = characterService.findById(id).get()

        return GetCharacterResponse(200, character.id, character.name, character.sex, character.description, character.bio)
    }

    @GetMapping("/get_character/avatar/{id}")
    fun getCharacterAvatar(@PathVariable("id") id: String): ByteArray {
        val path = "C:\\Users\\user\\Desktop\\RoleWorldServer_rewrite\\src\\main\\resources\\characters_avatars"

        val character = characterService.findById(id)

        val reader = FileInputStream(File("${path}\\${character.get().avatar}"))
        val byteArray = reader.readAllBytes()

        reader.close()

        return byteArray
    }
}