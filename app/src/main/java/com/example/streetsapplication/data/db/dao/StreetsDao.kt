package com.example.streetsapplication.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.streetsapplication.data.db.model.StreetAndLocationEntity

@Dao
interface StreetsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStreetAndLocation(streetAndLocationEntity: StreetAndLocationEntity)

    @Query("SELECT * FROM streets_and_location WHERE id = :id")
    fun getStreetAndLocation(id: Int): StreetAndLocationEntity
}