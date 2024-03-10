package com.example.bhandara.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.bhandara.DestinationScreens
import com.example.bhandara.R
import com.example.bhandara.navigation.Food
import com.example.bhandara.navigation.StartNavigation
import com.example.bhandara.ui.theme.CategoryOne
import com.example.bhandara.ui.theme.CategoryTwo

data class Category(
    val id :Int,
    val title :String,
    val subTitle: String,
    @DrawableRes val image :Int,
    val color: Color,
    val destination : String
)
val categoryList = listOf(
    Category(
        1,
        "BHANDARA",
        "Enjoy Bhandara",
        R.drawable.food_app,
        color = CategoryOne,
        DestinationScreens.ScreenFood
    ),
    Category(
        2,
        "DONATION",
        "For Bhandara",
        R.drawable.donation,
        color = CategoryTwo,
        DestinationScreens.ScreenDonation
    ),
    Category(
        3,
        "BOOK CHEF",
        "For Event",
        R.drawable.chef,
        color = CategoryTwo,
        DestinationScreens.ScreenBookChef
    ),
    Category(
        4,
        "SHARE BHANDARA",
        "For Public",
        R.drawable.post_bhandara,
        color = CategoryOne,
        DestinationScreens.ScreenShareBhandara
    ),
)