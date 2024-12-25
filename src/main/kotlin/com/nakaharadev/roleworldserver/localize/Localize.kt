package com.nakaharadev.roleworldserver.localize

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.File
import java.io.FileReader

object Localize {
    private val languagesMap = HashMap<String, JsonObject>()

    fun init() {
        val configs = File("${File(".").absolutePath}\\src\\main\\resources\\conf\\languages")

        for (conf in configs.listFiles()!!) {
            val reader = FileReader(conf)
            val file = reader.readText()

            languagesMap[conf.name.split('.')[0]] = JsonParser().parse(file).asJsonObject

            reader.close()
        }
    }

    fun getAll(lang: String): JsonObject {
        return languagesMap[lang] ?: languagesMap["en"] ?: JsonObject()
    }

    fun get(lang: String, key: String): String {
        return getAll(lang)[key]?.asString ?: ""
    }
}