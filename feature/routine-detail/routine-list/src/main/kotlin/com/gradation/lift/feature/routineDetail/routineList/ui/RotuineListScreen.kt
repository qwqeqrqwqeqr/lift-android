package com.gradation.lift.feature.routineDetail.routineList.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.button.LiftSolidLargeButton
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.routineDetail.routineList.data.model.LabelFilterType
import com.gradation.lift.feature.routineDetail.routineList.data.model.SortType
import com.gradation.lift.feature.routineDetail.routineList.data.model.WeekdayFilterType
import com.gradation.lift.feature.routineDetail.routineList.data.state.RoutineListUiState
import com.gradation.lift.feature.routineDetail.routineList.data.state.RoutineListInfoState
import com.gradation.lift.feature.routineDetail.routineList.data.state.RoutineListScreenState
import com.gradation.lift.feature.routineDetail.routineList.data.state.SortFilterState
import com.gradation.lift.feature.routineDetail.routineList.ui.component.success.RoutineListView
import com.gradation.lift.feature.routineDetail.routineList.ui.component.success.SearchSortFilterView
import com.gradation.lift.feature.routineDetail.routineList.ui.component.bottomsheet.LabelFilterBottomSheet
import com.gradation.lift.feature.routineDetail.routineList.ui.component.bottomsheet.SortBottomSheet
import com.gradation.lift.feature.routineDetail.routineList.ui.component.bottomsheet.WeekdayFilterBottomSheet

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
    navigateRoutineListToRoutineInRoutineDetailGraph: (Int) -> Unit
) {
    if (routineListScreenState.sortTypeBottomSheetView) {
        SortBottomSheet(modifier, routineListScreenState, sortFilterState, sortType)
    }
    if (routineListScreenState.weekdayFilterTypeBottomSheetView) {
        WeekdayFilterBottomSheet(
            modifier,
            routineListScreenState,
            sortFilterState,
            weekdayFilterType
        )
    }
    if (routineListScreenState.labelFilterTypeBottomSheetView) {
        LabelFilterBottomSheet(
            modifier,
            routineListScreenState,
            sortFilterState,
            labelFilterType
        )
    }
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "내 루틴",
                onBackClickTopBar = navigateRoutineDetailGraphToHomeGraph,
            )
        }
    ) { padding ->
        Surface(
            color = LiftTheme.colorScheme.no17,
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (routineSetRoutineListUiState) {
                is RoutineListUiState.Fail -> {
                    Box(modifier = modifier.fillMaxSize())
                }

                RoutineListUiState.Loading -> {
                    /*TODO Not Implement*/
                }

                is RoutineListUiState.Success -> {
                    Column {
                        Column(modifier = modifier.weight(1f)) {
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
                            RoutineListView(
                                modifier,
                                routineSetRoutineListUiState.routineSetRoutineList,
                                routineListInfoState,
                                routineListScreenState,
                                navigateRoutineListToRoutineInRoutineDetailGraph
                            )
                        }
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(
                                    start = LiftTheme.space.horizontalPaddingSpace,
                                    end = LiftTheme.space.horizontalPaddingSpace,
                                    bottom = LiftTheme.space.space8
                                ),
                            verticalArrangement = Arrangement.Bottom,
                        ) {
                            LiftSolidLargeButton(
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
}




