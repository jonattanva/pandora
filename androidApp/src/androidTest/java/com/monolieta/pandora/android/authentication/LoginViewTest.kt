package com.monolieta.pandora.android.authentication

import android.Manifest;

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.screenshot.Screenshot
import com.monolieta.pandora.android.MainActivity
import com.monolieta.pandora.android.ui.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginViewTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
/*
    @get:Rule
    var permissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE)
*/
    @Before
    fun setUp() {
        composeTestRule.setContent {
            NavigationView()
        }
    }

    @Test
    fun testInvalidPassword() {
        // Screenshot.capture().process()

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

        // Screenshot.capture(composeTestRule.activity).process()
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