package com.nakaharadev.roleworldserver.database.services

import com.nakaharadev.roleworldserver.database.entities.UserEntity
import com.nakaharadev.roleworldserver.database.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService {
    @Autowired
    private lateinit var repository: UserRepository

    fun save(entity: UserEntity) {
        repository.save(entity)
    }

    fun getAll(): List<UserEntity> {
        return repository.findAll()
    }

    fun findByEmail(email: String): UserEntity? {
        return repository.findByEmail(email)
    }

    fun findById(id: String): Optional<UserEntity> {
        return repository.findById(id)
    }

    fun updateAvatar(userId: String, name: String) {
        repository.saveAvatar(userId, name)
    }

    fun updateNickname(userId: String, value: String) {
        repository.updateNickname(userId, value)
    }

    fun setCharacters(userId: String, value: String) {
        repository.setCharacters(userId, value)
    }
}