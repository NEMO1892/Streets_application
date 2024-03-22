package com.example.streetsapplication.domain.use_case

import com.example.streetsapplication.domain.model.PhotoDomain
import com.example.streetsapplication.domain.repository.StreetsRepositoryContract
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeletePhotosUseCase @Inject constructor(
    private val streetsRepository: StreetsRepositoryContract
) {

    suspend operator fun invoke(photos: List<PhotoDomain>): Flow<String> = streetsRepository.deletePhotos(photos)
}