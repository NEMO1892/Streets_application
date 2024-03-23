package com.example.streetsapplication.data.datasource

import com.example.streetsapplication.data.datasource.model.StreetAndLocationData
import com.example.streetsapplication.data.db.dao.StreetsDao
import com.example.streetsapplication.domain.model.PhotoDomain
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StreetsDataSource @Inject constructor(
    private val streetsDao: StreetsDao
) {

    suspend fun getAllPhotos(): Flow<List<PhotoDomain>> = flow {
        val storageReference = FirebaseStorage.getInstance().getReference("images/")
        val listResult = storageReference.listAll().await()
        val photos = mutableListOf<PhotoDomain>()
        listResult.items.forEach { item ->
            val photoDomain = PhotoDomain(id = item.name, photoReference = item)
            photos.add(photoDomain)
        }
        emit(photos)
    }

    suspend fun addPhoto(photoDomain: PhotoDomain) = flow {
        val storageReference =
            FirebaseStorage.getInstance().getReference("images/${photoDomain.id}")
        photoDomain.photoUri?.let { uri ->
            storageReference.putFile(uri).await()
        }
        emit(Unit)
    }.flowOn(Dispatchers.IO)

    suspend fun deletePhotos(photos: List<PhotoDomain>): Flow<String> = flow {
        val storage = FirebaseStorage.getInstance()
        photos.forEach { photo ->
            val photoReference = storage.getReference("images/${photo.id}")
            try {
                photoReference.delete().await()
                emit("Deleted")
            } catch (e: Exception) {
                emit("Error")
            }
        }
    }.flowOn(Dispatchers.IO)

    suspend fun insertStreetAndLocation(streetAndLocationData: StreetAndLocationData) = flow {
        emit(streetsDao.insertStreetAndLocation(streetAndLocationData.mapToEntity()))
    }.flowOn(Dispatchers.IO)

    suspend fun getStreetAndLocation(): Flow<StreetAndLocationData> = flow {
        emit(streetsDao.getStreetAndLocation(0).mapToData())
    }.flowOn(Dispatchers.IO)
}