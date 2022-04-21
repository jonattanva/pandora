package com.monolieta.pandora.android.ui.authentication

import android.widget.Toast
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.ui.Screen
import com.monolieta.pandora.android.ui.component.Email
import com.monolieta.pandora.android.ui.component.Form
import com.monolieta.pandora.android.ui.component.Password
import com.monolieta.pandora.android.ui.state.InputState
import com.monolieta.pandora.android.ui.theme.PandoraTheme
import kotlinx.coroutines.launch

@Composable
fun LoginView(
    navigation: NavHostController,
    viewModel: AuthenticationViewModel = viewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val loading = viewModel.loading
    val authenticationResult = viewModel.authenticationResult

    fun clickHandle(username: String, password: String) {
        scope.launch {
            viewModel.signIn(username = username, password = password)
        }
    }

    authenticationResult?.error?.let {
        viewModel.clear()
        Toast.makeText(context, stringResource(it), Toast.LENGTH_LONG)
            .show()
    }

    authenticationResult?.route?.let {
        viewModel.clear()
        navigation.navigate(it)
    }

    FormView(
        loading = loading,
        onClick = ::clickHandle,
        navigation = navigation
    )
}

@Composable
private fun FormView(
    loading: Boolean,
    navigation: NavHostController,
    onClick: (String, String) -> Unit
) {
    val emailState = remember { InputState() }
    val passwordState = remember { InputState() }

    fun onSubmit() {
        onClick(emailState.value, passwordState.value)
    }

    Form(loading = loading) {
        Text(stringResource(R.string.sign_in_title), fontSize = 30.sp)

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
                .clickable { navigation.navigate(Screen.Recover.route) }
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
                        navigation.navigate(Screen.Account.route)
                    }
            )
        }
    }
}

@Composable
@Preview(name = "Dark Mode")
fun LoginViewDarkMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = true) {
        LoginView(navigation = navController)
    }
}

@Composable
@Preview(name = "Light Mode")
fun LoginViewLightMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = false) {
        LoginView(navigation = navController)
    }
}