package com.gradation.lift.feature.login.sign_in

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.gradation.lift.feature.login.sign_in.component.HeaderView
import org.junit.Before
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HeaderViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            HeaderView()
        }
    }

    @Test
    fun `display header text`() {
        composeTestRule
            .onNode(
                hasText(
                    "매일매일 운동하고, 기록하고! \n" +
                            "나만의 운동파트너, 리프트"
                )
            )
            .assertExists()
    }
}