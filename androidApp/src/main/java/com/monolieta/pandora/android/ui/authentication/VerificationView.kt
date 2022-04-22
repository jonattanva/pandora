package com.monolieta.pandora.android.ui.authentication

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.ui.component.Number
import com.monolieta.pandora.android.ui.component.Form
import com.monolieta.pandora.android.ui.state.CodeState
import com.monolieta.pandora.android.ui.theme.PandoraTheme
import com.monolieta.pandora.model.User
import kotlinx.coroutines.launch

@Composable
fun VerificationView(
    user: User?,
    navigation: NavHostController,
    viewModel: AuthenticationViewModel = viewModel()
) {
    val scope = rememberCoroutineScope()

    val loading = viewModel.loading
    val authenticationResult = viewModel.authenticationResult

    fun clickHandle(code: String) {
        scope.launch {
            user?.let {
                viewModel.confirmSignUp(it, code)
            }
        }
    }

    fun resendCodeHandle() {
        scope.launch {
            user?.let {
                viewModel.resendSignUpCode(it.email)
            }
        }
    }

    authenticationResult?.route?.let {
        viewModel.clear()
        navigation.navigate(it)
    }

    FormView(
        loading = loading,
        onClick = ::clickHandle,
        onResendCode = ::resendCodeHandle
    )
}

@Composable
private fun FormView(
    loading: Boolean,
    onClick: (String) -> Unit,
    onResendCode: () -> Unit
) {
    val codeState = remember { CodeState() }
    val codeFocusRequest = remember { FocusRequester() }

    fun onSubmit() {
        onClick(codeState.value)
    }

    Form(loading = loading) {
        Text(stringResource(R.string.verification_title), fontSize = 30.sp)

        Spacer(modifier = Modifier.height(16.dp))
        Number(
            text = stringResource(R.string.code),
            state = codeState,
            onDone = ::onSubmit,
            modifier = Modifier.focusRequester(codeFocusRequest)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = ::onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = codeState.isValid
        ) {
            Text(text = stringResource(R.string.verify_action))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(stringResource(R.string.verification_code))
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                stringResource(R.string.resend_code),
                modifier = Modifier
                    .clickable { onResendCode() },
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
@Preview(name = "Dark Mode")
fun ConfirmViewDarkMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = true) {
        VerificationView(
            user = User(
                id = "1",
                email = "solid.snake@monolieta.com"
            ),
            navigation = navController
        )
    }
}

@Composable
@Preview(name = "Light Mode")
fun ConfirmViewLightMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = false) {
        VerificationView(
            user = User(
                id = "1",
                email = "solid.snake@monolieta.com"
            ),
            navigation = navController
        )
    }
}