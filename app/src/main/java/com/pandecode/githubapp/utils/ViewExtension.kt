package com.pandecode.githubapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadAsCircle(source: String) {
    Glide.with(this.context)
        .load(source)
        .circleCrop()
        .into(this)
}

fun ImageView.load(source: String) {
    Glide.with(this.context)
        .load(source)
        .into(this)
}