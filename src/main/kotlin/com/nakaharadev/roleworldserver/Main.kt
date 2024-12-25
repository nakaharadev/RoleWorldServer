package com.nakaharadev.roleworldserver

import com.nakaharadev.roleworldserver.localize.Localize
import org.springframework.boot.runApplication

fun main(args: Array<String>) {
    Localize.init()
    runApplication<Server>(*args)
}