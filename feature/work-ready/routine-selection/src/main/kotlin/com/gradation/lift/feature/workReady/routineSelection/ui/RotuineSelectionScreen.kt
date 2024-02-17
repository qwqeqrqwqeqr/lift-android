package com.gradation.lift.feature.workReady.routineSelection.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.bottomBar.LiftDefaultBottomBar
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.workReady.routineSelection.data.model.LabelFilterType
import com.gradation.lift.feature.workReady.routineSelection.data.model.SortType
import com.gradation.lift.feature.workReady.routineSelection.data.model.WeekdayFilterType
import com.gradation.lift.feature.workReady.routineSelection.data.state.RoutineListInfoState
import com.gradation.lift.feature.workReady.routineSelection.data.state.RoutineListScreenState
import com.gradation.lift.feature.workReady.routineSelection.data.state.RoutineListUiState
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
    updateRoutineSetIdSet: (Set<Int>) -> Unit,
    popBackStack: () -> Unit,
    navigateRoutineSelectionToReadyInWorkReadyGraph: () -> Unit,
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
                        RoutineListView(
                            modifier,
                            routineSetRoutineListUiState.routineSetRoutineList,
                            routineListInfoState,
                            routineListScreenState,
                        )
                    }
                    LiftDefaultBottomBar(
                        modifier = modifier

                    ) {
                        with(routineListInfoState.selectedRoutineList.toSet()) {
                            LiftSolidButton(
                                modifier = modifier.fillMaxWidth(),
                                text = "운동시작하기(${size}개)",
                                onClick = {
                                    updateRoutineSetIdSet(this)
                                    navigateRoutineSelectionToReadyInWorkReadyGraph()
                                },
                                enabled = routineListInfoState.selectedRoutineList.toList()
                                    .isNotEmpty()
                            )
                        }
                    }
                }
            }
        }
    }
}




