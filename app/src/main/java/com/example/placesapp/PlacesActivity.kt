package com.example.placesapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.placesapp.Datamanager.places
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PlacesActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    lateinit var db: FirebaseFirestore
    lateinit var auth : FirebaseAuth


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)

        db = Firebase.firestore

        auth = Firebase.auth

        recyclerView = findViewById(R.id.placesRecycleView)
        val button = findViewById<ImageButton>(R.id.backButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PlacesRecyclerAdapter(this, Datamanager.places)

        val adapter = PlacesRecyclerAdapter(this, places)
        recyclerView.adapter = adapter

        val pictureButton = findViewById<FloatingActionButton>(R.id.pictureButton)
        pictureButton.setOnClickListener {
            goToPictureActivity()
        }

        button.setOnClickListener {
            finish()
        }

    }
    fun goToPictureActivity(){
        val intent = Intent(this, ImageActivity::class.java)
        startActivity(intent)
    }


}
