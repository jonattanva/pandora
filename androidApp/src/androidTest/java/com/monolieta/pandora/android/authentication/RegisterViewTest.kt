package com.monolieta.pandora.android.authentication

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.monolieta.pandora.android.MainActivity
import com.monolieta.pandora.android.ui.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterViewTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            NavigationView()
        }
    }

    @Test
    fun testInvalidEmail() {
        composeTestRule.onNodeWithTag(AVATAR_TAG)
            .performClick()

        composeTestRule.onNodeWithTag(CREATE_ACCOUNT_LINK_TAG)
            .performClick()

        composeTestRule.onNodeWithTag(EMAIL_INPUT_TAG)
            .performTextInput("solid.snake")

        composeTestRule.onNodeWithTag(PASSWORD_INPUT_TAG)
            .performTextInput("1")

        composeTestRule.onNodeWithText("Invalid email")
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(CREATE_ACCOUNT_ACTION_TAG)
            .assertIsNotEnabled()
    }

    @Test
    fun testInvalidPassword() {
        composeTestRule.onNodeWithTag(AVATAR_TAG)
            .performClick()

        composeTestRule.onNodeWithTag(CREATE_ACCOUNT_LINK_TAG)
            .performClick()

        composeTestRule.onNodeWithTag(EMAIL_INPUT_TAG)
            .performTextInput("solid.snake@pandora.com")

        composeTestRule.onNodeWithTag(PASSWORD_INPUT_TAG)
            .performTextInput("1")

        composeTestRule.onNodeWithTag(CONFIRM_INPUT_TAG)
            .performTextInput("1")

        composeTestRule.onNodeWithText("Use 8 characters or more for your password")
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(CREATE_ACCOUNT_ACTION_TAG)
            .assertIsNotEnabled()
    }

    @Test
    fun testInvalidConfirmPassword() {
        composeTestRule.onNodeWithTag(AVATAR_TAG)
            .performClick()

        composeTestRule.onNodeWithTag(CREATE_ACCOUNT_LINK_TAG)
            .performClick()

        composeTestRule.onNodeWithTag(EMAIL_INPUT_TAG)
            .performTextInput("solid.snake@pandora.com")

        composeTestRule.onNodeWithTag(PASSWORD_INPUT_TAG)
            .performTextInput("123456789")

        composeTestRule.onNodeWithTag(CONFIRM_INPUT_TAG)
            .performTextInput("1")

        composeTestRule.onNodeWithTag(EMAIL_INPUT_TAG)
            .performClick()

        composeTestRule.onNodeWithText("Passwords don't match")
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(CREATE_ACCOUNT_ACTION_TAG)
            .assertIsNotEnabled()
    }
}