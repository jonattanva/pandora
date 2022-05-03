package com.monolieta.pandora.android.ui.component

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monolieta.pandora.android.ui.CODE_INPUT_TAG
import com.monolieta.pandora.android.ui.state.InputState
import com.monolieta.pandora.android.ui.theme.PandoraTheme

@Composable
fun Code(
    state: InputState
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        val focusManager = LocalFocusManager.current
        for (index in 0..5) {
            val value = state.value.getOrNull(index)
                ?.takeIf { it.isDigit() }
                ?.toString() ?: ""

            OutlinedTextField(
                value = value,
                onValueChange = {
                    Log.e("TAG", "Change: " + index + " - " + it)
                    state.value = state.value + it
                    focusManager.moveFocus(FocusDirection.Right)
                },
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .testTag(CODE_INPUT_TAG)
                    .onFocusChanged { focusState ->
                        state.onFocusChange(focusState.isFocused)
                        if (!focusState.isFocused) {
                            state.enableShowErrors()
                        }
                    },
                textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(
                    imeAction = if (index == 6) {
                        ImeAction.Done
                    } else ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )

            if (index < 6) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }


/*

        Spacer(modifier = Modifier.width(4.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            maxLines = 1,
            singleLine = true,
            isError = state.isError(),
            modifier = Modifier.weight(1f),
            textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.width(4.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            maxLines = 1,
            singleLine = true,
            isError = state.isError(),
            modifier = Modifier.weight(1f),
            textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.width(4.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            maxLines = 1,
            singleLine = true,
            isError = state.isError(),
            modifier = Modifier.weight(1f),
            textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.width(4.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            maxLines = 1,
            singleLine = true,
            isError = state.isError(),
            modifier = Modifier.weight(1f),
            textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(modifier = Modifier.width(4.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            maxLines = 1,
            singleLine = true,
            isError = state.isError(),
            modifier = Modifier.weight(1f),
            textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            )
        )
 */
    }
}

@Composable
@Preview(name = "Dark Mode")
fun CodeDarkMode() {
    PandoraTheme(darkTheme = true) {
        Code(InputState())
    }
}

@Composable
@Preview(name = "Light Mode")
fun CodeLightMode() {
    PandoraTheme(darkTheme = false) {
        Code(InputState())
    }
}