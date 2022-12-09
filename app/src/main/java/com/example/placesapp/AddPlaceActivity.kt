package com.example.placesapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_place)

        db = Firebase.firestore
        auth = Firebase.auth
        addNameEditText = findViewById(R.id.addNameEditText)
        addInfoEditText = findViewById(R.id.addInfoEditText)


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
        )

        addNameEditText.setText("")
        addInfoEditText.setText("")


        val user = auth.currentUser
        if (user == null) {
            return
        }

        db.collection("users").document(user.uid)
            .collection("places").add(place)

        onBackPressed()
    }
}
