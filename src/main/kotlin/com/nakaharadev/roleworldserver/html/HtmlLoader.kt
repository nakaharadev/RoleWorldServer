package com.nakaharadev.roleworldserver.html

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileReader


object HtmlLoader {
    private val TEMPLATES_DIR = "${File(".").absolutePath}\\src\\main\\resources\\web\\templates"
    private val CSS_DIR =       "${File(".").absolutePath}\\src\\main\\resources\\web\\static\\css"
    private val IMAGES_DIR =    "${File(".").absolutePath}\\src\\main\\resources\\web\\static\\images"
    private val JS_DIR =        "${File(".").absolutePath}\\src\\main\\resources\\web\\static\\js"
    private val SVG_DIR =       "${File(".").absolutePath}\\src\\main\\resources\\web\\static\\svg"

    private fun loadFile(path: String): HtmlFile? {
        try {
            val reader = FileReader(File(path))
            val file = HtmlFile(reader.readText())

            reader.close()

            return file
        } catch (e: FileNotFoundException) {
            return null
        }
    }

    fun loadHtml(name: String): HtmlFile? {
        return loadFile("$TEMPLATES_DIR\\$name")
    }

    fun loadCss(name: String): HtmlFile? {
        return loadFile("$CSS_DIR\\$name")
    }

    fun loadJs(name: String): HtmlFile? {
        return loadFile("$JS_DIR\\$name")
    }

    fun loadSvg(name: String): HtmlFile? {
        return loadFile("$SVG_DIR\\$name")
    }

    fun loadImage(name: String): ByteArray {
        val reader = FileInputStream(File("$IMAGES_DIR\\$name"))
        val byteArray = reader.readAllBytes()

        reader.close()

        return byteArray
    }
}