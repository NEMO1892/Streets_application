package com.example.streetsapplication.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "streets_and_location")
data class StreetAndLocationEntity(
    @PrimaryKey
    val id: String = "",
    @ColumnInfo("name_of_street")
    val nameOfStreet: String,
    @ColumnInfo("name_of_location")
    val nameOfLocation: String
)