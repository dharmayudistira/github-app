package com.pandecode.githubapp.utils

import android.widget.ImageView
import coil.imageLoader
import coil.load
import coil.transform.CircleCropTransformation

fun ImageView.loadAsCircle(source: String) {
    val imageLoader = context.imageLoader

    this.load(
        uri = source,
        imageLoader = imageLoader
    ) {
        transformations(CircleCropTransformation())
    }
}
