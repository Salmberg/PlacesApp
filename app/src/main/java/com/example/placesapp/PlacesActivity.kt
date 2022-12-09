package com.example.placesapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.placesapp.Datamanager.places
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PlacesActivity : AppCompatActivity() {

    var places = mutableListOf<Place>(
                Place("Ullådalen","lite info"),
                Place("Kongabodarna","lite info"),
                Place("Runmarö","lite info"),
                Place("Vaxholm","lite info"),
                Place("Killinge","lite info"),
                Place("Ottsjön","lite info"),
                Place("Åre","lite info"),
                Place("Köpenhamn","lite info"),
                Place("Gotland","lite info")
    )

    lateinit var recyclerView: RecyclerView
    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth
    lateinit var adapter: PlacesRecyclerAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places)

        db = Firebase.firestore
        auth = Firebase.auth

        recyclerView = findViewById(R.id.placesRecycleView)


        val signOutButton = findViewById<ImageButton>(R.id.backButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PlacesRecyclerAdapter(this, places)
        recyclerView.adapter = adapter
        getUserData()

        val addPlaceButton = findViewById<FloatingActionButton>(R.id.addPlaceButton)
        addPlaceButton.setOnClickListener {
            goToAddActivity()
        }

        signOutButton.setOnClickListener {
            auth.signOut()
            finish()
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun getUserData() {

        db = Firebase.firestore

        auth.currentUser?.let {
            db.collection("users")
                .document(it.uid)
                .collection("places")
                .addSnapshotListener { documents, e ->
                    if (e != null) {
                        Log.w("!!!!", "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    for (dc in documents!!.documentChanges) {
                        when (dc.type) {
                            DocumentChange.Type.ADDED -> {
                                val place = dc.document.toObject(Place::class.java)
                                places.add(place)
                            }
                            DocumentChange.Type.MODIFIED -> Log.d(
                                "!!!!",
                                "Modified city: ${dc.document.data}"
                            )
                            DocumentChange.Type.REMOVED -> Log.d(
                                "!!!!",
                                "Removed city: ${dc.document.data}"
                            )
                        }
                    }
                    val places = mutableListOf<Place>()
                    for (doc in documents!!) {
                        val place = doc.toObject(Place::class.java)
                        places.add(place)
                        Log.d("!!!!", doc.data.toString())
                    }
                    Log.d("!!!!", "Current places: ${places}")
                    adapter.notifyDataSetChanged()
                }
        }
    }

    fun goToPictureActivity() {
        val intent = Intent(this, ImageActivity::class.java)
        startActivity(intent)
        Log.d("!!!", "inne i gotopicture")
    }

    fun goToAddActivity() {
        val intent = Intent(this, AddPlaceActivity::class.java)
        startActivity(intent)
    }
}
