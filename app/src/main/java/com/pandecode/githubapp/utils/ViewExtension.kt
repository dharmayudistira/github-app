package com.pandecode.githubapp.utils

import android.view.View
import android.widget.ImageView
import coil.imageLoader
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

fun ImageView.loadAsCircle(source: String) {
    val imageLoader = context.imageLoader

    this.load(
        uri = source,
        imageLoader = imageLoader
    ) {
        transformations(CircleCropTransformation())
    }
}

fun FloatingActionButton.setIconFromDrawableId(drawableId: Int) {
    val imageLoader = context.imageLoader

    this.load(
        drawableResId = drawableId,
        imageLoader = imageLoader
    )
}

fun View.showSnackbarMessage(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}
