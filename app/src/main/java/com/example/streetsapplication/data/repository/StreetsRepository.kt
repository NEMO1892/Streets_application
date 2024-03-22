package com.example.streetsapplication.data.repository

import com.example.streetsapplication.data.datasource.StreetsDataSource
import com.example.streetsapplication.domain.model.PhotoDomain
import com.example.streetsapplication.domain.model.StreetAndLocationDomain
import com.example.streetsapplication.domain.repository.StreetsRepositoryContract
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class StreetsRepository @Inject constructor(
    private val streetsDataSource: StreetsDataSource
) : StreetsRepositoryContract {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getStreets(): Flow<List<StreetAndLocationDomain>> =
        streetsDataSource.getAllStreets()
            .flatMapConcat { streetsDtoList ->
                flow {
                    val streetDomainList = streetsDtoList.map { it.mapDtoToDomain() }
                    emit(streetDomainList)
                }
            }

    override suspend fun getAllPhotos(): Flow<List<PhotoDomain>> = streetsDataSource.getAllPhotos()

    override suspend fun addPhoto(photoDomain: PhotoDomain): Flow<Unit> = streetsDataSource.addPhoto(photoDomain)

    override suspend fun addStreet(streetDomain: StreetAndLocationDomain): Flow<Unit> =
        flowOf(Unit)

    override suspend fun deletePhotos(photos: List<PhotoDomain>): Flow<String> = streetsDataSource.deletePhotos(photos)
}