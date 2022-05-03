package com.monolieta.pandora.android.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monolieta.pandora.android.ui.PASSWORD_INPUT_TAG
import com.monolieta.pandora.android.ui.state.InputState
import com.monolieta.pandora.android.ui.theme.PandoraTheme

@Composable
fun Password(
    text: String,
    state: InputState,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onDone: () -> Unit = {},
    testTag: String = PASSWORD_INPUT_TAG

) {
    val show = remember { mutableStateOf(false) }

    val visualTransformation = if (show.value) {
        VisualTransformation.None
    } else PasswordVisualTransformation()

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = ""
                )
            },
            trailingIcon = {
                if (show.value) {
                    IconButton(onClick = { show.value = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = ""
                        )
                    }
                } else {
                    IconButton(onClick = { show.value = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = ""
                        )
                    }
                }
            },
            value = state.value,
            onValueChange = { state.value = it },
            modifier = modifier
                .fillMaxWidth()
                .testTag(testTag)
                .onFocusChanged { focusState ->
                    state.onFocusChange(focusState.isFocused)
                    if (!focusState.isFocused) {
                        state.enableShowErrors()
                    }
                },
            maxLines = 1,
            isError = state.isError(),
            visualTransformation = visualTransformation,
            keyboardActions = KeyboardActions(
                onDone = { onDone() }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = imeAction,
                keyboardType = KeyboardType.Password
            )
        )

        state.error()?.let {
            Text(
                stringResource(it),
                fontSize = 12.sp,
                color = Color.Red,
                modifier = Modifier.padding(start = 14.dp, top = 4.dp)
            )
        }
    }

}

@Composable
@Preview(name = "Dark Mode")
fun PasswordDarkMode() {
    PandoraTheme(darkTheme = true) {
        Password("Password", InputState())
    }
}

@Composable
@Preview(name = "Light Mode")
fun PasswordLightMode() {
    PandoraTheme(darkTheme = false) {
        Password("Password", InputState())
    }
}