package com.nakaharadev.roleworldserver.database.entities

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    var id: String,
    @Column(name = "nickname")
    var nickname: String,
    @Column(name = "email")
    var email: String,
    @Column(name = "password")
    var password: String,
    @Column(name = "avatar")
    val avatar: String,
    @Column(name = "characters")
    val characters: String
) {
    constructor() : this("", "", "", "", "", "")
    override fun toString(): String {
        return "UserEntity{\n" +
                "\tid='$id'\n" +
                "\tnickname='$nickname'\n" +
                "\temail='$email'\n" +
                "\tpassword='$password'\n" +
                "\tavatar='$avatar'\n" +
                "\tcharacters=$characters\n" +
                "}"
    }
}
