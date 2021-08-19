package com.monolieta.pandora.android.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.monolieta.pandora.android.R

@Composable
fun Cover(url: String) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            crossfade(true)
        })

    Image(
        painter = painter,
        contentDescription = stringResource(id = R.string.cover_content_description),
        modifier = Modifier.size(128.dp)
    )
}

@Composable
@Preview(name = "Cover", showBackground = true)
private fun DefaultPreview() {
    Cover(url = "https://firebasestorage.googleapis.com/v0/b/pandora-90101.appspot.com/o/001.jpeg?alt=media&token=9cc928e6-dc29-4339-8b31-56deacdfed9d")
}