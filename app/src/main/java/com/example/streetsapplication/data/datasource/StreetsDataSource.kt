package com.example.streetsapplication.data.datasource

import com.example.streetsapplication.data.datasource.model.StreetDto
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StreetsDataSource @Inject constructor(
    private val streetsDatabaseReference: DatabaseReference
) {

    suspend fun getAllStreets(): List<StreetDto> {
        val querySnapshot = streetsDatabaseReference.get().await()
        return querySnapshot.children.mapNotNull { data -> data.getValue<StreetDto>() }
    }

    suspend fun addStreet(streetDto: StreetDto) {
        val snapshot = streetsDatabaseReference.get().await()
        streetsDatabaseReference.child(snapshot.childrenCount.toString()).setValue(streetDto)
    }

    suspend fun deleteStreets(streetsDto: List<StreetDto>) {
        // TODO: make functionality
    }
}