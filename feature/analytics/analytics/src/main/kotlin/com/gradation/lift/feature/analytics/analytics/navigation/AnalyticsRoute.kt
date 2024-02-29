package com.gradation.lift.feature.analytics.analytics.navigation

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
import com.gradation.lift.designsystem.component.chart.model.WorkCategoryCount
import com.gradation.lift.designsystem.component.chart.model.WorkCountByMonth
import com.gradation.lift.designsystem.component.chart.model.WorkPartCountByMonth
import com.gradation.lift.feature.analytics.analytics.data.AnalyticsViewModel
import com.gradation.lift.feature.analytics.analytics.data.model.WeekDateHistoryCount
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsBarChartState
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsCalendarState
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsHexagonChartState
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsPieChartState
import com.gradation.lift.feature.analytics.analytics.data.state.AnalyticsScreenState
import com.gradation.lift.feature.analytics.analytics.data.state.HistoryUiState
import com.gradation.lift.feature.analytics.analytics.data.state.START_DATE
import com.gradation.lift.feature.analytics.analytics.data.state.rememberAnalyticsScreenState
import com.gradation.lift.feature.analytics.analytics.ui.AnalyticsScreen
import com.gradation.lift.feature.analytics.analytics.ui.bottomSheet.DatePickerBottomSheet
import com.gradation.lift.feature.analytics.analytics.ui.bottomSheet.WorkBottomSheet
import com.gradation.lift.ui.extensions.showToast
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import java.time.temporal.ChronoUnit

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun AnalyticsRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateAnalyticsGraphToWorkReadyReadyRouter: () -> Unit,
    navigateAnalyticsGraphToWorkReadyRoutineSelectionRouter: () -> Unit,
    viewModel: AnalyticsViewModel = hiltViewModel(),
    analyticsCalendarState: AnalyticsCalendarState = viewModel.analyticsCalendarState,
    analyticsBarChartState: AnalyticsBarChartState = viewModel.analyticsBarChartState,
    analyticsHexagonChartState: AnalyticsHexagonChartState = viewModel.analyticsHexagonChartState,
    analyticsPieChartState: AnalyticsPieChartState = viewModel.analyticsPieChartState,
    analyticsScreenState: AnalyticsScreenState = rememberAnalyticsScreenState(viewModel.today),
) {

    val historyUiState: HistoryUiState by viewModel.historyUiState.collectAsStateWithLifecycle()
    val selectedDate: LocalDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val updateSelectedDate: (LocalDate) -> Unit = viewModel.updateSelectedDate


    val calendar: Map<Int, List<WeekDateHistoryCount>> by analyticsCalendarState.calendar.collectAsStateWithLifecycle()
    val selectedDateHistoryCount: Int by analyticsCalendarState.selectedDateHistoryCount.collectAsStateWithLifecycle()

    val workCountByMonthList: List<WorkCountByMonth> by analyticsBarChartState.workCountByMonthList.collectAsStateWithLifecycle()
    val thisMonthWorkCountForPreMonth: Int by analyticsBarChartState.thisMonthWorkCountForPreMonth.collectAsStateWithLifecycle()

    val workPartCountByMonthList: List<WorkPartCountByMonth> by analyticsHexagonChartState.workPartCountByMonthList.collectAsStateWithLifecycle()
    val workCountByPreCurrentMonth: List<WorkCountByMonth> by analyticsHexagonChartState.workCountByPreCurrentMonth.collectAsStateWithLifecycle()
    val mostUsedWorkPartInThisMonth: String by analyticsHexagonChartState.mostUsedWorkPartInThisMonth.collectAsStateWithLifecycle()


    val workPartList: List<String> = analyticsPieChartState.workPartList
    val selectedWorkPart: String by analyticsPieChartState.selectedWorkPart.collectAsStateWithLifecycle()
    val workCategoryCountByWorkPartList: List<WorkCategoryCount> by analyticsPieChartState.workCategoryCountByWorkPartList.collectAsStateWithLifecycle()
    val sumOfWorkCountByWorkPart: Int by analyticsPieChartState.sumOfWorkCountByWorkPart.collectAsStateWithLifecycle()
    val mostUsedWorkCategory: String by analyticsPieChartState.mostUsedWorkCategory.collectAsStateWithLifecycle()
    val selectedDateTotalWorkCount: Int by analyticsPieChartState.selectedDateTotalWorkCount.collectAsStateWithLifecycle()


    val updateSelectedWorkPart: (String) -> Unit = analyticsPieChartState.updateSelectedWorkPart


    AnimatedVisibility(visible = analyticsScreenState.workBottomSheetView) {
        WorkBottomSheet(
            modifier,
            navigateAnalyticsGraphToWorkReadyReadyRouter,
            navigateAnalyticsGraphToWorkReadyRoutineSelectionRouter,
            analyticsScreenState
        )
    }
    AnimatedVisibility(visible = analyticsScreenState.datePickerBottomSheetView) {
        DatePickerBottomSheet(
            modifier, selectedDate, updateSelectedDate, analyticsScreenState
        )
    }

    LaunchedEffect(analyticsScreenState.pagerState, selectedDate) {
        snapshotFlow {
            Pair(
                analyticsScreenState.pagerState.currentPage,
                selectedDate
            )
        }.collect { it ->
            selectedDate.run {
                updateSelectedDate(
                    java.time.LocalDate.of(
                        START_DATE.year,
                        START_DATE.monthValue,
                        START_DATE.dayOfMonth
                    ).plusMonths(it.first.toLong()).toKotlinLocalDate()
                )
                analyticsScreenState.pagerState.scrollToPage(
                    START_DATE.until(
                        this.toJavaLocalDate(),
                        ChronoUnit.MONTHS
                    ).toInt()
                )
            }
        }
    }

    BackHandler(onBack = {
        if (System.currentTimeMillis() - analyticsScreenState.terminateWaitTime.value >= 1500) {
            analyticsScreenState.terminateWaitTime.value = System.currentTimeMillis()
            showToast(analyticsScreenState.context, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.")
        } else {
            navController.popBackStack()
        }
    })


    AnalyticsScreen(
        modifier,
        historyUiState,
        selectedDate,
        calendar,
        selectedDateHistoryCount,
        workCountByMonthList,
        thisMonthWorkCountForPreMonth,
        workPartCountByMonthList,
        workCountByPreCurrentMonth,
        mostUsedWorkPartInThisMonth,
        workPartList,
        selectedWorkPart,
        workCategoryCountByWorkPartList,
        sumOfWorkCountByWorkPart,
        mostUsedWorkCategory,
        selectedDateTotalWorkCount,
        updateSelectedWorkPart,
        analyticsScreenState
    )


}