package org.sopt.sample.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message : String, isShort:Boolean){
    val time = if(isShort) Snackbar.LENGTH_SHORT else Snackbar.LENGTH_LONG
    Snackbar.make(this, message, time).show()
}