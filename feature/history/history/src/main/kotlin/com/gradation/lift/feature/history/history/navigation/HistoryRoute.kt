package com.gradation.lift.feature.history.history.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.history.history.data.HistoryViewModel
import com.gradation.lift.feature.history.history.data.model.WeekDateHistoryCount
import com.gradation.lift.feature.history.history.data.state.HistoryScreenState
import com.gradation.lift.feature.history.history.data.state.START_DATE
import com.gradation.lift.feature.history.history.data.state.rememberHistoryScreenState
import com.gradation.lift.feature.history.history.ui.HistoryScreen
import com.gradation.lift.feature.history.history.ui.bottomSheet.DatePickerBottomSheet
import com.gradation.lift.feature.history.history.ui.bottomSheet.WorkBottomSheet
import com.gradation.lift.model.model.history.History
import com.gradation.lift.ui.extensions.showToast
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import java.time.temporal.ChronoUnit

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HistoryRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateHistoryGraphToWorkReadyReadyRouter: () -> Unit,
    navigateHistoryGraphToWorkReadyRoutineSelectionRouter: () -> Unit,
    navigateHistoryToUpdateInfoInHistoryGraph: (String, Int, Int, String) -> Unit,
    viewModel: HistoryViewModel = hiltViewModel(),
    historyScreenState: HistoryScreenState = rememberHistoryScreenState(viewModel.today),
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


    BackHandler(onBack = {
        if (System.currentTimeMillis() - historyScreenState.terminateWaitTime.value >= 1500) {
            historyScreenState.terminateWaitTime.value = System.currentTimeMillis()
            showToast(historyScreenState.context, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.")
        } else {
            navController.popBackStack()
        }
    })



    LaunchedEffect(historyScreenState.pagerState, selectedDate) {
        snapshotFlow {
            Pair(
                historyScreenState.pagerState.currentPage,
                selectedDate
            )
        }.distinctUntilChanged().collect { it ->
            selectedDate.run {
                updateSelectedDate(
                    java.time.LocalDate.of(
                        START_DATE.year,
                        START_DATE.monthValue,
                        it.second.dayOfMonth
                    ).plusMonths(it.first.toLong()).toKotlinLocalDate()
                )
                historyScreenState.pagerState.scrollToPage(
                    START_DATE.until(
                        this.toJavaLocalDate(),
                        ChronoUnit.MONTHS
                    ).toInt()
                )
            }
        }
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