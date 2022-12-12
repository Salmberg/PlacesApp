package com.example.placesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class InfoActivity : AppCompatActivity() {

    lateinit var infoTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

       infoTextView = findViewById(R.id.infoTextView)

        val backButton = findViewById<ImageButton>(R.id.backToPlacesActivity)

        backButton.setOnClickListener{
            finish()
        }

    }
    fun getData() {

    }
}