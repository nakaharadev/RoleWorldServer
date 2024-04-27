package com.nakaharadev.roleworldserver.database.repositories

import com.nakaharadev.roleworldserver.database.entities.UserEntity
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, String> {
    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    fun findByEmail(@Param("email") email: String): UserEntity?

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.avatar = :name WHERE u.id = :id")
    fun saveAvatar(@Param("id") id: String, @Param("name") name: String)

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.nickname = :value WHERE u.id = :id")
    fun updateNickname(@Param("id") id: String, @Param("value") name: String)

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.characters = :value WHERE u.id = :id")
    fun setCharacters(@Param("id") id: String, @Param("value") name: String)


    @Transactional
    @Modifying
    @Query("UPDATE UserEntity c SET c.showId = :showId WHERE c.id = :id")
    fun updateShowingId(@Param("id") id: String, @Param("showId") showId: String)
}