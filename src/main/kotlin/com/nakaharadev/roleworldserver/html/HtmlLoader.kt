package com.nakaharadev.roleworldserver.html

import java.io.File
import java.io.FileReader

object HtmlLoader {
    private const val TEMPLATES_DIR = "C:\\Users\\user\\Desktop\\RoleWorldServer_rewrite\\src\\main\\resources\\templates"
    private const val CSS_DIR = "C:\\Users\\user\\Desktop\\RoleWorldServer_rewrite\\src\\main\\resources\\static\\css"
    
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
}