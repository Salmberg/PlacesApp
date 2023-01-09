package com.example.placesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.placesapp.databinding.ActivityThisPlaceMapsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class thisPlaceMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityThisPlaceMapsBinding
    var longitude = 0.0
    var latitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        longitude = intent.getDoubleExtra("longitude",0.0)
        latitude = intent.getDoubleExtra("latitude",0.0)

        db = Firebase.firestore
        auth = Firebase.auth

        binding = ActivityThisPlaceMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backButton = findViewById<Button>(R.id.backButton3)
        backButton.setOnClickListener {
            finish()
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val placePin = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(placePin).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(placePin))
    }
}