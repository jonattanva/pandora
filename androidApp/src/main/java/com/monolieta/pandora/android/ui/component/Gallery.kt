package com.monolieta.pandora.android.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Gallery(dataSet: Array<Pair<String, String>>, onClick: (key: String) -> Unit) {
    val cells: GridCells = GridCells.Adaptive(minSize = 128.dp)

    Box(Modifier.fillMaxSize()) {
        LazyVerticalGrid(cells) {
            items(items = dataSet) { (key, url) ->
                Cover(url, modifier = Modifier
                    .size(128.dp)
                    .clickable { onClick(key) })
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    Gallery(dataSet = arrayOf(
        Pair("1", ""),
        Pair("2", ""),
        Pair("3", ""),
        Pair("4", ""),
    )) {}
}