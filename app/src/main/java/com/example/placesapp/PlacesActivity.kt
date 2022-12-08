package com.example.placesapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.placesapp.Datamanager.places

class PlacesActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)


        recyclerView = findViewById(R.id.placesRecycleView)
        val button = findViewById<ImageButton>(R.id.backButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PlacesRecyclerAdapter(this, Datamanager.places)

        val adapter = PlacesRecyclerAdapter(this, places)
        recyclerView.adapter = adapter

        button.setOnClickListener {
            finish()
        }

    }
}
