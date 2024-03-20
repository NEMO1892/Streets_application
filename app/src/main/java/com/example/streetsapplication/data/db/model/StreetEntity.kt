package com.example.streetsapplication.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "streets")
data class StreetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo("name_of_street")
    val nameOfStreet: String,
    @ColumnInfo("name_of_location")
    val nameOfLocation: String,
    @ColumnInfo("photo_url")
    val photoUrl: String,

    )
