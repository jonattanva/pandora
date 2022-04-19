package com.monolieta.pandora.android.ui.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
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
import com.monolieta.pandora.android.ui.component.*
import com.monolieta.pandora.android.ui.state.ConfirmPasswordState
import com.monolieta.pandora.android.ui.state.EmailState
import com.monolieta.pandora.android.ui.state.PasswordState
import com.monolieta.pandora.android.ui.theme.PandoraTheme
import com.monolieta.pandora.model.User
import kotlinx.coroutines.launch

@Composable
fun RegisterView(
    navigation: NavHostController,
    viewModel: AuthenticationViewModel = viewModel(factory = AuthenticationViewModelFactory())
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val registerResult = viewModel.registerResult

    fun onClick(user: User) {
        scope.launch {
            viewModel.signUp(user)
        }
    }
/*
    if (registerResult?.error != null) {
        Toast.makeText(context, registerResult.error, Toast.LENGTH_LONG)
            .show()
    }*/


    FormView(
        loading = false,
        navigation = navigation,
        onClick = ::onClick
    )
}

@Composable
private fun FormView(
    loading: Boolean,
    navigation: NavHostController,
    onClick: (User) -> Unit
) {
    val passwordFocusRequest = remember { FocusRequester() }
    val confirmationPasswordFocusRequest = remember { FocusRequester() }

    val emailState = remember { EmailState() }
    val passwordState = remember { PasswordState() }
    val confirmPasswordState = remember {
        ConfirmPasswordState(passwordState = passwordState)
    }

    fun onSubmit() {
        onClick(
            User(
                email = emailState.value,
                password = passwordState.value
            )
        )
    }

    Form(loading = loading) {
        Spacer(modifier = Modifier.height(16.dp))
        Email(
            text = stringResource(R.string.email),
            state = emailState,
            imeAction = ImeAction.Next,
            onDone = { passwordFocusRequest.requestFocus() }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Password(
            text = stringResource(R.string.password),
            state = passwordState,
            imeAction = ImeAction.Next,
            onDone = { confirmationPasswordFocusRequest.requestFocus() }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Password(
            text = stringResource(R.string.confirm_password),
            state = confirmPasswordState,
            onDone = ::onSubmit,
            modifier = Modifier.focusRequester(confirmationPasswordFocusRequest)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = ::onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = emailState.isValid && passwordState.isValid && confirmPasswordState.isValid
        ) {
            Text(text = stringResource(R.string.create_account_action))
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Text(
                stringResource(R.string.sign_in_instead),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        navigation.navigate(View.Login.route)
                    }
            )
        }
    }
}

@Composable
@Preview(name = "Dark Mode")
fun AccountViewDarkMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = true) {
        RegisterView(navigation = navController)
    }
}

@Composable
@Preview(name = "Light Mode")
fun AccountViewLightMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = false) {
        RegisterView(navigation = navController)
    }
}