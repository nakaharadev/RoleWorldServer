package com.nakaharadev.roleworldserver.controllers

import com.nakaharadev.roleworldserver.html.HtmlLoader
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths


@RestController
class WebController {
    @GetMapping("/")
    fun rootMapping(): String {
        return HtmlLoader.loadHtml("index.html")?.data ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @GetMapping("css/{name}")
    fun getCss(@PathVariable name: String): String {
        return HtmlLoader.loadCss(name)?.data ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @GetMapping("svg/{name}")
    fun getSvg(@PathVariable name: String): ResponseEntity<String> {
        val headers = HttpHeaders()
        headers[HttpHeaders.CONTENT_TYPE] = "image/svg+xml"
        return ResponseEntity(HtmlLoader.loadSvg(name)?.data ?: throw ResponseStatusException(HttpStatus.NOT_FOUND), headers, HttpStatus.OK)
    }

    @GetMapping("img/{name}")
    fun getImage(@PathVariable name: String): ByteArray {
        return HtmlLoader.loadImage(name)
    }

    @GetMapping("js/{name}", produces = ["text/javascript;charset=UTF-8"])
    fun getJs(@PathVariable name: String): ResponseEntity<String> {
        val pathElements = name.split('-')
        var path = ""
        for (elem in pathElements) {
            path += "$elem/"
        }
        path = path.substring(0, path.length - 1)

        val headers = HttpHeaders()
        headers[HttpHeaders.CONTENT_TYPE] = "text/javascript"
        return ResponseEntity(HtmlLoader.loadJs(path)?.data ?: throw ResponseStatusException(HttpStatus.NOT_FOUND), headers, HttpStatus.OK)
    }

    @GetMapping("video/{name}")
    fun getVideo(@PathVariable name: String): FileSystemResource {
        return FileSystemResource(File("${File(".").absolutePath}\\src\\main\\resources\\web\\static\\video\\$name"))
    }

    @GetMapping("/web/{name}")
    fun getWebTemplate(@PathVariable name: String, @RequestParam("id", required=false) id: String?): ResponseEntity<String> {
        var data = HtmlLoader.loadHtml("$name.html")?.data

        return if (data == null)
            ResponseEntity(HtmlLoader.loadHtml("error/404.html")?.data ?: "", HttpStatus.NOT_FOUND)
        else {
            if (id != null) data = putDataForKey(data, "id", id)
            ResponseEntity(data, HttpStatus.OK)
        }
    }

    @GetMapping("/download/{name}")
    fun downloadFile(@PathVariable name: String): ResponseEntity<Resource> {
        val downloadDir = "${File(".").absolutePath}\\src\\main\\resources\\web\\download_files"

        val file = File("$downloadDir/$name")
        val path = Paths.get(file.absolutePath)
        val byteArrayResource = ByteArrayResource(Files.readAllBytes(path))

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=$name")
            .contentLength(file.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(byteArrayResource)
    }

    fun putDataForKey(htmlData: String, key: String, value: String): String {
        val htmlSplit = htmlData.split('%')
        var result = ""

        for (elem in htmlSplit) {
            if (elem == key)
                result += value
            else result += elem
        }

        return result;
    }
}