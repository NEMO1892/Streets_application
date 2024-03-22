package com.example.streetsapplication.ui.util

import android.annotation.SuppressLint
import android.widget.ImageView
import com.example.streetsapplication.ui.GlideApp
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadImageFromFirebase(storageReference: StorageReference?) {
    GlideApp.with(context)
        .load(storageReference)
        .into(this)
}

@SuppressLint("SimpleDateFormat")
fun getCurrentDateTime(): String {
    val currentTimeMillis = System.currentTimeMillis()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
    return dateFormat.format(Date(currentTimeMillis))
}

fun createUniqueId(): String {
    return UUID.randomUUID().toString().plus(getCurrentDateTime())
}