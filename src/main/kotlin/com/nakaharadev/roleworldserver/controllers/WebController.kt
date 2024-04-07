package com.nakaharadev.roleworldserver.controllers

import com.nakaharadev.roleworldserver.html.HtmlLoader
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

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

    @GetMapping("img/{name}")
    fun getImage(@PathVariable name: String): ByteArray {
        return HtmlLoader.loadImage(name)
    }

    @GetMapping("js/{name}")
    fun getJs(@PathVariable name: String): String {
        return HtmlLoader.loadJs(name).data
    }

    @GetMapping("/web/info")
    fun info(): String {
        return HtmlLoader.loadHtml("info.html").data
    }

    @GetMapping("/web/download")
    fun download(): String {
        return HtmlLoader.loadHtml("download.html").data
    }

    @GetMapping("/download/{name}")
    fun downloadFile(@PathVariable name: String): ResponseEntity<Resource> {
        val downloadDir = "C:\\Users\\user\\Desktop\\RoleWorldServer_rewrite\\src\\main\\resources\\download_files"

        val file = File("$downloadDir/$name")
        val path = Paths.get(file.absolutePath)
        val byteArrayResource = ByteArrayResource(Files.readAllBytes(path))

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=$name")
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(byteArrayResource)
    }
}