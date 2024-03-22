package com.example.streetsapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.streetsapplication.databinding.ItemStreetBinding
import com.example.streetsapplication.domain.model.PhotoDomain

class PhotosAdapter(private val longClickListener: (PhotoDomain) -> Unit) : ListAdapter<PhotoDomain, PhotosViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder =
        PhotosViewHolder(
            ItemStreetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            longClickListener
        )

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setSelectedState() {
        currentList.forEachIndexed { index, photo ->
            photo.isSelected = true
        }
        notifyDataSetChanged()
    }

    fun setUnselectedState(callback: (Boolean) -> Unit) {
        var isNoOneSelected = 0
        currentList.forEachIndexed { _, photo ->
            if (photo.isSelectedDeleted) {
                isNoOneSelected++
            }
        }
        if (isNoOneSelected == 0) {
            currentList.forEachIndexed { _, photo ->
                photo.isSelected = false
            }
            callback.invoke(false)
        } else {
            callback.invoke(true)
        }
        notifyDataSetChanged()
    }

    class DiffCallback : DiffUtil.ItemCallback<PhotoDomain>() {

        override fun areItemsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain): Boolean {
            return oldItem == newItem
        }
    }
}