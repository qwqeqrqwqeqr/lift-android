package com.gradation.lift.designsystem

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.gradation.lift.designsystem.component.LiftButton
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class RobolectricTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `test lift button`() {
        composeTestRule.setContent {
            LiftButton(onClick = {}) {
                Text(TEST_TAG)
            }
        }

        composeTestRule.onNodeWithText(TEST_TAG)
            .assertIsDisplayed()
    }
}

const val TEST_TAG = "리프트"
