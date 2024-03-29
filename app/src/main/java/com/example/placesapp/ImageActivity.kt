package com.example.placesapp

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import java.net.URL
import com.bumptech.glide.Glide

class ImageActivity : AppCompatActivity() {

    lateinit var pictureView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        pictureView = findViewById(R.id.pictureView)



        val picture = intent.getStringExtra("picture_image")
            Glide.with(this)
                .load(picture)
                .into(pictureView)


        val backToPlacesButton = findViewById<ImageView>(R.id.backToPlacesButton)
        backToPlacesButton.setOnClickListener {
            finish()
        }
    }
}
