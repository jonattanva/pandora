package com.monolieta.pandora.android

import android.os.Bundle
import com.monolieta.pandora.Greeting
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.monolieta.pandora.android.ui.Screen
import com.monolieta.pandora.android.ui.ScreenHandle
import com.monolieta.pandora.android.ui.component.Navigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface(color = MaterialTheme.colors.background) {
                Main()
            }
        }
    }
}

@Composable
private fun Main() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { Navigation(navController, items = Screen.values()) }
    ) {
        ScreenHandle(navController, padding = it)
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
    Main()
}