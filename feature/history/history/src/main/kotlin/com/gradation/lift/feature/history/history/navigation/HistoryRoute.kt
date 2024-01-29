package com.gradation.lift.feature.history.history.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.history.history.data.HistoryViewModel
import com.gradation.lift.feature.history.history.data.model.WeekDateHistoryCount
import com.gradation.lift.feature.history.history.data.state.HistoryScreenState
import com.gradation.lift.feature.history.history.data.state.rememberHistoryScreenState
import com.gradation.lift.feature.history.history.ui.HistoryScreen
import com.gradation.lift.feature.history.history.ui.bottomSheet.DatePickerBottomSheet
import com.gradation.lift.feature.history.history.ui.bottomSheet.WorkBottomSheet
import com.gradation.lift.model.model.history.History
import kotlinx.datetime.LocalDate

@Composable
internal fun HistoryRoute(
    modifier: Modifier = Modifier,
    navigateHistoryGraphToWorkReadyReadyRouter: () -> Unit,
    navigateHistoryGraphToWorkReadyRoutineSelectionRouter: () -> Unit,
    navigateHistoryToUpdateInfoInHistoryGraph: (String, Int, Int, String) -> Unit,
    viewModel: HistoryViewModel = hiltViewModel(),
    historyScreenState: HistoryScreenState = rememberHistoryScreenState(),
) {

    val today: LocalDate = viewModel.today
    val selectedDate: LocalDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val selectedTabIndex: Int by viewModel.selectedTabIndex.collectAsStateWithLifecycle()
    val calendar: Map<Int, List<WeekDateHistoryCount>> by viewModel.calendar.collectAsStateWithLifecycle()
    val selectedHistoryList: List<History> by viewModel.selectedHistoryList.collectAsStateWithLifecycle()

    val updateSelectedTabIndex: (Int) -> Unit = viewModel.updateSelectedTabIndex
    val updateSelectedDate: (LocalDate) -> Unit = viewModel.updateSelectedDate

    AnimatedVisibility(visible = historyScreenState.workBottomSheetView) {
        WorkBottomSheet(
            modifier,
            navigateHistoryGraphToWorkReadyReadyRouter,
            navigateHistoryGraphToWorkReadyRoutineSelectionRouter,
            historyScreenState
        )
    }
    AnimatedVisibility(visible = historyScreenState.datePickerBottomSheetView) {
        DatePickerBottomSheet(
            modifier, selectedDate, updateSelectedDate, historyScreenState
        )
    }


    HistoryScreen(
        modifier,
        today,
        selectedDate,
        selectedTabIndex,
        calendar,
        selectedHistoryList,
        updateSelectedTabIndex,
        updateSelectedDate,
        navigateHistoryToUpdateInfoInHistoryGraph,
        historyScreenState,
    )
}