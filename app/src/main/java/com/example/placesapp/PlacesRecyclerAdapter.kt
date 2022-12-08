package com.example.placesapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PlacesRecyclerAdapter(
    val context: PlacesActivity,
    val places: List<Place>
):
    RecyclerView.Adapter<PlacesRecyclerAdapter.ViewHolder>() {


    val layoutInflater = LayoutInflater.from(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.places, parent, false)

        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = places[position]

        holder.nameTextView.text = place.name

        holder.pinImageView.setImageDrawable(holder.pinImageView.context.getDrawable(place.position))
        holder.pictureButton.setImageDrawable(holder.pictureButton.context.getDrawable(place.image))


    }

    override fun getItemCount() = places.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        val pictureButton = itemView.findViewById<FloatingActionButton>(R.id.pictureButton)
        val pinImageView = itemView.findViewById<ImageView>(R.id.pinImageView)



    }

}

