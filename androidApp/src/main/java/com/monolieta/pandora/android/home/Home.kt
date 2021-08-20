package com.monolieta.pandora.android.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun HomeView() {
    Box {
        Text(text = stringResource(id = Screen.Home.title))
    }
}