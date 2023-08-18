package com.gradation.lift.feature.home.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetThisWeekUseCase
import com.gradation.lift.domain.usecase.date.GetTodayUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByWeekdayUseCase
import com.gradation.lift.domain.usecase.user.GetUserDetailUseCase
import com.gradation.lift.feature.home.data.model.WeekDateSelection
import com.gradation.lift.feature.home.data.state.UserDetailUiState
import com.gradation.lift.feature.home.data.state.WeekDateRoutineUiState
import com.gradation.lift.model.model.common.toWeekday
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.setValueSavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.datetime.LocalDate
import javax.inject.Inject

/**
 * [HomeViewModel]
 * @property today 오늘 날짜
 * @property selectedDate 선택한 날짜, 해당 필드를 바탕으로 요일에 맞는 루틴을 불러옴, 초기값은 오늘 날짜로 설정되어 있음
 * @property weekDateSelectionList 이번주에 해당하는 weekDataSelection 목록 [WeekDateSelection] 참고할 것
 * @property weekDateRoutineUiState 해당 요일에 따른 루틴 목록 상태 루틴이 존재하지 않을 수 도 있음
 * @property userDetailUiState 사용자 상세정보 상태
 * @since 2023-08-18 18:57:49
 */
@OptIn(ExperimentalCoroutinesApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    getTodayUseCase: GetTodayUseCase,
    getThisWeekUseCase: GetThisWeekUseCase,
    getRoutineSetRoutineByWeekdayUseCase: GetRoutineSetRoutineByWeekdayUseCase,
    getUserDetailUseCase: GetUserDetailUseCase,
) : ViewModel() {

    var today: MutableStateFlow<LocalDate> = MutableStateFlow(getTodayUseCase())
    private val selectedDate: MutableStateFlow<LocalDate> = MutableStateFlow(getTodayUseCase())


    internal val weekDateSelectionList: StateFlow<List<WeekDateSelection>> = today.map { today ->
        getThisWeekUseCase(today).map { localDate ->
            WeekDateSelection(
                day = localDate.dayOfMonth.toString(),
                weekday = localDate.toWeekday(),
                localDate = localDate,
                selected = localDate == selectedDate.value
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    internal val weekDateRoutineUiState: StateFlow<WeekDateRoutineUiState> =
        selectedDate.flatMapLatest { selectedDate ->
            getRoutineSetRoutineByWeekdayUseCase(selectedDate.toWeekday()).map {
                when (it) {
                    is DataState.Fail -> {
                        WeekDateRoutineUiState.Fail(message = it.message)
                    }
                    is DataState.Success -> {
                        if (it.data.isEmpty()) {
                            WeekDateRoutineUiState.Empty
                        } else {
                            WeekDateRoutineUiState.Success(
                                weekDateRoutine = it.data
                            )
                        }
                    }
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = WeekDateRoutineUiState.Loading
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


    internal fun updateSelectedDate(): (LocalDate) -> Unit = {
        selectedDate.value = it
    }

    internal fun updateKey(navController: NavController, selectedRoutineSetRoutineId: Int) {
        navController.setValueSavedStateHandle(
            SavedStateHandleKey.WorkKey.ROUTINE_SET_ROUTINE_KEY,
            selectedRoutineSetRoutineId
        )
    }
}




