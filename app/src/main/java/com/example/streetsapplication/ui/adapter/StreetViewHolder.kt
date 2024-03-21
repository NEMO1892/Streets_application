package com.example.streetsapplication.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.streetsapplication.databinding.ItemStreetBinding
import com.example.streetsapplication.ui.model.Street
import com.example.streetsapplication.ui.util.loadImage
import com.example.streetsapplication.ui.util.setOnDebounceClickListener

class StreetViewHolder(
    private val binding: ItemStreetBinding,
    private val longClickListener: (Street) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Street) = with(binding) {
        setUpClickListeners(item)
        streetImage.loadImage(item.photoUrl)
    }

    private fun setUpClickListeners(item: Street) {

        binding.root.setOnDebounceClickListener {
            longClickListener.invoke(item)
        }
    }
}