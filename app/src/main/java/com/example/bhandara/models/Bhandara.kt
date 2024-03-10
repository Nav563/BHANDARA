package com.example.bhandara.models

import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint
import java.io.Serializable

data class Bhandara(
    val bhandaraImage: String = "",
    val bhandaraAddress: String? = "",
    val bhandaraLocation: String = "",
    val bhandaraDate: String = "",
    val postDate: String = "",
    val postedByID: String = "",
    val postedByName: String = "",
    val bhandaraDay: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val timestamp: Timestamp = Timestamp.now(),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val zoom: Float = DEFAULT_ZOOM_LEVEL,
    val location: GeoPoint = GeoPoint(0.0, 0.0)
) : Serializable {
    companion object {
        const val DEFAULT_ZOOM_LEVEL: Float = 15f
    }
}