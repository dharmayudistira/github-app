package com.pandecode.githubapp.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.fragment.app.Fragment
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

fun ImageView.loadAsCircleFromDrawable(drawableId: Int) {
    val imageLoader = context.imageLoader

    this.load(
        drawableResId = drawableId,
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

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
