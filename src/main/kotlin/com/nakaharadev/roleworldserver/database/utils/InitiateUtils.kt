package com.nakaharadev.roleworldserver.database.utils

import com.nakaharadev.roleworldserver.database.entities.UserEntity
import com.nakaharadev.roleworldserver.database.services.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service


@Service
class InitiateUtils(val service: UserService) : CommandLineRunner {
    @Throws(Exception::class)
    //переопределяем метод который позволит
    //нам выполнять методы нашего приложения при запуске
    override fun run(vararg args: String) {}
}