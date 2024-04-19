package com.nakaharadev.roleworldserver.database.entities

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "characters")
data class CharacterEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    var id: String,
    @Column(name = "nickname")
    var name: String,
    @Column(name = "avatar")
    val avatar: String
) {
    constructor() : this("", "", "")
    override fun toString(): String {
        return "CharacterEntity {\n" +
                "\tid='$id'\n" +
                "\tavatar='$avatar'\n" +
                "}"
    }
}
