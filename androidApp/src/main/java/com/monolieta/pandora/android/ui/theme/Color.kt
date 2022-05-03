package com.monolieta.pandora.android.ui.theme

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

fun getVibrantColor(bitmap: Bitmap): Color {
    val palette = Palette.from(bitmap)
        .generate()

    return palette.vibrantSwatch?.rgb?.let {
        Color(it)
    } ?: Color.Transparent
}