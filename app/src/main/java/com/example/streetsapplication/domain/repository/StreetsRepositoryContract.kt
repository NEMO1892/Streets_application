package com.example.streetsapplication.domain.repository

import com.example.streetsapplication.domain.model.PhotoDomain
import com.example.streetsapplication.domain.model.StreetAndLocationDomain
import kotlinx.coroutines.flow.Flow

interface StreetsRepositoryContract {

    suspend fun getAllPhotos(): Flow<List<PhotoDomain>>

    suspend fun addPhoto(photoDomain: PhotoDomain): Flow<Unit>

    suspend fun deletePhotos(photos: List<PhotoDomain>): Flow<String>

    suspend fun insertStreetAndLocation(streetAndLocationDomain: StreetAndLocationDomain): Flow<Unit>

    suspend fun getStreetAndLocation(): Flow<StreetAndLocationDomain>
}