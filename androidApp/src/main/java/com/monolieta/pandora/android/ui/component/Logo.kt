package com.monolieta.pandora.android.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.monolieta.pandora.android.R

@Composable
fun Logo(size: Dp = 128.dp) {
    Image(
        painterResource(R.drawable.ic_launcher_background),
        contentDescription = "",
        modifier = Modifier.size(size)
    )
}