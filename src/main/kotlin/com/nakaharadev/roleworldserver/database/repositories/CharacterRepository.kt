package com.nakaharadev.roleworldserver.database.repositories

import com.nakaharadev.roleworldserver.database.entities.CharacterEntity
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CharacterRepository : CrudRepository<CharacterEntity, String> {
    @Query("SELECT c FROM CharacterEntity c WHERE c.name = :name")
    fun findByName(@Param("name") name: String): CharacterEntity

    @Transactional
    @Modifying
    @Query("UPDATE CharacterEntity c SET c.name = :name WHERE c.id = :id")
    fun updateName(@Param("id") id: String, @Param("name") name: String)

    @Transactional
    @Modifying
    @Query("UPDATE CharacterEntity c SET c.description = :desc WHERE c.id = :id")
    fun updateDescription(@Param("id") id: String, @Param("desc") desc: String)

    @Transactional
    @Modifying
    @Query("UPDATE CharacterEntity c SET c.bio = :bio WHERE c.id = :id")
    fun updateBio(@Param("id") id: String, @Param("bio") bio: String)

    @Transactional
    @Modifying
    @Query("UPDATE CharacterEntity c SET c.showId = :showId WHERE c.id = :id")
    fun updateShowingId(@Param("id") id: String, @Param("showId") showId: String)

    @Transactional
    @Modifying
    @Query("UPDATE CharacterEntity c SET c.avatar = :avatar WHERE c.id = :id")
    fun updateAvatar(@Param("id") id: String, @Param("avatar") avatar: String)

    @Transactional
    @Modifying
    @Query("UPDATE CharacterEntity c SET c.sex = :sex WHERE c.id = :id")
    fun updateSex(@Param("id") id: String, @Param("sex") sex: String)
}