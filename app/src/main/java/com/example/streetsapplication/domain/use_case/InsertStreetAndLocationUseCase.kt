package com.example.streetsapplication.domain.use_case

import com.example.streetsapplication.domain.model.StreetAndLocationDomain
import com.example.streetsapplication.domain.repository.StreetsRepositoryContract
import javax.inject.Inject

class InsertStreetAndLocationUseCase @Inject constructor(
    private val streetsRepository: StreetsRepositoryContract
) {

    suspend operator fun invoke(streetAndLocationDomain: StreetAndLocationDomain) =
        streetsRepository.insertStreetAndLocation(streetAndLocationDomain)
}