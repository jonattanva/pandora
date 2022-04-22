package com.monolieta.pandora.android.ui.authentication

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.ui.component.Email
import com.monolieta.pandora.android.ui.component.Form
import com.monolieta.pandora.android.ui.state.EmailState
import com.monolieta.pandora.android.ui.theme.PandoraTheme
import kotlinx.coroutines.launch

@Composable
fun RecoverView(
    navigation: NavHostController,
    viewModel: AuthenticationViewModel = viewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val loading = viewModel.loading
    val authenticationResult = viewModel.authenticationResult

    fun clickHandle(username: String) {
        scope.launch {
            viewModel.resetPassword(username)
        }
    }

/*
    if (recoverResult?.error != null) {
        Toast.makeText(context, recoverResult.error, Toast.LENGTH_LONG)
            .show()
    }*/

    FormView(
        loading = loading,
        onClick = ::clickHandle
    )
}

@Composable
private fun FormView(
    loading: Boolean,
    onClick: (String) -> Unit
) {
    val emailState = remember { EmailState() }
    val emailFocusRequest = remember { FocusRequester() }

    fun onSubmit() {
        onClick(emailState.value)
    }

    Form(loading = loading) {
        Text(stringResource(R.string.recover_title), fontSize = 30.sp)

        Spacer(modifier = Modifier.height(16.dp))
        Email(
            text = stringResource(R.string.email),
            state = emailState,
            onDone = ::onSubmit,
            modifier = Modifier.focusRequester(emailFocusRequest)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = ::onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = emailState.isValid
        ) {
            Text(text = stringResource(R.string.next_action))
        }
    }
}

@Composable
@Preview(name = "Dark Mode")
fun RecoverViewDarkMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = true) {
        RecoverView(navigation = navController)
    }
}

@Composable
@Preview(name = "Light Mode")
fun RecoverViewLightMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = false) {
        RecoverView(navigation = navController)
    }
}