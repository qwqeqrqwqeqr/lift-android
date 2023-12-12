package com.gradation.lift.test.rule

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule

class ComposeTestRule {

    val composeAndroidTestRule = createAndroidComposeRule<ComponentActivity>()

    val composeTestRule = createComposeRule()
}