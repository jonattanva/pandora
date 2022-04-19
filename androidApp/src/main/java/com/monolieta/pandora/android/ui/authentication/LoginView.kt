package com.monolieta.pandora.android.ui.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.View
import com.monolieta.pandora.android.ui.component.Email
import com.monolieta.pandora.android.ui.component.Password
import com.monolieta.pandora.android.ui.state.InputState
import com.monolieta.pandora.android.ui.theme.MyApplicationTheme

@Composable
fun LoginView(
    navigation: NavHostController,
    viewModel: AuthenticationViewModel = viewModel(factory = AuthenticationViewModelFactory())
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val loginResult = viewModel.loginResult

    fun clickHandle(username: String, password: String) {
        /*scope.launch {
            viewModel.signIn(username = username, password = password)
        }*/
    }
/*
    if (loginResult?.error != null) {
        Toast.makeText(context, loginResult.error, Toast.LENGTH_LONG)
            .show()
    }*/

    FormView(
        onClick = ::clickHandle,
        navigation = navigation
    )
}

@Composable
private fun FormView(
    navigation: NavHostController,
    onClick: (String, String) -> Unit
) {
    val emailState = remember { InputState() }
    val passwordState = remember { InputState() }

    fun onSubmit() {
        onClick(emailState.value, passwordState.value)
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
            imeAction = ImeAction.Next,
        )

        Spacer(modifier = Modifier.height(16.dp))
        Password(
            text = stringResource(R.string.password),
            state = passwordState,
            onDone = ::onSubmit
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringResource(R.string.forgot_your_password),
            modifier = Modifier
                .align(Alignment.End)
                .clickable { navigation.navigate(View.Recover.route) }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = ::onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = stringResource(R.string.sign_in))
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Text(
                stringResource(R.string.create_account),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        navigation.navigate(View.Account.route)
                    }
            )
        }
    }
}

@Composable
@Preview(name = "Dark Mode")
fun LoginViewDarkMode() {
    val navController = rememberNavController()
    MyApplicationTheme(darkTheme = true) {
        LoginView(navigation = navController)
    }
}

@Composable
@Preview(name = "Light Mode")
fun LoginViewLightMode() {
    val navController = rememberNavController()
    MyApplicationTheme(darkTheme = false) {
        LoginView(navigation = navController)
    }
}