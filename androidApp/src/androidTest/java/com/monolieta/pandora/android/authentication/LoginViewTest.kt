package com.monolieta.pandora.android.authentication

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.monolieta.pandora.android.MainActivity
import com.monolieta.pandora.android.ui.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
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
    fun testInvalidPassword() {
        composeTestRule.onNodeWithTag(AVATAR_TAG)
            .performClick()

        composeTestRule.onNodeWithTag(EMAIL_INPUT_TAG)
            .performTextInput("solid.snake@pandora.com")

        composeTestRule.onNodeWithTag(LOGIN_ACTION_TAG)
            .performClick()

        /* Espresso.onView(withText("Use 8 characters or more for your password")).inRoot(
            withDecorView(not(`is`(composeTestRule.activity.window.decorView)))
        ).check(matches(isDisplayed())) */

        composeTestRule.onNodeWithTag(AVATAR_TAG)
            .assertDoesNotExist()
    }

    @Test
    fun testInvalidEmail() {
        composeTestRule.onNodeWithTag(AVATAR_TAG)
            .performClick()

        composeTestRule.onNodeWithTag(PASSWORD_INPUT_TAG)
            .performTextInput("123456789")

        composeTestRule.onNodeWithTag(LOGIN_ACTION_TAG)
            .performClick()

        /* Espresso.onView(withText("Invalid email")).inRoot(
            withDecorView(not(`is`(composeTestRule.activity.window.decorView)))
        ).check(matches(isDisplayed())) */

        composeTestRule.onNodeWithTag(AVATAR_TAG)
            .assertDoesNotExist()
    }
}