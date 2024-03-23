package com.example.streetsapplication.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.streetsapplication.data.datasource.model.StreetAndLocationData
import com.example.streetsapplication.domain.model.StreetAndLocationDomain

@Entity(tableName = "streets_and_location")
data class StreetAndLocationEntity(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo("name_of_street")
    val nameOfStreet: String,
    @ColumnInfo("name_of_location")
    val nameOfLocation: String
) {

    fun mapToData() = StreetAndLocationData(
        nameOfStreet,
        nameOfLocation
    )
}