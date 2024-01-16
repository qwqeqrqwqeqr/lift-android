package com.gradation.lift.feature.workReady.routineSelection.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.workReady.routineSelection.data.model.LabelFilterType
import com.gradation.lift.feature.workReady.routineSelection.data.model.SortType
import com.gradation.lift.feature.workReady.routineSelection.data.model.WeekdayFilterType
import com.gradation.lift.feature.workReady.routineSelection.data.state.RoutineListUiState
import com.gradation.lift.feature.workReady.routineSelection.data.state.RoutineListInfoState
import com.gradation.lift.feature.workReady.routineSelection.data.state.RoutineListScreenState
import com.gradation.lift.feature.workReady.routineSelection.data.state.SortFilterState
import com.gradation.lift.feature.workReady.routineSelection.ui.component.RoutineListView
import com.gradation.lift.feature.workReady.routineSelection.ui.component.SearchSortFilterView

@Composable
internal fun RoutineSelectionScreen(
    modifier: Modifier = Modifier,
    routineListScreenState: RoutineListScreenState,
    routineListInfoState: RoutineListInfoState,
    routineSetRoutineListUiState: RoutineListUiState,
    sortFilterState: SortFilterState,
    labelFilterType: LabelFilterType,
    weekdayFilterType: WeekdayFilterType,
    searchFilterText: String,
    sortType: SortType,
    popBackStack: () -> Unit,
    navigateRoutineSelectionToReadyInWorkReadyGraph: (String) -> Unit,
) {

    Scaffold(
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = "루틴 선택",
                backgroundColor = LiftTheme.colorScheme.no5,
                onClick = popBackStack
            )
        }
    ) { padding ->
        when (routineSetRoutineListUiState) {
            is RoutineListUiState.Fail -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(LiftTheme.colorScheme.no17)
                )
            }

            RoutineListUiState.Loading -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(LiftTheme.colorScheme.no17)
                )
            }

            is RoutineListUiState.Success -> {
                Column(
                    modifier = modifier
                        .background(LiftTheme.colorScheme.no17)
                        .padding(padding)
                ) {
                    Column(
                        modifier = modifier
                            .weight(1f)
                            .background(LiftTheme.colorScheme.no5)
                    ) {
                        AnimatedVisibility(routineListScreenState.searchSortFilterView) {
                            SearchSortFilterView(
                                modifier,
                                sortFilterState,
                                searchFilterText,
                                routineSetRoutineListUiState.routineSetRoutineList,
                                weekdayFilterType,
                                labelFilterType,
                                sortType,
                                routineListScreenState
                            )
                        }
                        if (routineSetRoutineListUiState.routineSetRoutineList.isNotEmpty())
                            RoutineListView(
                                modifier,
                                routineSetRoutineListUiState.routineSetRoutineList,
                                routineListInfoState,
                                routineListScreenState,
                            )
                        else
                            Column(
                                modifier = modifier
                                    .fillMaxSize()
                                    .background(LiftTheme.colorScheme.no17),
                                verticalArrangement = Arrangement.spacedBy(
                                    LiftTheme.space.space16,
                                    Alignment.CenterVertically
                                ),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    modifier = modifier.size(LiftTheme.space.space72),
                                    painter = painterResource(id = R.drawable.open_box),
                                    contentDescription = "emptyBox",
                                )
                                LiftText(
                                    textStyle = LiftTextStyle.No4,
                                    text = "조건에 맞는 루틴이 존재하지 않네요..",
                                    color = LiftTheme.colorScheme.no2,
                                    textAlign = TextAlign.Center
                                )
                            }
                    }
                    LiftDefaultContainer(
                        modifier = modifier
                            .background(LiftTheme.colorScheme.no5)
                            .fillMaxWidth(),
                        shape = RectangleShape,
                        verticalPadding = LiftTheme.space.space10,
                        horizontalPadding = LiftTheme.space.space20
                    ) {
                        LiftSolidButton(
                            modifier = modifier.fillMaxWidth(),
                            text = "운동시작하기(${routineListInfoState.selectedRoutineList.toList().size}개)",
                            onClick = {
                                navigateRoutineSelectionToReadyInWorkReadyGraph(routineListInfoState.selectedRoutineList.joinToString("|"))
                            },
                            enabled = routineListInfoState.selectedRoutineList.toList().isNotEmpty()
                        )
                    }
                }
            }
        }
    }
}




