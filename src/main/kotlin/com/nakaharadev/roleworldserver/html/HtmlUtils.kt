package com.nakaharadev.roleworldserver.html

import com.nakaharadev.roleworldserver.localize.Localize

object HtmlUtils {
    private fun findAllKeys(page: String): ArrayList<String> {
        val result = ArrayList<String>()

        val regex = Regex("%[a-z]*.*&?.*&?.*%")
        val find = regex.findAll(page)

        for (elem in find) {
            result.add(elem.value)
        }

        return result
    }

    fun replaceAll(html: String, pathVars: Map<String, String?>): String {
        var result = html

        val keys = findAllKeys(html)

        for (key in keys) {
            val split = key.substring(1, key.length - 1).split('&').toMutableList()

            for (i in split.indices) {
                val matchResult = Regex("-pv=.*-").find(split[i])
                if (matchResult != null) {
                    split[i] = pathVars[split[i].substring(1, split[i].length - 1).split('=')[1]] ?: ""
                }
            }

            when(split[0]) {
                "pv" -> {
                    result = result.replace(key, pathVars[split[1]] ?: "")
                }
                "svg" -> {
                    var svg = HtmlLoader.loadSvg("${split[1]}.svg")?.data ?: continue
                    val paramsStr = if (split.size == 4) {
                        "class=\"${split[2]}\" id=\"${split[3]}\""
                    } else {
                        "class=\"${split[2]}\""
                    }

                    svg = svg.replace("<svg", "<svg $paramsStr")
                    result = result.replace(key, svg)
                }
                "loc" -> {
                    result = result.replace(key, Localize.get(pathVars["lang"] ?: "", split[1]))
                }
                "tmpl" -> {
                    result = result.replace(key, replaceAll(HtmlLoader.loadHtml("${split[1]}.html")?.data ?: "", pathVars))
                }
            }
        }

        return result
    }
}