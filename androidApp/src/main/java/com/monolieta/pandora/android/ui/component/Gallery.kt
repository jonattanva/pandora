package com.monolieta.pandora.android.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Gallery() {
    val state = rememberLazyListState()
    LazyColumn(state = state) {
        items(100) {
            Cover(url = "https://developer.android.com/images/brand/Android_Robot.png")
        }
    }
}

@Composable
@Preview(name = "Gallery", showBackground = true)
private fun DefaultPreview() {
    Gallery()
}