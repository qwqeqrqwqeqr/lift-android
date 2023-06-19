package com.gradation.lift.feature.routine

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.gradation.lift.feature.routine.viewmodel.WeekDateRoutineUiState
import com.gradation.lift.feature.routine.viewmodel.WeekDateUiState
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.junit.Rule
import org.junit.Test


class RoutineScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()


    @Test
    fun loading_showsLoadingSpinner() {
        composeTestRule.setContent {
            RoutineScreen(
                currentDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                weekDateRoutineUiState = WeekDateRoutineUiState.Loading,
                weekDateUiState = WeekDateUiState(emptyList()),
                navigateCreateRoutineClick= {},
                weekCardClick = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(
                composeTestRule.activity.resources.getString(R.string.test),
            )
            .assertExists()
    }
}
