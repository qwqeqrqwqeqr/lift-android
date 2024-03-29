package com.gradation.lift.feature.home.home.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.badge.GetUserBadgeByMainFlagUseCase
import com.gradation.lift.domain.usecase.date.GetCurrentWeekUseCase
import com.gradation.lift.domain.usecase.date.GetTodayUseCase
import com.gradation.lift.domain.usecase.history.GetHistoryUseCase
import com.gradation.lift.domain.usecase.routine.GetMostUsedRoutineSetRoutineUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRecentUseCase
import com.gradation.lift.domain.usecase.user.GetUserDetailUseCase
import com.gradation.lift.domain.usecase.work.ClearWorkUseCase
import com.gradation.lift.domain.usecase.work.ExistWorkUseCase
import com.gradation.lift.feature.home.home.data.model.RecentUsedRoutineSetRoutine
import com.gradation.lift.feature.home.home.data.state.BadgeUiState
import com.gradation.lift.feature.home.home.data.state.RoutineState
import com.gradation.lift.feature.home.home.data.state.RoutineUiState
import com.gradation.lift.feature.home.home.data.state.UserDetailUiState
import com.gradation.lift.feature.home.home.data.state.WorkStampState
import com.gradation.lift.feature.home.home.data.state.WorkStampUiState
import com.gradation.lift.model.model.history.History
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.toJavaLocalDate
import javax.inject.Inject

/**
 * [HomeViewModel]
 * @property userDetailUiState 사용자 상세정보 상태
 * @property badgeUiState 대표 뱃지 상태
 * @property routineUiState 루틴 상태
 * @since 2024-01-07 18:40:21
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    getUserDetailUseCase: GetUserDetailUseCase,
    getUserBadgeByMainFlagUseCase: GetUserBadgeByMainFlagUseCase,
    getMostUsedRoutineSetRoutineUseCase: GetMostUsedRoutineSetRoutineUseCase,
    getRoutineSetRoutineByRecentUseCase: GetRoutineSetRoutineByRecentUseCase,
    getHistoryUseCase: GetHistoryUseCase,
    getCurrentWeekUseCase: GetCurrentWeekUseCase,
    getTodayUseCase: GetTodayUseCase,
    existWorkUseCase: ExistWorkUseCase,
    clearWorkUseCase: ClearWorkUseCase,
) : ViewModel() {


    internal val existWorkState: StateFlow<Boolean> = existWorkUseCase().map {
        when (it) {
            is DataState.Fail -> false
            is DataState.Success -> it.data
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false
    )


    internal val userDetailUiState: StateFlow<UserDetailUiState> = getUserDetailUseCase().map {
        when (it) {
            is DataState.Fail -> UserDetailUiState.Fail(it.message)
            is DataState.Success -> UserDetailUiState.Success(it.data)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UserDetailUiState.Loading
    )

    internal val badgeUiState: StateFlow<BadgeUiState> = getUserBadgeByMainFlagUseCase().map {
        when (it) {
            is DataState.Fail -> BadgeUiState.Fail(it.message)
            is DataState.Success -> BadgeUiState.Success(it.data)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = BadgeUiState.Loading
    )

    internal val routineUiState: StateFlow<RoutineUiState> =
        combine(
            getMostUsedRoutineSetRoutineUseCase(),
            getRoutineSetRoutineByRecentUseCase()
        ) { mostUsed, recent ->
            when (mostUsed) {
                is DataState.Fail -> RoutineUiState.Fail(mostUsed.message)
                is DataState.Success -> {
                    when (recent) {
                        is DataState.Fail -> RoutineUiState.Fail(recent.message)
                        is DataState.Success -> {
                            if (mostUsed.data.isEmpty()) RoutineUiState.Empty
                            else
                                RoutineUiState.Success(
                                    RoutineState(mostUsed.data.map {
                                        RecentUsedRoutineSetRoutine(
                                            routineSetRoutine = it,
                                            recentUsed = recent.data.contains(it)
                                        )
                                    })
                                )
                        }
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RoutineUiState.Loading
        )

    internal val workStampUiState: StateFlow<WorkStampUiState> =
        getHistoryUseCase().map { result ->
            when (result) {
                is DataState.Fail -> WorkStampUiState.Fail(result.message)
                is DataState.Success -> {
                    result.data.let { historyList ->
                        val today = getTodayUseCase()
                        WorkStampUiState.Success(
                            WorkStampState(
                                continuousWorkCount = countContinuousWorkCount(today, historyList),
                                today = today,
                                workByDay = getCurrentWeekUseCase().map { date ->
                                    Pair(
                                        historyList.any {
                                            it.historyTimeStamp.let { timeStamp ->
                                                LocalDate(
                                                    timeStamp.year,
                                                    timeStamp.monthNumber,
                                                    timeStamp.dayOfMonth
                                                ).toEpochDays() == date.toEpochDays()
                                            }
                                        }, date
                                    )
                                },
                                countOfWorkInMonth = historyList.filter { history -> history.historyTimeStamp.monthNumber == today.monthNumber }
                                    .distinctBy {
                                        it.historyTimeStamp.let { timeStamp ->
                                            LocalDate(
                                                timeStamp.year,
                                                timeStamp.monthNumber,
                                                timeStamp.dayOfMonth
                                            ).toEpochDays()
                                        }
                                    }.count(),
                                endOfDay = getTodayUseCase().toJavaLocalDate().lengthOfMonth()
                            )
                        )
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WorkStampUiState.Loading
        )

    internal val clearWork: () -> Unit = {
        viewModelScope.launch {
            clearWorkUseCase().collect()
        }
    }

    private fun countContinuousWorkCount(
        today: LocalDate,
        historyList: List<History>,
    ): Int {
        var count = 0
        var currentDay = today
        while (true) {
            if (
                historyList.any {
                    it.historyTimeStamp.let { timeStamp ->
                        LocalDate(
                            timeStamp.year,
                            timeStamp.monthNumber,
                            timeStamp.dayOfMonth
                        ).toEpochDays() == currentDay.toEpochDays()
                    }
                }
            ) {
                count += 1
                currentDay = currentDay.minus(DatePeriod(days = 1))
            } else {
                break
            }
        }
        return count
    }
}




