package com.gradation.lift.feature.history.analytics.data.state

import android.os.Build
import androidx.annotation.RequiresApi
import com.gradation.lift.domain.usecase.date.GetCurrentWeekUseCase
import com.gradation.lift.domain.usecase.date.GetPreWeekUseCase
import com.gradation.lift.feature.history.analytics.data.model.WorkCategoryFrequency
import com.gradation.lift.feature.history.analytics.data.model.WorkPartAnalyticsTargetDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import javax.inject.Inject

/**
 * [WorkPartAnalyticsState]
 * @property workPartAnalyticsTargetDate  비교할 타겟 날짜 (주,월,년)
 * @property historyWorkPartCountByCurrent  현재의 운동부위 별 운동 횟수 (주,월,년)
 * @property historyWorkPartCountByPre  과거의 운동부위 별 운동 횟수  (주,월,년)
 * @since 2023-09-05 14:23:25
 */
class WorkPartAnalyticsState @Inject constructor(
    viewModelScope: CoroutineScope,
    today: StateFlow<LocalDate>,
    historyUiState: StateFlow<HistoryUiState>,
    getPreWeekUseCase: GetPreWeekUseCase,
    getCurrentWeekUseCase: GetCurrentWeekUseCase,
) {


    val workPartAnalyticsTargetDate: MutableStateFlow<WorkPartAnalyticsTargetDate> =
        MutableStateFlow(WorkPartAnalyticsTargetDate.Week)

    @RequiresApi(Build.VERSION_CODES.O)
    val historyWorkPartCountByPre: StateFlow<WorkCategoryFrequency> =
        combine(
            today,
            workPartAnalyticsTargetDate,
            historyUiState
        ) { today, workPartAnalyticsTargetDate, historyUiStateResult ->
            if (historyUiStateResult is HistoryUiState.Success) {
                when(workPartAnalyticsTargetDate){
                    WorkPartAnalyticsTargetDate.Month -> {
                        with(today.minus(DatePeriod(0, 1, 0)).month) {
                            historyUiStateResult.historyList.filter { history -> history.historyTimeStamp.month == this }
                                .let { it ->
                                    WorkCategoryFrequency(
                                        chestFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == CHEST_NAME },
                                        shoulderFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == SHOULDER_NAME },
                                        armFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == ARM_NAME },
                                        backFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == BACK_NAME },
                                        lowerBodyFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == LOWER_BODY_NAME },
                                        absFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == ABS_NAME }
                                    )

                                }
                        }
                    }
                    WorkPartAnalyticsTargetDate.Week -> {
                        with(getPreWeekUseCase(today)) {
                            historyUiStateResult.historyList.filter { history -> history.historyTimeStamp.date in this }
                                .let { it ->
                                    WorkCategoryFrequency(
                                        chestFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == CHEST_NAME },
                                        shoulderFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == SHOULDER_NAME },
                                        armFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == ARM_NAME },
                                        backFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == BACK_NAME },
                                        lowerBodyFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == LOWER_BODY_NAME },
                                        absFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == ABS_NAME }
                                    )

                                }
                        }
                    }
                    WorkPartAnalyticsTargetDate.Year -> {
                        with(today.minus(DatePeriod(1, 0, 0)).year) {
                            historyUiStateResult.historyList.filter { history -> history.historyTimeStamp.year == this }
                                .let { it ->
                                    WorkCategoryFrequency(
                                        chestFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == CHEST_NAME },
                                        shoulderFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == SHOULDER_NAME },
                                        armFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == ARM_NAME },
                                        backFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == BACK_NAME },
                                        lowerBodyFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == LOWER_BODY_NAME },
                                        absFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == ABS_NAME }
                                    )

                                }
                        }
                    }
                }
            } else {
                WorkCategoryFrequency()
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WorkCategoryFrequency()
        )

    @RequiresApi(Build.VERSION_CODES.O)
    val historyWorkPartCountByCurrent: StateFlow<WorkCategoryFrequency> =
        combine(
            today,
            workPartAnalyticsTargetDate,
            historyUiState
        ) { today, workPartAnalyticsTargetDate, historyUiStateResult ->
            if (historyUiStateResult is HistoryUiState.Success) {
                when(workPartAnalyticsTargetDate){
                    WorkPartAnalyticsTargetDate.Month -> {
                        with(today.month) {
                            historyUiStateResult.historyList.filter { history -> history.historyTimeStamp.month == this }
                                .let { it ->
                                    WorkCategoryFrequency(
                                        chestFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == CHEST_NAME },
                                        shoulderFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == SHOULDER_NAME },
                                        armFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == ARM_NAME },
                                        backFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == BACK_NAME },
                                        lowerBodyFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == LOWER_BODY_NAME },
                                        absFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == ABS_NAME }
                                    )

                                }
                        }
                    }
                    WorkPartAnalyticsTargetDate.Week -> {
                        with(getCurrentWeekUseCase(today)) {
                            historyUiStateResult.historyList.filter { history -> history.historyTimeStamp.date in this }
                                .let { it ->
                                    WorkCategoryFrequency(
                                        chestFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == CHEST_NAME },
                                        shoulderFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == SHOULDER_NAME },
                                        armFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == ARM_NAME },
                                        backFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == BACK_NAME },
                                        lowerBodyFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == LOWER_BODY_NAME },
                                        absFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == ABS_NAME }
                                    )

                                }
                        }
                    }
                    WorkPartAnalyticsTargetDate.Year -> {
                        with(today.year) {
                            historyUiStateResult.historyList.filter { history -> history.historyTimeStamp.year == this }
                                .let { it ->
                                    WorkCategoryFrequency(
                                        chestFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == CHEST_NAME },
                                        shoulderFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == SHOULDER_NAME },
                                        armFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == ARM_NAME },
                                        backFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == BACK_NAME },
                                        lowerBodyFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == LOWER_BODY_NAME },
                                        absFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.name == ABS_NAME }
                                    )

                                }
                        }
                    }
                }
            } else {
                WorkCategoryFrequency()
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WorkCategoryFrequency()
        )





    companion object {
        const val CHEST_NAME = "가슴"
        const val SHOULDER_NAME = "어꺠"
        const val ARM_NAME = "팔"
        const val BACK_NAME = "등"
        const val LOWER_BODY_NAME = "하체"
        const val ABS_NAME = "복근"
    }
}

