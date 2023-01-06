package com.example.placesapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddPlaceActivity : AppCompatActivity() {

    lateinit var addNameEditText: EditText
    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth
    lateinit var addInfoEditText: EditText
    lateinit var addUrlEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_place)

        db = Firebase.firestore
        auth = Firebase.auth
        addNameEditText = findViewById(R.id.addNameEditText)
        addInfoEditText = findViewById(R.id.addInfoEditText)
        addUrlEditText = findViewById(R.id.addUrlEditText)


        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            savePlace()

        }

        val goBackButton = findViewById<ImageButton>(R.id.goBackButton)
        goBackButton.setOnClickListener {
            onBackPressed()
        }

    }

    fun savePlace() {
        val place = Place(
            name = addNameEditText.text.toString(),
            info = addInfoEditText.text.toString(),
            imageURL = addUrlEditText.text.toString(),
            longitude = intent.getDoubleExtra("longitude",0.0),
            latitude = intent.getDoubleExtra("latitude",0.0)
        )



        addNameEditText.setText("")
        addInfoEditText.setText("")
        addUrlEditText.setText("")


        val user = auth.currentUser
        if (user == null) {
            return
        }

        db.collection("users").document(user.uid)
            .collection("places").add(place)

        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)

    }
}
