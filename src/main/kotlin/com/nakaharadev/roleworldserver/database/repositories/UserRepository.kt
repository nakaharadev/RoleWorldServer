package com.nakaharadev.roleworldserver.database.repositories

import com.nakaharadev.roleworldserver.database.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Int>