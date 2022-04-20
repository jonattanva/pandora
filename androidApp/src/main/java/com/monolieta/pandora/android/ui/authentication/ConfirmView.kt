package com.monolieta.pandora.android.ui.authentication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.ui.component.Number
import com.monolieta.pandora.android.ui.component.Form
import com.monolieta.pandora.android.ui.state.InputState
import kotlinx.coroutines.launch

@Composable
fun ConfirmView(
    username: String?,
    navigation: NavHostController,
    viewModel: AuthenticationViewModel = viewModel(factory = AuthenticationViewModelFactory())
) {
    val scope = rememberCoroutineScope()

    val loading = viewModel.loading
    val authenticationResult = viewModel.authenticationResult

    fun clickHandle(code: String) {
        scope.launch {
            username?.let {
                // TODO: NO ESTA ENVIANDO AL HOME!!
                viewModel.confirmSignUp(it, code)
            }
        }
    }

    fun resendCodeHandle() {
        scope.launch {
            username?.let {
                viewModel.resendSignUpCode(it)
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
    val codeState = remember { InputState() }

    fun onSubmit() {
        onClick(codeState.value)
    }

    Form(loading = loading) {
        Text(stringResource(R.string.verification_title), fontSize = 30.sp)

        Spacer(modifier = Modifier.height(16.dp))
        Number(
            text = stringResource(R.string.code),
            state = codeState,
            onDone = ::onSubmit
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = ::onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
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