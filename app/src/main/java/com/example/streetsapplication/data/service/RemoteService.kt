package com.example.streetsapplication.data.service

import com.example.streetsapplication.domain.service.RemoteServiceContract
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class RemoteService @Inject constructor(
    private val streetsDatabaseReference: DatabaseReference
) : RemoteServiceContract {
}