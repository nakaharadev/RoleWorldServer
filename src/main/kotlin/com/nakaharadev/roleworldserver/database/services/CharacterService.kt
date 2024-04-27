package com.nakaharadev.roleworldserver.database.services

import com.nakaharadev.roleworldserver.database.entities.CharacterEntity
import com.nakaharadev.roleworldserver.database.repositories.CharacterRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class CharacterService(val repository: CharacterRepository) {
    fun save(entity: CharacterEntity) {
        repository.save(entity)
    }

    fun findById(id: String): Optional<CharacterEntity> {
        return repository.findById(id)
    }

    fun findByName(name: String): CharacterEntity {
        return repository.findByName(name)
    }

    fun updateName(id: String, name: String) {
        repository.updateName(id, name)
    }

    fun updateDescription(id: String, desc: String) {
        repository.updateDescription(id, desc)
    }

    fun updateBio(id: String, bio: String) {
        repository.updateBio(id, bio)
    }

    fun updateAvatar(id: String, avatar: String) {
        repository.updateAvatar(id, avatar)
    }

    fun updateSex(id: String, sex: String) {
        repository.updateSex(id, sex)
    }
}