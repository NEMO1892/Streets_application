package com.example.streetsapplication.di.module

import android.content.Context
import androidx.room.Room
import com.example.streetsapplication.data.db.StreetsDatabase
import com.example.streetsapplication.data.db.dao.StreetsDao
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): StreetsDatabase =
        Room.databaseBuilder(
            appContext,
            StreetsDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideResultDao(database: StreetsDatabase): StreetsDao = database.getStreetsDao()

    @Provides
    @Singleton
    fun provideStreetsDatabaseReference(): DatabaseReference = Firebase.database.getReference(STREETS_DATABASE_KEY)

    private companion object {

        const val DATABASE_NAME = "StreetsDatabase"
        const val STREETS_DATABASE_KEY = "Streets"
    }
}