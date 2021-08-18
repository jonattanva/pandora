package com.monolieta.pandora.android.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.monolieta.pandora.android.ui.Screen

@Composable
fun HomeView() {
    Box {
        Text(text = stringResource(id = Screen.Home.title))
    }
}

@Composable
@Preview(showBackground = true)
private fun DefaultPreview() {
    HomeView()
}