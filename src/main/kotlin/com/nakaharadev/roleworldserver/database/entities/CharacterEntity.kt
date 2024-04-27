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
    var avatar: String = "",

    @Column(name = "sex")
    var sex: String = "",

    @Column(name = "description", length = 5000)
    var description: String = "",

    @Column(name = "bio", length = 5000)
    var bio: String = "",
) {
    constructor() : this("", "", "", "", "", "")

    override fun toString(): String {
        return "CharacterEntity {\n" +
                "\tid='$id'\n" +
                "\tavatar='$avatar'\n" +
                "\tsex='$sex'\n" +
                "\tdescription='$description'\n" +
                "\tbio='$bio'\n" +
                "}"
    }
}
