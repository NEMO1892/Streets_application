package com.example.streetsapplication.ui.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.streetsapplication.databinding.ItemStreetBinding
import com.example.streetsapplication.domain.model.PhotoDomain
import com.example.streetsapplication.ui.util.loadImageFromFirebase

class PhotosViewHolder(
    private val binding: ItemStreetBinding,
    private val longClickListener: (PhotoDomain) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PhotoDomain) = with(binding) {
        setUpClickListeners(item)
        streetImage.loadImageFromFirebase(item.photoReference)
        checkState(item)
    }

    private fun setUpClickListeners(item: PhotoDomain) = with(binding) {
        root.setOnLongClickListener {
            item.isSelectedDeleted = !item.isSelectedDeleted
            deleteSelectedStateButton.isVisible = item.isSelectedDeleted
            longClickListener.invoke(item)
            true
        }
    }

    private fun checkState(item: PhotoDomain) {
        binding.deleteUnselectedStateButton.isVisible = item.isSelected
    }
}