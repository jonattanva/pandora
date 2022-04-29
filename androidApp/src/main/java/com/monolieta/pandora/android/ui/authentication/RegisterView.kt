package com.monolieta.pandora.android.ui.authentication

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.monolieta.pandora.android.R
import com.monolieta.pandora.android.ui.*
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
    viewModel: AuthenticationViewModel = viewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val isLoading = viewModel.loading
    val authenticationResult = viewModel.authenticationResult

    fun onClick(user: User) {
        scope.launch {
            viewModel.signUp(user)
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

    RegisterFormView(
        loading = isLoading,
        navigation = navigation,
        onClick = ::onClick
    )
}

@Composable
private fun RegisterFormView(
    loading: Boolean,
    navigation: NavHostController,
    onClick: (User) -> Unit = {}
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
        Back(navigation = navigation)

        Spacer(modifier = Modifier.height(64.dp))
        Text(
            stringResource(R.string.create_account),
            fontSize = 34.sp,
        )

        Spacer(modifier = Modifier.height(32.dp))
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
            onDone = { confirmationPasswordFocusRequest.requestFocus() },
            testTag = PASSWORD_INPUT_TAG
        )

        Spacer(modifier = Modifier.height(16.dp))
        Password(
            text = stringResource(R.string.confirm_password),
            state = confirmPasswordState,
            onDone = ::onSubmit,
            modifier = Modifier.focusRequester(confirmationPasswordFocusRequest),
            testTag = CONFIRM_INPUT_TAG
        )

        Spacer(modifier = Modifier.height(48.dp))
        Button(
            onClick = ::onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag(CREATE_ACCOUNT_ACTION_TAG),
            enabled = emailState.isValid && passwordState.isValid && confirmPasswordState.isValid
        ) {
            Text(text = stringResource(R.string.sign_up))
        }
    }
}

@Composable
@Preview(
    name = "Dark Mode",
    showBackground = true
)
fun AccountViewDarkMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = true) {
        RegisterFormView(
            loading = false,
            navigation = navController
        )
    }
}

@Composable
@Preview(
    name = "Light Mode",
    showBackground = true
)
fun AccountViewLightMode() {
    val navController = rememberNavController()
    PandoraTheme(darkTheme = false) {
        RegisterFormView(
            loading = false,
            navigation = navController
        )
    }
}