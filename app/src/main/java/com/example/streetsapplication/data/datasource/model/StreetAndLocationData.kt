package com.example.streetsapplication.data.datasource.model

import android.os.Parcelable
import com.example.streetsapplication.data.db.model.StreetAndLocationEntity
import com.example.streetsapplication.domain.model.StreetAndLocationDomain
import kotlinx.parcelize.Parcelize

@Parcelize
data class StreetAndLocationData(
    val nameOfStreet: String = "",
    val nameOfLocation: String = "",
) : Parcelable {

    fun mapDataToDomain() = StreetAndLocationDomain(
        nameOfStreet,
        nameOfLocation,
    )

    fun mapToEntity() = StreetAndLocationEntity(
        nameOfStreet = nameOfStreet,
        nameOfLocation = nameOfLocation
    )
}
