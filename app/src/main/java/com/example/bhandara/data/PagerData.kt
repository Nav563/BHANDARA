package com.example.bhandara.data

import androidx.annotation.DrawableRes
import com.example.bhandara.R

data class Pager(
    @DrawableRes val image: Int,
    val des: String
)

val dataList = listOf(
    Pager(R.drawable.food_app, "SignIn"),
    Pager(R.drawable.chef, "SignUp"),
    Pager(R.drawable.post_bhandara, "Bhandara App")
)