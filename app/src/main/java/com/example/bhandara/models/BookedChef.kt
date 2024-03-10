package com.example.bhandara.models

import android.net.Uri
import com.google.firebase.Timestamp

data class BookedChef (
    val chefName : String? = "",
    val chefMobile: String? = "",
    val chefAddress: String? = "",
    val bookedId : String? = "",
    val userId : String? = "",
    val status : String? = "",
    val chefId : String? = "",
    val eventType : String? = "",
    val noofGuests: String? = "",
    val eventAddress: String? = "",
    val menu : String? = null,
    val mobile : String = "",
    val timestamp: Timestamp = Timestamp.now()
){
    // Custom deserialization method for 'menu'
    fun getMenuUri(): Uri? {
        return menu?.let { Uri.parse(it) }
    }
}
