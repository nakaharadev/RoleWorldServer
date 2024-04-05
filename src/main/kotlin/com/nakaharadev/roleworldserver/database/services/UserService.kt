package com.nakaharadev.roleworldserver.database.services

import com.nakaharadev.roleworldserver.database.entities.UserEntity
import com.nakaharadev.roleworldserver.database.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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
}