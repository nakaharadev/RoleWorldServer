package com.nakaharadev.roleworldserver.database.entities

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator

@Entity
@Table(name = "users")
data class UserEntity(
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    var id: Int,
    @Column(name = "nickname")
    var nickname: String,
    @Column(name = "email")
    var email: String,
    @Column(name = "password")
    var password: String
) {
    constructor() : this(0, "", "", "")
    override fun toString(): String {
        return "UserEntity{\n" +
                "\tid='$id'\n" +
                "\tnickname='$nickname'\n" +
                "\temail='$email'\n" +
                "\tpassword='$password'\n" +
                "}"
    }
}
