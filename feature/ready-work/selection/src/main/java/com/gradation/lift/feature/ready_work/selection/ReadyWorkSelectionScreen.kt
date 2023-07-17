package com.gradation.lift.feature.ready_work.selection

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.feature.ready_work.selection.component.WeekdayView
import com.gradation.lift.feature.ready_work.selection.data.WeekdayCard
import com.gradation.lift.model.common.Weekday
import kotlinx.datetime.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun ReadyWorkSelectionRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ReadyWorkSelectionViewModel = hiltViewModel(),
) {

    val weekDate: List<WeekdayCard> by viewModel.weekDate.collectAsStateWithLifecycle()

    ReadyWorkSelectionScreen(
        modifier = modifier,
        weekday = weekDate,
        onBackClickTopBar = {},
        onClickWeekDayCard =  viewModel.onClickWeekDayCard()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ReadyWorkSelectionScreen(
    modifier: Modifier = Modifier,
    weekday: List<WeekdayCard>,
    onBackClickTopBar: () -> Unit,
    onClickWeekDayCard: (LocalDate) -> Unit,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "루틴리스트 선택",
                onBackClickTopBar = onBackClickTopBar
            )
        },
    ) {
        Column(
            modifier = modifier.padding(it)
        ) {
            WeekdayView(
                weekday = weekday,
                modifier = modifier,
                onClickWeekDayCard = onClickWeekDayCard
            )
        }
    }
}

@Composable
@Preview
fun ReadyWorkSelectionPreview() {
    LiftMaterialTheme {
        ReadyWorkSelectionScreen(
            modifier = Modifier,
            weekday = listOf(
                WeekdayCard(weekday = Weekday.Monday()),
                WeekdayCard(weekday = Weekday.Tuesday()),
                WeekdayCard(weekday = Weekday.Wednesday()),
                WeekdayCard(weekday = Weekday.Thursday()),
                WeekdayCard(weekday = Weekday.Friday()),
                WeekdayCard(weekday = Weekday.Saturday()),
                WeekdayCard(weekday = Weekday.Sunday(), selected = true)
            ),
            onBackClickTopBar = {},
            onClickWeekDayCard = {}
        )

    }
}