package com.nakaharadev.roleworldserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication


@SpringBootApplication
@EntityScan
class Server

fun main(args: Array<String>) {
	runApplication<Server>(*args)
}