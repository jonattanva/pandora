package com.monolieta.pandora.android.ui.component

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import java.text.DateFormat
import java.util.*

@Composable
fun DateView(
    timestamp: Long,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle = LocalTextStyle.current
) {
    Text(
        text = format(timestamp),
        modifier = modifier,
        color = color,
        style = style
    )
}

private fun format(timestamp: Long): String {
    val simpleDateFormat = DateFormat.getDateInstance()
    return simpleDateFormat.format(Date(timestamp))
}