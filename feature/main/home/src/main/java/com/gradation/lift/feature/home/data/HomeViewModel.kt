package com.gradation.lift.feature.home.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.date.GetWeekDateUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByWeekdayUseCase
import com.gradation.lift.domain.usecase.user.GetUserDetailUseCase
import com.gradation.lift.model.common.toWeekday
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.setIntValue
import com.gradation.lift.navigation.saved_state.setStringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import javax.inject.Inject


@OptIn(ExperimentalCoroutinesApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    getWeekDateUseCase: GetWeekDateUseCase,
    getRoutineSetRoutineByWeekdayUseCase: GetRoutineSetRoutineByWeekdayUseCase,
    getUserDetailUseCase: GetUserDetailUseCase,
) : ViewModel() {

    private val _today = MutableStateFlow(Clock.System.todayIn(TimeZone.currentSystemDefault()))
    var today = _today.asStateFlow()

    private val currentDate =
        MutableStateFlow(Clock.System.todayIn(TimeZone.currentSystemDefault()))

    internal val weekDate = currentDate.map {
        getWeekDateUseCase(it).map { localDate ->
            WeekDate(
                day = localDate.dayOfMonth.toString(),
                weekDay = localDate.toWeekday(),
                localDate = localDate,
                selected = false
            ).apply {
                if (this.localDate == currentDate.value) this.selected = true
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    internal val weekDateRoutine: StateFlow<WeekDateRoutineUiState> =
        currentDate.flatMapLatest { currentDate ->
            getRoutineSetRoutineByWeekdayUseCase(currentDate.toWeekday()).map {
                when (it) {
                    is DataState.Fail -> WeekDateRoutineUiState.Fail(message = it.message)
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


    internal val userDetail: StateFlow<UserDetailUiState> = getUserDetailUseCase().map {
        when (it) {
            is DataState.Fail -> UserDetailUiState.Fail(it.message)
            is DataState.Success -> UserDetailUiState.Success(it.data)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UserDetailUiState.Loading
    )


    internal fun onClickDate(selectedDate: LocalDate) {
        currentDate.value = selectedDate
    }

    internal fun updateKey(navController: NavController,routineSetId :Int) {
        navController.setIntValue(SavedStateHandleKey.WorkKey.ROUTINE_SET_ID_KEY, routineSetId)
    }
}




