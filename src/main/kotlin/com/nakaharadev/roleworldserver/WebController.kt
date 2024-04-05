package com.nakaharadev.roleworldserver

import com.nakaharadev.roleworldserver.html.FileLoader
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(produces = ["text/html"])
class WebController {
    @GetMapping("/")
    fun rootMapping(): String {
        return FileLoader.load("index.html").data
    }

    @GetMapping("/web/home")
    fun home(): String {
        return "<h1>Home page</h1>"
    }
}