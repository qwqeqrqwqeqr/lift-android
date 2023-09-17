package com.gradation.lift.feature.home.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetCurrentWeekUseCase
import com.gradation.lift.domain.usecase.date.GetTodayUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByWeekdayUseCase
import com.gradation.lift.domain.usecase.user.GetUserDetailUseCase
import com.gradation.lift.feature.home.data.model.WeekDateSelection
import com.gradation.lift.feature.home.data.state.UserDetailUiState
import com.gradation.lift.feature.home.data.state.WeekDateRoutineUiState

import com.gradation.lift.model.model.date.toWeekday
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

@HiltViewModel
class HomeViewModel @Inject constructor(
    getTodayUseCase: GetTodayUseCase,
    getCurrentWeekUseCase: GetCurrentWeekUseCase,
    getRoutineSetRoutineByWeekdayUseCase: GetRoutineSetRoutineByWeekdayUseCase,
    getUserDetailUseCase: GetUserDetailUseCase,
) : ViewModel() {

    var today: MutableStateFlow<LocalDate> = MutableStateFlow(getTodayUseCase())
    private val selectedDate: MutableStateFlow<LocalDate> = MutableStateFlow(getTodayUseCase())


    internal val weekDateSelectionList: StateFlow<List<WeekDateSelection>> = selectedDate.map { selectedDate ->
        getCurrentWeekUseCase(selectedDate).map { localDate ->
            WeekDateSelection(
                day = localDate.dayOfMonth.toString(),
                weekday = localDate.toWeekday(),
                localDate = localDate,
                selected = localDate == selectedDate
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

    /**
     * [updateRoutineSetIdKey]
     * 화면에 표시되어있는 루틴 세트를 클릭해 운동을 시작할 경우
     * 해당 루틴세트 정보를 운동 페이지로 넘겨줘야함
     * 해당 루틴세트의 아이디를 키에 보관한 후 운동페이지에서 호출함
     */
    internal fun updateRoutineSetIdKey(): (NavController, Int) -> Unit = { navController,routineSetId ->
        navController.setValueSavedStateHandle(
            SavedStateHandleKey.WorkKey.ROUTINE_SET_ID_KEY,
            routineSetId
        )
    }
}




