package com.example.bhandara.data


data class PostData(
    val id :Int,
    val title :String,
    val description: String,
)
val postDataList = listOf(
    PostData(
        1,
        "Total Post",
        "100",
    ),
    PostData(
        2,
        "Total Likes",
        "2000",
    ),
    PostData(
        3,
        "Total Comments",
        "200",
    )
)