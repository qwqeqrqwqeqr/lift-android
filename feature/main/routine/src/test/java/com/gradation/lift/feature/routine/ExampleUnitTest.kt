package com.gradation.lift.feature.routine

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowLog


@RunWith(RobolectricTestRunner::class)
class RobolectricTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun `test screen text`() {
        composeTestRule.setContent {
            Text(STRING_TEST)
        }

        composeTestRule.onNodeWithText(STRING_TEST)
            .assertIsDisplayed()
    }
}
const val STRING_TEST = "Jetpack Compose with Robolectric"
