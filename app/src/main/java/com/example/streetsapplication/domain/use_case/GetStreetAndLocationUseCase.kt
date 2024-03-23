package com.example.streetsapplication.domain.use_case

import com.example.streetsapplication.domain.repository.StreetsRepositoryContract
import javax.inject.Inject

class GetStreetAndLocationUseCase @Inject constructor(
    private val streetsRepository: StreetsRepositoryContract
) {

    suspend operator fun invoke() = streetsRepository.getStreetAndLocation()
}