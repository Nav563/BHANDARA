package com.example.bhandara.models

import com.google.firebase.Timestamp

data class Post(
    val postImage: String = "",
    val postDescription: String? = "",
    val timestamp: Timestamp = Timestamp.now(),
    val postDate: String = "",
    val postedByID: String = "",
    val postedByName: String = "",
    val phone: String = "",
    val canPublicContact: Boolean = false,
    val userName: String = ""
)