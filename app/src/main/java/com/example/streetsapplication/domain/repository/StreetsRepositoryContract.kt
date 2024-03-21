package com.example.streetsapplication.domain.repository

import com.example.streetsapplication.domain.model.StreetDomain

interface StreetsRepositoryContract {

    fun getStreets(): List<StreetDomain>

    fun addStreet(streetDomain: StreetDomain)

    fun deleteStreets(streetDomain: List<StreetDomain>)
}