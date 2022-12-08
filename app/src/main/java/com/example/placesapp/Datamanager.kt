package com.example.placesapp

object Datamanager {

    val places = mutableListOf<Place>()

    init {
        createMockData()
    }

    fun createMockData() {
        places.add(Place("Ullådalen", R.drawable.picture_button, R.drawable.pin_button))
        places.add(Place("Kongabodarna", R.drawable.picture_button, R.drawable.pin_button))
        places.add(Place("Runmarö", R.drawable.picture_button, R.drawable.pin_button))
        places.add(Place("Vaxholm", R.drawable.picture_button, R.drawable.pin_button))
        places.add(Place("Killinge", R.drawable.picture_button, R.drawable.pin_button))
        places.add(Place("Ottsjön", R.drawable.picture_button, R.drawable.pin_button))
        places.add(Place("Åre", R.drawable.picture_button, R.drawable.pin_button))
        places.add(Place("Köpenhamn", R.drawable.picture_button, R.drawable.pin_button))
        places.add(Place("Gotland", R.drawable.picture_button, R.drawable.pin_button))

    }
}