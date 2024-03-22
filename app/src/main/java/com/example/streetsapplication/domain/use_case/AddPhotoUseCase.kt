package com.example.streetsapplication.domain.use_case

import com.example.streetsapplication.domain.model.PhotoDomain
import com.example.streetsapplication.domain.model.StreetAndLocationDomain
import com.example.streetsapplication.domain.repository.StreetsRepositoryContract
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddPhotoUseCase @Inject constructor(
    private val streetsRepository: StreetsRepositoryContract
) {

    suspend operator fun invoke(photoDomain: PhotoDomain) : Flow<Unit> = streetsRepository.addPhoto(photoDomain)
}