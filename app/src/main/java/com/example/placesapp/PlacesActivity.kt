package com.example.placesapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FieldPath.documentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

open class PlacesActivity : AppCompatActivity() {

    var places = mutableListOf<Place>(
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
        val swipeToDeleteCallback = object : swipeToDeleteCallback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val selectedPlaceId = places[position].documentId
                places.removeAt(position)
                recyclerView.adapter?.notifyItemRemoved(position)
                if (selectedPlaceId != null) {
                    deletePlace(selectedPlaceId)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    fun deletePlace(id: String) {
        Firebase.firestore.collection("users")
            .document(Firebase.auth.uid.toString())
            .collection("places")
            .document(id)
            .delete()
            .addOnSuccessListener { Log.d(ContentValues.TAG, "Document raderat") }
            .addOnFailureListener { e -> Log.w(ContentValues.TAG, "Något gick fel") }
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
                            DocumentChange.Type.REMOVED -> {
                                val place = dc.document.toObject(Place::class.java)
                                places.remove(place)
                            }
                        }
                    }
                    val places = mutableListOf<Place>()
                    for (doc in documents!!) {
                        val place = doc.toObject(Place::class.java)
                        places.add(place)
                        Log.d("!!!!", doc.data.toString())
                    }
                    Log.d("!!!!", "Current places: ${places.size}")
                    adapter.notifyDataSetChanged()
                }
        }
    }

    fun goToAddActivity() {
        val intent = Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }
}






