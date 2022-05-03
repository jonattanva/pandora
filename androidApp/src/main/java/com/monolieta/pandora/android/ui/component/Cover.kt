package com.monolieta.pandora.android.ui.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.monolieta.pandora.android.R

@Composable
fun Cover(url: String, modifier: Modifier = Modifier) = Cover(url, modifier, null)

@Composable
fun Cover(
    url: String,
    modifier: Modifier = Modifier,
    onSuccess: ((result: Bitmap) -> Unit)? = null
) {
    Cover(
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(url).apply {
                crossfade(true)
                target { drawable ->
                    if (onSuccess != null) {
                        onSuccess(drawable.toBitmap())
                    }
                }
            }.build()
        ), modifier
    )
}

@Composable
fun Cover(painter: Painter, modifier: Modifier = Modifier) {
    Image(
        painter,
        contentDescription = "",
        modifier = modifier
    )
}

@Preview
@Composable
private fun CoverPreview() {
    Cover(
        painter = painterResource(R.drawable.psychonauts),
        modifier = Modifier.size(64.dp)
    )
}