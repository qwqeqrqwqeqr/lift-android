package com.gradation.lift.feature.routine

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gradation.lift.domain.usecase.date.GetCurrentDateUseCase
import com.gradation.lift.domain.usecase.date.GetWeekDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.DayOfWeek
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class RoutineViewModel @Inject constructor(
    getWeekDateUseCase: GetWeekDateUseCase,
    getCurrentDateUseCase: GetCurrentDateUseCase
) : ViewModel() {

    val currentDate by mutableStateOf<String>(getCurrentDateUseCase().let { "${it.monthNumber}월 ${it.dayOfMonth}일" })
    
    //TODO 상태를 가지고 있게 만들어야함... 현재 선택된 객체가 어떤건지...
    val weekDate by mutableStateOf<List<WeekCardUiState>>(
        value = getWeekDateUseCase().map {
            WeekCardUiState(
                it.dayOfMonth.toString(), when (it.dayOfWeek) {
                    DayOfWeek.MONDAY -> "월"
                    DayOfWeek.TUESDAY -> "화"
                    DayOfWeek.WEDNESDAY -> "수"
                    DayOfWeek.THURSDAY -> "목"
                    DayOfWeek.FRIDAY -> "금"
                    DayOfWeek.SATURDAY -> "토"
                    DayOfWeek.SUNDAY -> "일"
                }, false
            )
        }.also { it.first().selected = true } as MutableList<WeekCardUiState>)
}


data class WeekCardUiState(
    val day: String,
    val weekDay: String,
    var selected: Boolean
)