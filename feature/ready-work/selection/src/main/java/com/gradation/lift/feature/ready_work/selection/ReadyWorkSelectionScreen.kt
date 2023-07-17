package com.gradation.lift.feature.ready_work.selection

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.ToggleCheckbox
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.ready_work.selection.component.WeekdayView
import com.gradation.lift.feature.ready_work.selection.component.routine_list.LoadingRoutineListView
import com.gradation.lift.feature.ready_work.selection.data.ReadyWorkSelectionViewModel
import com.gradation.lift.feature.ready_work.selection.data.WeekDateRoutineUiState
import com.gradation.lift.feature.ready_work.selection.data.WeekdayCard
import com.gradation.lift.model.common.Weekday
import com.gradation.lift.navigation.navigation.navigateHomeToReadyWorkGraph
import com.gradation.lift.test.data.TestModelDataGenerator
import kotlinx.datetime.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun ReadyWorkSelectionRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ReadyWorkSelectionViewModel = hiltViewModel(),
) {

    val weekDate: List<WeekdayCard> by viewModel.weekDate.collectAsStateWithLifecycle()
    val weekDateRoutine: WeekDateRoutineUiState by viewModel.weekDateRoutine.collectAsStateWithLifecycle()

    ReadyWorkSelectionScreen(
        modifier = modifier,
        weekday = weekDate,
        weekDateRoutine = weekDateRoutine,
        onBackClickTopBar = { navController.navigateHomeToReadyWorkGraph() },
        onClickWeekDayCard = viewModel.onClickWeekDayCard()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ReadyWorkSelectionScreen(
    modifier: Modifier = Modifier,
    weekday: List<WeekdayCard>,
    weekDateRoutine: WeekDateRoutineUiState,
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

            when (weekDateRoutine) {
                WeekDateRoutineUiState.Empty -> {

                }
                is WeekDateRoutineUiState.Fail -> {


                }
                WeekDateRoutineUiState.Loading -> {
                    LoadingRoutineListView(modifier = modifier)
                }
                is WeekDateRoutineUiState.Success -> {
                    Column(
                        modifier = modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(
                            12.dp,
                            Alignment.CenterVertically
                        ),
                    ) {
                        weekDateRoutine.weekDateRoutine.forEach { routineSetRoutine ->
                            Column(
                                modifier = modifier
                                    .background(LiftTheme.colorScheme.no5)
                                    .border(
                                        width = 1.dp,
                                        color = LiftTheme.colorScheme.no8,
                                        shape = RoundedCornerShape(size = 16.dp)
                                    )
                                    .clickable { }
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.Start,
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = modifier.padding(
                                        start = 16.dp,
                                        top = 12.dp,
                                        bottom = 12.dp,
                                        end = 24.dp
                                    )
                                ) {
                                    ToggleCheckbox(
                                        checked = false,
                                        onCheckedChange = {},
                                        modifier = modifier.size(26.dp)
                                    )
                                    Spacer(modifier = modifier.padding(4.dp))
                                    Column(
                                        horizontalAlignment = Alignment.Start,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = routineSetRoutine.name,
                                            style = LiftTheme.typography.no2,
                                            color = LiftTheme.colorScheme.no9,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1
                                        )
                                        Text(
                                            text = routineSetRoutine.description,
                                            style = LiftTheme.typography.no4,
                                            color = LiftTheme.colorScheme.no9,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1
                                        )
                                    }
                                    Spacer(modifier = modifier.padding(4.dp))
                                    Icon(
                                        painterResource(id = LiftIcon.ChevronRightSharp),
                                        contentDescription = null,
                                    )
                                }
                                routineSetRoutine.routine.forEach { routine ->

                                    Divider(
                                        modifier = modifier,
                                        thickness = 4.dp,
                                        color = LiftTheme.colorScheme.no1,
                                    )
                                    Column(
                                        modifier = modifier.padding(
                                            start = 16.dp,
                                            top = 12.dp,
                                            bottom = 12.dp,
                                            end = 24.dp
                                        )
                                    ) {
                                        Text(
                                            text = routine.workCategory.name,
                                            style = LiftTheme.typography.no3,
                                            color = LiftTheme.colorScheme.no9,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
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
            weekDateRoutine = WeekDateRoutineUiState.Success(
                weekDateRoutine = TestModelDataGenerator.Routine.routineSetRoutineModelList
            ),
            onBackClickTopBar = {},
            onClickWeekDayCard = {}
        )

    }
}