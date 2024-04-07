package com.nakaharadev.roleworldserver.html

import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileReader
import java.io.InputStream


object HtmlLoader {
    private const val TEMPLATES_DIR = "C:\\Users\\user\\Desktop\\RoleWorldServer_rewrite\\src\\main\\resources\\templates"
    private const val CSS_DIR = "C:\\Users\\user\\Desktop\\RoleWorldServer_rewrite\\src\\main\\resources\\static\\css"
    private const val IMAGES_DIR = "C:\\Users\\user\\Desktop\\RoleWorldServer_rewrite\\src\\main\\resources\\static\\images"
    private const val JS_DIR = "C:\\Users\\user\\Desktop\\RoleWorldServer_rewrite\\src\\main\\resources\\static\\js"

    fun loadHtml(name: String): HtmlFile {
        val reader = FileReader(File("$TEMPLATES_DIR\\$name"))
        val file = HtmlFile(reader.readText())

        reader.close()

        return file
    }

    fun loadCss(name: String): HtmlFile {
        val reader = FileReader(File("$CSS_DIR\\$name"))
        val file = HtmlFile(reader.readText())

        reader.close()

        return file
    }

    fun loadJs(name: String): HtmlFile {
        val reader = FileReader(File("$JS_DIR\\$name"))
        val file = HtmlFile(reader.readText())

        reader.close()

        return file
    }

    fun loadImage(name: String): ByteArray {
        val reader = FileInputStream(File("$IMAGES_DIR\\$name"))
        val byteArray = reader.readAllBytes()

        reader.close()

        return byteArray
    }
}