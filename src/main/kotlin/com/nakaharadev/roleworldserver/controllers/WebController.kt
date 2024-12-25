package com.nakaharadev.roleworldserver.controllers

import com.google.gson.GsonBuilder
import com.nakaharadev.roleworldserver.html.HtmlLoader
import com.nakaharadev.roleworldserver.html.HtmlUtils
import com.nakaharadev.roleworldserver.localize.Localize
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
    fun getCss(@PathVariable name: String, @RequestParam("theme", required=false) theme: String?): String {
        val params = HashMap<String, String?>()
        params["theme"] = theme
        return HtmlUtils.replaceAll(HtmlLoader.loadCss(name)?.data ?: throw ResponseStatusException(HttpStatus.NOT_FOUND), params)
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
    fun getWebTemplate(@PathVariable name: String, @RequestParam("id", required=false) id: String?, @RequestParam("lang", required=false) lang: String?, @RequestParam("sex", required=false) sex: String?): ResponseEntity<String> {
        val data = HtmlLoader.loadHtml("$name.html")?.data

        return if (data == null)
            ResponseEntity(HtmlLoader.loadHtml("error/404.html")?.data ?: "", HttpStatus.NOT_FOUND)
        else {
            val varsMap = HashMap<String, String?>()
            varsMap["id"] = id
            varsMap["lang"] = lang
            varsMap["sex"] = sex
            ResponseEntity(HtmlUtils.replaceAll(data, varsMap), HttpStatus.OK)
        }
    }

    @GetMapping("/web/locale")
    fun getLocaleValues(@RequestParam("lang", required=true) lang: String, @RequestParam("keys", required=true) keys: String): String {
        val result = HashMap<String, String>()

        if (keys == "*") {
            return Localize.getAll(lang).toString()
        } else {
            for (key in keys.split('1')) {
                result[key] = Localize.get(lang, key)
            }
        }

        return GsonBuilder().create().toJson(result);
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
}