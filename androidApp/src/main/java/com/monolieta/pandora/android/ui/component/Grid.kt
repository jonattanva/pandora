package com.monolieta.pandora.android.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monolieta.pandora.android.R

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun Grid(values: List<Pair<String, String>>) {
    LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 128.dp)) {
        items(values) { (_, url) ->
            Cover(url)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun GridToDrawable(values: List<Int>) {
    LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 128.dp)) {
        items(values) {
            Cover(painterResource(it))
        }
    }
}

@Preview
@Composable
private fun Preview() {
    GridToDrawable(listOf(R.drawable.psychonauts))
}