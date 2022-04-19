package com.monolieta.pandora.android.ui.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.ui.component.Email
import com.monolieta.pandora.android.ui.state.EmailState
import com.monolieta.pandora.android.ui.theme.MyApplicationTheme

@Composable
fun RecoverView(
    navigation: NavHostController,
    viewModel: AuthenticationViewModel = viewModel(factory = AuthenticationViewModelFactory())
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val recoverResult = viewModel.recoverResult
    val focusManager = LocalFocusManager.current

    fun clickHandle(username: String) {
        /*scope.launch {
            viewModel.reset(username = username)
        }*/
    }
/*
    if (recoverResult?.error != null) {
        Toast.makeText(context, recoverResult.error, Toast.LENGTH_LONG)
            .show()
    }*/

    FormView(
        onClick = ::clickHandle
    )
}

@Composable
private fun FormView(
    onClick: (String) -> Unit
) {
    val emailState = remember { EmailState() }

    fun onSubmit() {
        onClick(emailState.value)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        Email(
            text = stringResource(R.string.email),
            state = emailState,
            onDone = ::onSubmit
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = ::onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = stringResource(R.string.recover))
        }
    }
}

@Composable
@Preview(name = "Dark Mode")
fun RecoverViewDarkMode() {
    val navController = rememberNavController()
    MyApplicationTheme(darkTheme = true) {
        RecoverView(navigation = navController)
    }
}

@Composable
@Preview(name = "Light Mode")
fun RecoverViewLightMode() {
    val navController = rememberNavController()
    MyApplicationTheme(darkTheme = false) {
        RecoverView(navigation = navController)
    }
}