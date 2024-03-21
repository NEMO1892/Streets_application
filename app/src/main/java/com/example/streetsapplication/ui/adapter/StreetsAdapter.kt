package com.example.streetsapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.streetsapplication.databinding.ItemStreetBinding
import com.example.streetsapplication.ui.model.Street

class StreetsAdapter(private val longClickListener: (Street) -> Unit) :
    ListAdapter<Street, StreetViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StreetViewHolder =
        StreetViewHolder(
            ItemStreetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            longClickListener
        )

    override fun onBindViewHolder(holder: StreetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Street>() {

        override fun areItemsTheSame(oldItem: Street, newItem: Street): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Street, newItem: Street): Boolean {
            return oldItem == newItem
        }
    }
}