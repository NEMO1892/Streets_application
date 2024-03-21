package com.example.streetsapplication.ui.util

import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

const val CLICK_DEBOUNCE_TIME = 3000L

fun View.setOnDebounceClickListener(onClickCallback: (View) -> Unit) {
    setOnDebounceClickListener(onClickCallback, CLICK_DEBOUNCE_TIME)
}

fun View.setOnDebounceClickListener(onClickCallback: (View) -> Unit, debounceTime: Long) {
    var lastClickTime: Long = 0

    setOnClickListener(object : View.OnClickListener {

        override fun onClick(view: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) {
                return
            }
            lastClickTime = SystemClock.elapsedRealtime()
            onClickCallback(view)
        }
    })
}

fun ImageView.loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}