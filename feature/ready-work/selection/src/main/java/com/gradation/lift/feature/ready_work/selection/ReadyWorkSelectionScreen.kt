package com.gradation.lift.feature.ready_work.selection

import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.ToggleCheckbox
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.ready_work.selection.component.WeekdayCardListView
import com.gradation.lift.feature.ready_work.selection.component.routine_list.LoadingRoutineListView
import com.gradation.lift.feature.ready_work.selection.data.*
import com.gradation.lift.feature.ready_work.selection.data.WeekdayCard
import com.gradation.lift.model.common.Weekday
import com.gradation.lift.navigation.navigation.navigateReadyWorkToMain
import com.gradation.lift.test.data.TestModelDataGenerator
import kotlinx.datetime.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun ReadyWorkSelectionRoute(
    navController: NavController,
    previousRoutineSetId: Int?,
    modifier: Modifier = Modifier,
    viewModel: ReadyWorkSelectionViewModel = hiltViewModel(),
) {

    val weekDate: List<WeekdayCard> by viewModel.weekDate.collectAsStateWithLifecycle()
    val routineSetRoutineSelection: RoutineSetRoutineSelectionUiState by viewModel.routineSetRoutineSelection.collectAsStateWithLifecycle()
    val selectedRoutineSetIdList by viewModel.selectedRoutineSetIdList.collectAsStateWithLifecycle()


    ReadyWorkSelectionScreen(
        modifier = modifier,
        weekday = weekDate,
        routineSetRoutineSelection = routineSetRoutineSelection,
        onBackClickTopBar = {
            navController.navigateReadyWorkToMain()
        },
        onClickWeekDayCard = viewModel.onClickWeekDayCard(),
        onClickStartWork = {},
        selectedRoutineSetIdList = selectedRoutineSetIdList,
        onUpdateRoutineSetIdList = viewModel.updateRoutineSetIdList(),
    )

    LaunchedEffect(key1 = true) {
        viewModel.updatePreviousRoutineSetId(previousRoutineSetId)
    }
    BackHandler(enabled = true, onBack = {
        navController.navigateReadyWorkToMain()
    })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ReadyWorkSelectionScreen(
    modifier: Modifier = Modifier,
    weekday: List<WeekdayCard>,
    routineSetRoutineSelection: RoutineSetRoutineSelectionUiState,
    onBackClickTopBar: () -> Unit,
    onClickWeekDayCard: (LocalDate) -> Unit,
    onClickStartWork: () -> Unit,
    onUpdateRoutineSetIdList: (Int, Boolean) -> Unit,
    selectedRoutineSetIdList: List<Int>,
) {

    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "루틴리스트 선택",
                onBackClickTopBar = onBackClickTopBar
            )
        },
        floatingActionButton = {
            LiftButton(
                contentPadding = PaddingValues(
                    start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp
                ),
                onClick = onClickStartWork,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                    ),
                enabled = selectedRoutineSetIdList.isNotEmpty()
            ) {
                Text(
                    text = "운동시작하기 (${selectedRoutineSetIdList.size}개)",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no5,
                )
                Spacer(modifier = modifier.padding(2.dp))
                Icon(
                    painterResource(id = LiftIcon.ChevronRight),
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxHeight()
                        .width(8.dp)
                )

            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Column(
            modifier = modifier.padding(it)
        ) {
            WeekdayCardListView(
                weekday = weekday,
                modifier = modifier,
                onClickWeekDayCard = onClickWeekDayCard
            )

            when (routineSetRoutineSelection) {
                RoutineSetRoutineSelectionUiState.Empty -> {

                }
                is RoutineSetRoutineSelectionUiState.Fail -> {


                }
                RoutineSetRoutineSelectionUiState.Loading -> {
                    LoadingRoutineListView(modifier = modifier)
                }
                is RoutineSetRoutineSelectionUiState.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(all = 20.dp),
                        modifier = modifier,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        itemsIndexed(routineSetRoutineSelection.routineSetRoutineSelection) { _, routineSetRoutine ->
                            Column(
                                modifier = modifier
                                    .background(LiftTheme.colorScheme.no5)
                                    .border(
                                        width = 1.dp,
                                        color = LiftTheme.colorScheme.no8,
                                        shape = RoundedCornerShape(size = 16.dp)
                                    )
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
                                        checked = routineSetRoutine.selected,
                                        onCheckedChange = { checked ->
                                            onUpdateRoutineSetIdList(
                                                routineSetRoutine.routineSetRoutine.id,
                                                checked
                                            )
                                        },
                                        modifier = modifier.size(26.dp)
                                    )
                                    Spacer(modifier = modifier.padding(4.dp))
                                    Column(
                                        horizontalAlignment = Alignment.Start,
                                        verticalArrangement = Arrangement.Center,
                                        modifier = modifier.weight(1f)
                                    ) {
                                        Text(
                                            text = routineSetRoutine.routineSetRoutine.name,
                                            style = LiftTheme.typography.no2,
                                            color = if (routineSetRoutine.selected) LiftTheme.colorScheme.no4 else LiftTheme.colorScheme.no9,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1
                                        )
                                        Text(
                                            text = routineSetRoutine.routineSetRoutine.description,
                                            style = LiftTheme.typography.no4,
                                            color = LiftTheme.colorScheme.no9,
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1
                                        )
                                    }
                                    Spacer(modifier = modifier.padding(4.dp))
                                    Icon(
                                        painterResource(id = LiftIcon.ChevronRight),
                                        contentDescription = null,
                                        modifier = modifier
                                            .width(10.dp)
                                            .height(16.dp)
                                    )
                                }
                                routineSetRoutine.routineSetRoutine.routine.forEach { routine ->
                                    Divider(
                                        modifier = modifier,
                                        thickness = 4.dp,
                                        color = LiftTheme.colorScheme.no1,
                                    )
                                    Spacer(modifier = modifier.padding(2.dp))
                                    Column(
                                        modifier = modifier.padding(
                                            start = 16.dp,
                                            top = 12.dp,
                                            bottom = 12.dp,
                                            end = 24.dp
                                        )
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = modifier.fillMaxWidth()
                                        ) {
                                            Text(
                                                text = routine.workCategory.name,
                                                style = LiftTheme.typography.no3,
                                                color = LiftTheme.colorScheme.no9,
                                            )
                                            Icon(
                                                painterResource(id = LiftIcon.ChevronRight),
                                                contentDescription = null,
                                                modifier = modifier
                                                    .width(10.dp)
                                                    .height(16.dp)
                                            )
                                        }
                                    }
                                    Spacer(modifier = modifier.padding(2.dp))
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
            routineSetRoutineSelection = RoutineSetRoutineSelectionUiState.Success(
                routineSetRoutineSelection = TestModelDataGenerator.Routine.routineSetRoutineModelList.map {
                    RoutineSetRoutineSelection(
                        routineSetRoutine = it,
                        selected = false
                    )
                }
            ),
            onBackClickTopBar = {},
            onClickWeekDayCard = {},
            onClickStartWork = {},
            onUpdateRoutineSetIdList = { _, _ -> },
            selectedRoutineSetIdList = listOf()
        )

    }
}