package com.gradation.lift.feature.routine

import androidx.compose.ui.test.onNodeWithContentDescription
import com.gradation.lift.feature.routine.viewmodel.WeekDateRoutineUiState
import com.gradation.lift.feature.routine.viewmodel.WeekDateUiState
import com.gradation.lift.test.rule.ComposeTestRule
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class RoutineScreenTest {

    @get:Rule
    val composeAndroidTestRule  = ComposeTestRule().composeAndroidTestRule



    @Before
    fun setup() {
        composeAndroidTestRule.activity.apply {

        }
    }

    @Test
    fun test_loading_showsLoading_spinner() {
        composeAndroidTestRule.setContent {
            com.gradation.lift.feature.routine.RoutineScreen(
                currentDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                weekDateRoutineUiState = com.gradation.lift.feature.routine.viewmodel.WeekDateRoutineUiState.Loading,
                weekDateUiState = com.gradation.lift.feature.routine.viewmodel.WeekDateUiState(
                    emptyList()
                ),
                navigateCreateRoutineClick = {},
                weekCardClick = {}
            )
        }

        composeAndroidTestRule
            .onNodeWithContentDescription(
                composeAndroidTestRule.activity.resources.getString(R.string.test),
            )
            .assertExists()
    }
}
