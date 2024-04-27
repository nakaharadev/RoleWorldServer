package com.nakaharadev.roleworldserver.models

data class GetCharacterResponse(
    val status: Int,
    val id: String,
    val name: String,
    val sex: String,
    val desc: String,
    val bio: String
)