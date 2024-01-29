package com.gradation.lift.feature.analytics.analytics.data.state

import com.gradation.lift.domain.usecase.date.GetCurrentWeekUseCase
import com.gradation.lift.domain.usecase.date.GetPreWeekUseCase
import com.gradation.lift.feature.analytics.analytics.data.model.WorkPartAnalyticsTargetDate
import com.gradation.lift.feature.analytics.analytics.data.model.WorkPartAnalyticsTargetType
import com.gradation.lift.feature.analytics.analytics.data.model.WorkPartFrequency
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import javax.inject.Inject

/**
 * [WorkPartAnalyticsState]
 * @property workPartAnalyticsTargetDate  비교할 타겟 날짜 (주,월,년)
 * @property workPartAnalyticsTargetType  비교할 타겟 종류(타입) (이번,지난,전체)
 * @property historyWorkPartCountByCurrent  현재의 운동부위 별 운동 횟수 (주,월,년)
 * @property historyWorkPartCountByPre  과거의 운동부위 별 운동 횟수  (주,월,년)
 * @property historyCountByPre  과거 총 운동 횟수 [workPartAnalyticsTargetDate] 에 따라 변경
 * @property historyCountByCurrent 현재 총 운동 횟수 [workPartAnalyticsTargetDate] 에 따라 변경
 * @property maxOfWorkPartFrequency 가장 많은 운동빈도를 가지고 있는 부위
 * @since 2023-09-05 16:08:17
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

    val workPartAnalyticsTargetType: MutableStateFlow<WorkPartAnalyticsTargetType> =
        MutableStateFlow(WorkPartAnalyticsTargetType.All)


    val historyWorkPartCountByPre: StateFlow<WorkPartFrequency> =
        combine(
            today,
            workPartAnalyticsTargetDate,
            historyUiState
        ) { today, workPartAnalyticsTargetDate, historyUiStateResult ->
            if (historyUiStateResult is HistoryUiState.Success) {
                when (workPartAnalyticsTargetDate) {
                    WorkPartAnalyticsTargetDate.Month -> {
                        with(today.minus(DatePeriod(0, 1, 0)).month) {
                            historyUiStateResult.historyList.filter { history -> history.historyTimeStamp.month == this }
                                .let { it ->
                                    WorkPartFrequency(
                                        chestFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(CHEST_NAME) },
                                        shoulderFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(SHOULDER_NAME) },
                                        armFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(ARM_NAME) },
                                        backFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(BACK_NAME) },
                                        lowerBodyFrequency = it.flatMap { it.historyRoutine }
                                            .count {
                                                it.workCategory.workPart.contains(
                                                    LOWER_BODY_NAME
                                                )
                                            },
                                        absFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(ABS_NAME) }
                                    )

                                }
                        }
                    }

                    WorkPartAnalyticsTargetDate.Week -> {
                        with(getPreWeekUseCase(today)) {
                            historyUiStateResult.historyList.filter { history -> history.historyTimeStamp.date in this }
                                .let { it ->
                                    WorkPartFrequency(
                                        chestFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(CHEST_NAME) },
                                        shoulderFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(SHOULDER_NAME) },
                                        armFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(ARM_NAME) },
                                        backFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(BACK_NAME) },
                                        lowerBodyFrequency = it.flatMap { it.historyRoutine }
                                            .count {
                                                it.workCategory.workPart.contains(
                                                    LOWER_BODY_NAME
                                                )
                                            },
                                        absFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(ABS_NAME) }
                                    )

                                }
                        }
                    }

                    WorkPartAnalyticsTargetDate.Year -> {
                        with(today.minus(DatePeriod(1, 0, 0)).year) {
                            historyUiStateResult.historyList.filter { history -> history.historyTimeStamp.year == this }
                                .let { it ->
                                    WorkPartFrequency(
                                        chestFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(CHEST_NAME) },
                                        shoulderFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(SHOULDER_NAME) },
                                        armFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(ARM_NAME) },
                                        backFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(BACK_NAME) },
                                        lowerBodyFrequency = it.flatMap { it.historyRoutine }
                                            .count {
                                                it.workCategory.workPart.contains(
                                                    LOWER_BODY_NAME
                                                )
                                            },
                                        absFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(ABS_NAME) }
                                    )

                                }
                        }
                    }
                }
            } else {
                WorkPartFrequency()
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WorkPartFrequency()
        )


    val historyWorkPartCountByCurrent: StateFlow<WorkPartFrequency> =
        combine(
            today,
            workPartAnalyticsTargetDate,
            historyUiState
        ) { today, workPartAnalyticsTargetDate, historyUiStateResult ->
            if (historyUiStateResult is HistoryUiState.Success) {
                when (workPartAnalyticsTargetDate) {
                    WorkPartAnalyticsTargetDate.Month -> {
                        with(today.month) {
                            historyUiStateResult.historyList.filter { history -> history.historyTimeStamp.month == this }
                                .let { it ->
                                    WorkPartFrequency(
                                        chestFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(CHEST_NAME) },
                                        shoulderFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(SHOULDER_NAME) },
                                        armFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(ARM_NAME) },
                                        backFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(BACK_NAME) },
                                        lowerBodyFrequency = it.flatMap { it.historyRoutine }
                                            .count {
                                                it.workCategory.workPart.contains(
                                                    LOWER_BODY_NAME
                                                )
                                            },
                                        absFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(ABS_NAME) }
                                    )

                                }
                        }
                    }

                    WorkPartAnalyticsTargetDate.Week -> {
                        with(getCurrentWeekUseCase(today)) {
                            historyUiStateResult.historyList.filter { history -> history.historyTimeStamp.date in this }
                                .let { it ->
                                    WorkPartFrequency(
                                        chestFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(CHEST_NAME) },
                                        shoulderFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(SHOULDER_NAME) },
                                        armFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(ARM_NAME) },
                                        backFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(BACK_NAME) },
                                        lowerBodyFrequency = it.flatMap { it.historyRoutine }
                                            .count {
                                                it.workCategory.workPart.contains(
                                                    LOWER_BODY_NAME
                                                )
                                            },
                                        absFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(ABS_NAME) }
                                    )

                                }
                        }
                    }

                    WorkPartAnalyticsTargetDate.Year -> {
                        with(today.year) {
                            historyUiStateResult.historyList.filter { history -> history.historyTimeStamp.year == this }
                                .let { it ->
                                    WorkPartFrequency(
                                        chestFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(CHEST_NAME) },
                                        shoulderFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(SHOULDER_NAME) },
                                        armFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(ARM_NAME) },
                                        backFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(BACK_NAME) },
                                        lowerBodyFrequency = it.flatMap { it.historyRoutine }
                                            .count {
                                                it.workCategory.workPart.contains(
                                                    LOWER_BODY_NAME
                                                )
                                            },
                                        absFrequency = it.flatMap { it.historyRoutine }
                                            .count { it.workCategory.workPart.contains(ABS_NAME) }
                                    )

                                }
                        }
                    }
                }
            } else {
                WorkPartFrequency()
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WorkPartFrequency()
        )


    val historyCountByPre: StateFlow<Int> = historyWorkPartCountByPre.map {
        it.absFrequency + it.armFrequency + it.backFrequency + it.chestFrequency + it.shoulderFrequency + it.lowerBodyFrequency
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )


    val historyCountByCurrent: StateFlow<Int> = historyWorkPartCountByCurrent.map {
        it.absFrequency + it.armFrequency + it.backFrequency + it.chestFrequency + it.shoulderFrequency + it.lowerBodyFrequency
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0
    )


    val maxOfWorkPartFrequency: StateFlow<String> =
        historyWorkPartCountByCurrent.map { workPartFrequency ->
            maxOf(
                workPartFrequency.absFrequency,
                workPartFrequency.armFrequency,
                workPartFrequency.backFrequency,
                workPartFrequency.chestFrequency,
                workPartFrequency.shoulderFrequency,
                workPartFrequency.lowerBodyFrequency
            ).let {
                when (it) {
                    workPartFrequency.absFrequency -> {
                        ABS_NAME
                    }

                    workPartFrequency.armFrequency -> {
                        ARM_NAME
                    }

                    workPartFrequency.backFrequency -> {
                        BACK_NAME
                    }

                    workPartFrequency.chestFrequency -> {
                        CHEST_NAME
                    }

                    workPartFrequency.shoulderFrequency -> {
                        SHOULDER_NAME
                    }

                    else -> {
                        LOWER_BODY_NAME
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ""
        )

    fun updateWorkPartAnalyticsTargetDate(): (WorkPartAnalyticsTargetDate) -> Unit = {
        workPartAnalyticsTargetDate.value = it
    }

    fun updateWorkPartAnalyticsTargetType(): (WorkPartAnalyticsTargetType) -> Unit = {
        workPartAnalyticsTargetType.value = it
    }

    companion object {
        const val CHEST_NAME = "가슴"
        const val SHOULDER_NAME = "어깨"
        const val ARM_NAME = "팔"
        const val BACK_NAME = "등"
        const val LOWER_BODY_NAME = "하체"
        const val ABS_NAME = "복근"
    }
}

