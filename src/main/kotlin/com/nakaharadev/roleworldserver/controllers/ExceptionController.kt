package com.nakaharadev.roleworldserver.controllers

import com.nakaharadev.roleworldserver.html.HtmlLoader
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.NoHandlerFoundException

@RestController
class ExceptionController {
    @ExceptionHandler(NoHandlerFoundException::class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    fun notFound(e: NoHandlerFoundException): String {
        return HtmlLoader.loadHtml("error/404.html").data
    }
}