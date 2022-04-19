package com.monolieta.pandora.android.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monolieta.pandora.android.ui.state.InputState
import com.monolieta.pandora.android.ui.theme.PandoraTheme

@Composable
fun Email(
    text: String,
    state: InputState,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Done,
    onDone: () -> Unit = {}
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = ""
                )
            },
            value = state.value,
            onValueChange = { state.value = it },
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    state.onFocusChange(focusState.isFocused)
                    if (!focusState.isFocused) {
                        state.enableShowErrors()
                    }
                },
            maxLines = 1,
            isError = state.isError(),
            keyboardActions = KeyboardActions(
                onDone = { onDone() }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = imeAction,
                keyboardType = KeyboardType.Email
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

/*
@Composable
@Preview(name = "Dark Mode")
fun EmailDarkMode() {
    PandoraTheme(darkTheme = true) {
        Email("Email", InputState())
    }
}

@Composable
@Preview(name = "Light Mode")
fun EmailLightMode() {
    PandoraTheme(darkTheme = false) {
        Email("Email", InputState())
    }
}
*/
@Preview(name = "Light Mode")
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PreviewEmailInput() {
    PandoraTheme {
        Email("Email", InputState())
    }
}