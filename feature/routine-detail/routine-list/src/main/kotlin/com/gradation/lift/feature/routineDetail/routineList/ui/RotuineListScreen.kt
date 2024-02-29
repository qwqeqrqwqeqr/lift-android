package com.gradation.lift.feature.routineDetail.routineList.ui

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
import com.gradation.lift.feature.routineDetail.routineList.data.model.LabelFilterType
import com.gradation.lift.feature.routineDetail.routineList.data.model.SortType
import com.gradation.lift.feature.routineDetail.routineList.data.model.WeekdayFilterType
import com.gradation.lift.feature.routineDetail.routineList.data.state.RoutineListInfoState
import com.gradation.lift.feature.routineDetail.routineList.data.state.RoutineListScreenState
import com.gradation.lift.feature.routineDetail.routineList.data.state.RoutineListUiState
import com.gradation.lift.feature.routineDetail.routineList.data.state.SortFilterState
import com.gradation.lift.feature.routineDetail.routineList.ui.component.RoutineListView
import com.gradation.lift.feature.routineDetail.routineList.ui.component.SearchSortFilterView
import com.gradation.lift.ui.extensions.focusClearManager

@Composable
internal fun RoutineListScreen(
    modifier: Modifier = Modifier,
    routineListScreenState: RoutineListScreenState,
    routineListInfoState: RoutineListInfoState,
    routineSetRoutineListUiState: RoutineListUiState,
    sortFilterState: SortFilterState,
    labelFilterType: LabelFilterType,
    weekdayFilterType: WeekdayFilterType,
    searchFilterText: String,
    sortType: SortType,
    navigateRoutineDetailGraphToHomeGraph: () -> Unit,
    navigateRoutineDetailGraphToCreateRoutineGraph: () -> Unit,
    navigateRoutineListToRoutineInRoutineDetailGraph: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = "내 루틴",
                backgroundColor = LiftTheme.colorScheme.no5,
                onClick = navigateRoutineDetailGraphToHomeGraph
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
                        .focusClearManager(routineListScreenState.focusManager)
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
                            routineSetRoutineListUiState.routineListState.routineList,
                            weekdayFilterType,
                            labelFilterType,
                            sortType,
                            routineListScreenState
                        )
                        RoutineListView(
                            modifier,
                            routineSetRoutineListUiState.routineListState.routineList,
                            routineListInfoState,
                            routineListScreenState,
                            navigateRoutineListToRoutineInRoutineDetailGraph
                        )
                    }

                    LiftDefaultBottomBar(
                        modifier = modifier

                    ) {
                        LiftSolidButton(
                            modifier = modifier.fillMaxWidth(),
                            text = "루틴 추가하기",
                            onClick = navigateRoutineDetailGraphToCreateRoutineGraph
                        )
                    }
                }
            }
        }
    }
}




