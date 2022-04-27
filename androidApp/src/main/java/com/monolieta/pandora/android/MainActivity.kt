package com.monolieta.pandora.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.monolieta.pandora.Amplify
import com.monolieta.pandora.android.ui.NavigationView
import com.monolieta.pandora.android.ui.theme.PandoraTheme
import com.monolieta.pandora.manager.PasswordManager
import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var amplify: Amplify
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainAmplify.init(amplify)

        val password = "123456789"
        val passwordManager = PasswordManager()
        val result = passwordManager.encrypt(password, "pandora")
        Log.e("TAG", "onCreate: $result")
        Log.e("TAG", "onCreate: " + passwordManager.check(result, "pandora", password))

        setContent {
            PandoraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationView()
                }
            }
        }
    }
}