package com.example.placesapp

object Datamanager {

    val places = mutableListOf<Place>()

    init {
        createMockData()
    }

    fun createMockData() {
        places.add(Place("Ullådalen","lite info"))
        places.add(Place("Kongabodarna","lite info"))
        places.add(Place("Runmarö","lite info"))
        places.add(Place("Vaxholm","lite info"))
        places.add(Place("Killinge","lite info"))
        places.add(Place("Ottsjön","lite info"))
        places.add(Place("Åre","lite info"))
        places.add(Place("Köpenhamn","lite info"))
        places.add(Place("Gotland","lite info"))

    }
}