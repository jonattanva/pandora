package com.monolieta.pandora.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.monolieta.pandora.android.R

@Composable
fun Cover(url: String) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            crossfade(true)
        })

    Box {
        Image(
            painter = painter,
            contentDescription = stringResource(id = R.string.cover_content_description)
        )
    }
}

@Composable
@Preview(name = "Cover", showBackground = true)
private fun DefaultPreview() {
    Cover(url = "https://firebasestorage.googleapis.com/v0/b/pandora-90101.appspot.com/o/001.jpeg?alt=media&token=9cc928e6-dc29-4339-8b31-56deacdfed9d")
}