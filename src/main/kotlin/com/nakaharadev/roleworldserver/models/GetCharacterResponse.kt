package com.nakaharadev.roleworldserver.models

data class GetCharacterResponse(
    val status: Int,
    val id: String,
    val name: String,
)