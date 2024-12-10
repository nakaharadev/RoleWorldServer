package com.nakaharadev.roleworldserver.database.utils

import com.nakaharadev.roleworldserver.database.services.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service
import java.io.File


@Service
class InitiateUtils(val service: UserService) : CommandLineRunner {
    @Throws(Exception::class)
    override fun run(vararg args: String) {}
}