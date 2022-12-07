package com.example.placesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.placesapp.ui.main.PlacesFragment

class PlacesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places3)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PlacesFragment.newInstance())
                .commitNow()
        }
    }
}