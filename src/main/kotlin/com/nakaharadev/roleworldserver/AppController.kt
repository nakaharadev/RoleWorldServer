package com.nakaharadev.roleworldserver

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/app"], produces = ["application/json"])
class AppController {
    @GetMapping("/request")
    fun request(@RequestParam("req") req: String): String {
        return "{\"status\":\"${req}_200\"}"
    }
}