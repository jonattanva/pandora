package com.monolieta.pandora.android.ui.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.monolieta.pandora.android.R

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Cover(url: String, modifier: Modifier = Modifier) {
    Cover(
        rememberImagePainter(
            data = url,
            builder = {
                crossfade(true)
            }), modifier
    )
}

@Composable
fun Cover(painter: Painter, modifier: Modifier = Modifier) {
    Image(
        painter,
        contentDescription = stringResource(R.string.cover_content_description),
        modifier = modifier
    )
}