package com.example.bhandara.models

import com.google.firebase.Timestamp

data class Chef(
    val chefImage: String = "",
    val chefName: String = "",
    val chefAddress: String? = "",
    val chefMobile: String? = "",
    val chefEmail: String = "",
    val timestamp: Timestamp = Timestamp.now(),
)
