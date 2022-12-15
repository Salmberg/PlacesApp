package com.example.placesapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.DatabaseReference





open class InfoActivity : AppCompatActivity() {


    lateinit var infoTextView: TextView

    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

       infoTextView = findViewById(R.id.infoTextView)


        db = Firebase.firestore
        auth = Firebase.auth

        val getInfo = intent.getStringExtra("GetInfo")

       infoTextView.text = getInfo.toString()


        val backButton = findViewById<ImageButton>(R.id.backToPlacesActivity)

        backButton.setOnClickListener {
            finish()
        }
    }
}



