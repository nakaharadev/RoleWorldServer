package com.nakaharadev.roleworldserver

import com.nakaharadev.roleworldserver.html.HtmlLoader
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WebController {
    @GetMapping("/")
    fun rootMapping(): String {
        return HtmlLoader.loadHtml("index.html").data
    }

    @GetMapping("css/{name}")
    fun getCss(@PathVariable name: String): String {
        return HtmlLoader.loadCss(name).data
    }

    @GetMapping("/web/home")
    fun home(): String {
        return "<h1>Home page</h1>"
    }
}