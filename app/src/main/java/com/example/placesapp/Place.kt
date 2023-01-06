package com.example.placesapp

import android.widget.ImageView
import com.google.firebase.firestore.DocumentId

data class Place(
    @DocumentId var documentId: String? = null,
    var name: String? = null,
    var info: String? = null,
    var imageURL: String? = null,
    var longitude: Double? = null,
    var latitude: Double? = null
    ) {
}