package com.example.bhandara.models

data class User(
    val userImage: String? = "",
    val name: String = "",
    val mobile: String = "",
    val email: String = "",
    val password: String = "",
    val punyaCoins: Int? = 0,
    val profession: String = "",
    val bio: String = "",
)

