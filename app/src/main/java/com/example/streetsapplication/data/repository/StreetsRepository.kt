package com.example.streetsapplication.data.repository

import com.example.streetsapplication.data.datasource.StreetsDataSource
import com.example.streetsapplication.domain.model.StreetDomain
import com.example.streetsapplication.domain.repository.StreetsRepositoryContract
import javax.inject.Inject

class StreetsRepository @Inject constructor(
    private val streetsDataSource: StreetsDataSource
) : StreetsRepositoryContract {

    override fun getStreets(): List<StreetDomain> {
        return emptyList()
    }

    override fun addStreet(streetDomain: StreetDomain) {
        TODO("Not yet implemented")
    }

    override fun deleteStreets(streetDomain: List<StreetDomain>) {
        TODO("Not yet implemented")
    }
}