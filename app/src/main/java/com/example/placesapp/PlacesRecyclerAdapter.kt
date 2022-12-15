package com.example.placesapp

import android.annotation.SuppressLint
import android.content.Intent
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PlacesRecyclerAdapter(
    val context: PlacesActivity,
    val places: List<Place>
):
    RecyclerView.Adapter<PlacesRecyclerAdapter.ViewHolder>() {


    val layoutInflater = LayoutInflater.from(context)

    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.places, parent, false)

        return ViewHolder(itemView)

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = places[position]

        holder.nameTextView.text = place.name


        db = Firebase.firestore
        auth = Firebase.auth



        holder.apply {
            nameTextView.setOnClickListener {
                val intent = Intent( context, InfoActivity::class.java)
                intent.putExtra("GetInfo", place.info)
                context.startActivity(intent)
                Log.e("Clicked", "you clicked te text!")
            }
        }

        holder.apply {
            pictureButton.setOnClickListener {
                val intent = Intent( context, ImageActivity::class.java)
                context.startActivity(intent)
                Log.e("Clicked", "you clicked te text!")
            }
        }

       //holder.pinImageView.setImageDrawable(holder.pinImageView.context.getDrawable(place.position))
       //holder.pictureButton.setImageDrawable(holder.pictureButton.context.getDrawable(place.image))
    }

    override fun getItemCount() = places.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        val pictureButton = itemView.findViewById<FloatingActionButton>(R.id.pictureButton)
        val pinImageView = itemView.findViewById<ImageView>(R.id.pinImageView)



    }

}

