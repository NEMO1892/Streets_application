package com.example.streetsapplication.data.datasource.model

import android.os.Parcelable
import com.example.streetsapplication.domain.model.StreetAndLocationDomain
import kotlinx.parcelize.Parcelize

@Parcelize
data class StreetAndLocationDto(
    val id: String = "",
    val nameOfStreet: String = "",
    val nameOfLocation: String = "",
) : Parcelable {

    fun mapDtoToDomain() = StreetAndLocationDomain(
        id,
        nameOfStreet,
        nameOfLocation,
    )
}
