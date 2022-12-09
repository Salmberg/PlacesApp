package com.example.placesapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class ImageActivity : AppCompatActivity() {

    lateinit var imageView: ImageView

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        imageView = findViewById(R.id.pictureView)

        val button = findViewById<Button>(R.id.backToPlacesButton)
        button.setOnClickListener {

        }
    }
}