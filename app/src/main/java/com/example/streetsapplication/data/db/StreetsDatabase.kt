package com.example.streetsapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.streetsapplication.data.db.dao.StreetsDao
import com.example.streetsapplication.data.db.model.StreetEntity

@Database(
    entities = [StreetEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StreetsDatabase : RoomDatabase() {

    abstract fun getStreetsDao(): StreetsDao
}