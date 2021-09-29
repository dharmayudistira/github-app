package com.pandecode.githubapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.progressindicator.CircularProgressIndicator

fun ImageView.loadAsCircle(source: String) {
    Glide.with(this.context)
        .load(source)
        .circleCrop()
        .into(this)
}
