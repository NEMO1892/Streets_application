package com.example.streetsapplication.ui.model

import com.example.streetsapplication.domain.model.StreetAndLocationDomain

data class StreetAndLocation(
    val id: String,
    val nameOfStreet: String,
    val nameOfLocation: String,
) {

    fun mapToDomain() = StreetAndLocationDomain(
        id,
        nameOfStreet,
        nameOfLocation,
    )
}