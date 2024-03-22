package com.example.streetsapplication.domain.model

import android.net.Uri
import com.google.firebase.storage.StorageReference
import java.io.File

data class PhotoDomain(
    val id: String = "",
    val photoReference: StorageReference? = null,
    val photoUri: Uri? = null,
    var isSelected: Boolean = false,
    var isSelectedDeleted: Boolean = false
)