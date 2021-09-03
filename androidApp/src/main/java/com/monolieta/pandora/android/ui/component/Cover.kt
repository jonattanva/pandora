package com.monolieta.pandora.android.ui.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.monolieta.pandora.android.R

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Cover(url: String, modifier: Modifier = Modifier) = Cover(url, modifier, null)

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Cover(
    url: String,
    modifier: Modifier = Modifier,
    onSuccess: ((result: Bitmap) -> Unit)? = null
) {
    Cover(
        rememberImagePainter(
            data = url,
            builder = {
                crossfade(true)
                target { drawable ->
                    if (onSuccess != null) {
                        onSuccess(drawable.toBitmap())
                    }
                }
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

@Preview
@Composable
private fun Preview() {
    Cover(
        painter = painterResource(R.drawable.psychonauts),
        modifier = Modifier.size(128.dp)
    )
}