package com.nakaharadev.roleworldserver.database.repositories

import com.nakaharadev.roleworldserver.database.entities.CharacterEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CharacterRepository : CrudRepository<CharacterEntity, String> {
    @Query("SELECT c FROM CharacterEntity c WHERE c.name = :name")
    fun findByName(@Param("name") name: String): CharacterEntity
}