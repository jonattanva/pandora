package com.monolieta.pandora.android.authentication

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.monolieta.pandora.android.MainActivity
import com.monolieta.pandora.android.ui.NavigationView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginViewTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            NavigationView()
        }
    }

    @Test
    fun testValidateTitle() {
        composeTestRule.onNodeWithText("Lists").assertIsDisplayed()
    }
}