package com.example.streetsapplication.domain.model

import com.example.streetsapplication.data.datasource.model.StreetAndLocationData
import com.example.streetsapplication.ui.model.StreetAndLocation

data class StreetAndLocationDomain(
    val nameOfStreet: String,
    val nameOfLocation: String,
) {

    fun mapDomainToUi() = StreetAndLocation(
        nameOfStreet,
        nameOfLocation,
    )

    fun mapDomainToData() = StreetAndLocationData(
        nameOfStreet,
        nameOfLocation,
    )
}