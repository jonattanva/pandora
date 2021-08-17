package com.monolieta.pandora.android

import android.os.Bundle
import com.monolieta.pandora.Greeting
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(color = MaterialTheme.colors.background) {
                Greeting(greet())
            }
        }
    }
}

fun greet(): String {
    return Greeting().greeting()
}

@Composable
fun Greeting(name: String) {
    Text(text = "$name!")
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    Greeting(name = greet())
}