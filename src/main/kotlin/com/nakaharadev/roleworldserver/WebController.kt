package com.nakaharadev.roleworldserver

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WebController {
    @GetMapping("/")
    fun rootMapping(): String {
        return "<h1>Hello, world</h1>"
    }

    @GetMapping("/web/home")
    fun home(): String {
        return "<h1>Home page</h1>"
    }
}