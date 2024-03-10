package com.example.bhandara.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.bhandara.R
import com.example.bhandara.ui.theme.CategoryOne
import com.example.bhandara.ui.theme.CategoryTwo

data class ProfileData(
    val id :Int,
    val title :String,
    val description: String,
    @DrawableRes val image :Int,
    val color: Color
)
val profileDataList = listOf(
    ProfileData(
        1,
        "Name",
        "Navneet Shakya",
        R.drawable.food_app,
        color = CategoryOne
    ),
    ProfileData(
        2,
        "Mobile No.",
        "9456488996",
        R.drawable.donation,
        color = CategoryTwo
    ),
    ProfileData(
        3,
        "Email",
        "navneetshakya94@gmail.com",
        R.drawable.chef,
        color = CategoryTwo
    ),
    ProfileData(
        4,
        "Profession",
        "Mobile Application Developer",
        R.drawable.post_bhandara,
        color = CategoryOne
    ),
    ProfileData(
        5,
        "Bio",
        "I'm Professional Mobile Application Developer",
        R.drawable.post_bhandara,
        color = CategoryOne
    ),
    ProfileData(
        6,
        "Address",
        "17th Cross Rd, Mahadeshwara Nagar, Stage 2, BTM Layout, Bengaluru, Karnataka 560076, India",
        R.drawable.post_bhandara,
        color = CategoryOne
    ),
    ProfileData(
        7,
        "Education",
        "Bachelor of Technology",
        R.drawable.post_bhandara,
        color = CategoryOne
    ),
    ProfileData(
        8,
        "Education Stream",
        "Computer Science And Engineering",
        R.drawable.post_bhandara,
        color = CategoryOne
    ),
    ProfileData(
        9,
        "College",
        "ABSS Institute of Technology Meerut",
        R.drawable.man,
        color = CategoryOne
    )
)