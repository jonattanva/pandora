package com.monolieta.pandora.android.ui.library

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.monolieta.pandora.android.ui.component.Grid

@Composable
fun LibraryView() {
    Grid()
}

@Composable
@Preview(showBackground = true)
private fun DefaultPreview() {
    LibraryView()
}

// https://developer.android.com/topic/libraries/architecture/viewmodel?gclid=CjwKCAjw3_KIBhA2EiwAaAAliurI8sK9Tsfy1NxxrT7YKaPvhXGdl7ZmPlk1oZPdssxP52kDN-JHdRoCavQQAvD_BwE&gclsrc=aw.ds#kotlin
