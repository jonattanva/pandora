package com.monolieta.pandora.android.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun Grid(
    modifier: Modifier = Modifier,
    cells: GridCells = GridCells.Adaptive(minSize = 128.dp),
) {
    // Modifier.fillMaxWidth()
    Box(modifier) {
        LazyVerticalGrid(cells) {
            items(100) {
                Cover(url = "https://firebasestorage.googleapis.com/v0/b/pandora-90101.appspot.com/o/001.jpeg?alt=media&token=9cc928e6-dc29-4339-8b31-56deacdfed9d")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun DefaultPreview() {
    Grid()
}