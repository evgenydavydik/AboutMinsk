package com.davydikes.aboutminsk.screen.main

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.davydikes.aboutminsk.R
import com.davydikes.aboutminsk.models.Place
import com.squareup.picasso.Picasso
import com.squareup.picasso.Callback
import java.lang.Exception

class RecyclerViewAdapterPlaces(
    private val onClick:(Place)->Unit
): ListAdapter<Place, RecyclerView.ViewHolder>(PlaceAdapterDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = PlaceViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_place_minsk, parent, false),
        ::onItemClick
    )
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PlaceViewHolder).bind(getItem(position))
    }
    inner class PlaceViewHolder(
        itemView: View,
        private val onItemClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {


        private val tvImage = itemView.findViewById<ImageView>(R.id.tvImagePlace)
        private val tvName = itemView.findViewById<TextView>(R.id.tvTextPlace)

        init {
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }

        fun bind(item: Place) {
            Picasso.get()
                .load(item.logo)
                .into(tvImage, object : Callback {
                    override fun onSuccess() {
                        Log.d(ContentValues.TAG, "success1")
                    }

                    override fun onError(e: Exception?) {
                        Log.d(ContentValues.TAG, "error")
                    }
                })
            tvName.text = item.name
        }
    }

    private fun onItemClick(position: Int) {
        onClick(getItem(position))
    }
}

class PlaceAdapterDiffCallback : DiffUtil.ItemCallback<Place>() {
    override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
        return oldItem.name == newItem.name && oldItem.logo == newItem.logo
    }
}