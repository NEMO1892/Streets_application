package com.example.streetsapplication.domain.model

import com.example.streetsapplication.data.datasource.model.StreetAndLocationDto
import com.example.streetsapplication.ui.model.StreetAndLocation

data class StreetAndLocationDomain(
    val id: String,
    val nameOfStreet: String,
    val nameOfLocation: String,
) {

    fun mapDomainToUi() = StreetAndLocation(
        id,
        nameOfStreet,
        nameOfLocation,
    )

    fun mapDomainToData() = StreetAndLocationDto(
        id,
        nameOfStreet,
        nameOfLocation,
    )
}