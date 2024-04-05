package com.nakaharadev.roleworldserver.html

import java.io.File
import java.io.FileReader

object FileLoader {
    private const val TEMPLATES_DIR = "C:\\Users\\user\\Desktop\\RoleWorldServer_rewrite\\src\\main\\resources\\templates"
    fun load(name: String): HtmlFile {
        val reader = FileReader(File("$TEMPLATES_DIR\\$name"))
        val file = HtmlFile(reader.readText())

        reader.close()

        return file
    }
}