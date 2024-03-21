package com.example.streetsapplication.data.datasource.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StreetDto(
    val id: Long = 0,
    val nameOfStreet: String = "",
    val nameOfLocation: String = "",
    val photoUrl: String = "",
) : Parcelable
