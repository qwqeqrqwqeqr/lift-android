package com.gradation.lift.feature.routine_detail.routine_list.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.routine_detail.routine_list.data.model.LabelFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.model.SortType
import com.gradation.lift.feature.routine_detail.routine_list.data.model.WeekdayFilterType
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineDetailRoutineListUiState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineListInfoState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.RoutineListScreenState
import com.gradation.lift.feature.routine_detail.routine_list.data.state.SortFilterState
import com.gradation.lift.feature.routine_detail.routine_list.ui.component.success.RoutineListView
import com.gradation.lift.feature.routine_detail.routine_list.ui.component.success.SearchSortFilterView
import com.gradation.lift.feature.routine_detail.routine_list.ui.component.bottomsheet.LabelFilterBottomSheet
import com.gradation.lift.feature.routine_detail.routine_list.ui.component.bottomsheet.SortBottomSheet
import com.gradation.lift.feature.routine_detail.routine_list.ui.component.bottomsheet.WeekdayFilterBottomSheet


@OptIn(ExperimentalGlideComposeApi::class, ExperimentalLayoutApi::class)
@Composable
internal fun RoutineListScreen(
    modifier: Modifier = Modifier,
    routineListScreenState: RoutineListScreenState,
    routineListInfoState: RoutineListInfoState,
    routineSetRoutineListUiState: RoutineDetailRoutineListUiState,
    sortFilterState: SortFilterState,
    labelFilterType: LabelFilterType,
    weekdayFilterType: WeekdayFilterType,
    searchFilterText: String,
    sortType: SortType
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
                onBackClickTopBar = {},
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
                RoutineDetailRoutineListUiState.Empty -> {

                }

                is RoutineDetailRoutineListUiState.Fail -> {

                }

                RoutineDetailRoutineListUiState.Loading -> {

                }

                is RoutineDetailRoutineListUiState.Success -> {
                    Column {
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
                            routineListInfoState
                        )
                    }
                }
            }
        }
    }
}




