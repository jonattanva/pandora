package com.monolieta.pandora.android.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.monolieta.pandora.android.ui.Screen

@Composable
fun SearchView() {
    Box {
        Text(text = Screen.Search.name)
    }
}

@Composable
@Preview(showBackground = true)
private fun DefaultPreview() {
    SearchView()
}