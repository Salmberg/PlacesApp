package com.example.placesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.placesapp.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var auth: FirebaseAuth
    lateinit var db: FirebaseFirestore
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Firebase.firestore
        auth = Firebase.auth

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

        val adapter = PlacesInfoAdapter(this)
        mMap.setInfoWindowAdapter(adapter)

        //createMarkers()
        createPlaces()

        val latitude = 59.40
        val longitude = 18.32
        val zoomLevel = 15f

        val homeLatLng = LatLng(latitude, longitude)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoomLevel))
        mMap.addMarker(MarkerOptions().position(homeLatLng))

        setMapLongClick(mMap)
    }

    private fun setMapLongClick(map:GoogleMap) {
        map.setOnMapLongClickListener {
                latLng ->
            map.addMarker(
                MarkerOptions()
                    .position(latLng)

            )
        }
    }

   /* fun createMarkers(){
        var vaxholm = LatLng(59.40, 18.32)

        var marker1 = mMap.addMarker(
            MarkerOptions()
                .position(vaxholm)
                .title("Vaxholm")
                .snippet("Skärgårdens huvudstad")
        )

        var marker2 = mMap.addMarker(
            MarkerOptions()
                .position(
                    LatLng(59.310310, 18.080688)
                )
                .title("Knivsöder")
                .snippet("Home of Alkebäck")
        )

        var marker3 = mMap.addMarker(
            MarkerOptions()
                .position(LatLng(59.369223, 16.503793))
                .title("Eskilstuna")
                .snippet("Home of Friddler")
        )
    }

    */

    fun createPlaces() {

        val p1 = PlaceInfo("Vaxholm", "Skärgårdens huvudstad", LatLng(59.40, 18.32), R.drawable.ic_baseline_home_24)
        //val p2 = PlaceInfo("Knivsöder", "Home of Alkebäck", LatLng(59.310310, 18.080688), R.drawable.ic_baseline_flatware_24)
       // val p3 = PlaceInfo("Eskilstuna", "Home of Friddler", LatLng(59.369223, 16.503793), R.drawable.ic_baseline_wine_bar_24)

        val placeList = listOf<PlaceInfo>(p1)

        for (place in placeList) {
            val marker = mMap.addMarker(MarkerOptions().position(place.position))
            marker?.tag = place
        }
    }
}

data class PlaceInfo(val name: String, val info:String, val position:LatLng, val image:Int)
