package com.example.placesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
        holder.pictureImageView.setImageDrawable(holder.pictureImageView.context.getDrawable(place.image))
        holder.pinImageView.setImageDrawable(holder.pinImageView.context.getDrawable(place.position))
    }

    override fun getItemCount() = places.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
        val pictureImageView = itemView.findViewById<ImageView>(R.id.pictureImageView)
        val pinImageView = itemView.findViewById<ImageView>(R.id.pinImageView)

    }

}

