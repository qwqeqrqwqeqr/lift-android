package com.gradation.lift.feature.routine

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.domain.model.work.WorkPart
import com.gradation.lift.domain.usecase.date.GetCurrentDateUseCase
import com.gradation.lift.domain.usecase.date.GetWeekDateUseCase
import com.gradation.lift.domain.usecase.routine.GetRoutineByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.datetime.LocalDate
import java.time.DayOfWeek
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class RoutineViewModel @Inject constructor(
    getWeekDateUseCase: GetWeekDateUseCase,
    getCurrentDateUseCase: GetCurrentDateUseCase,
    private val getRoutineByDateUseCase: GetRoutineByDateUseCase
) : ViewModel() {

    val currentDate by mutableStateOf<String>(getCurrentDateUseCase().let { "${it.monthNumber}월 ${it.dayOfMonth}일" })
    val weekDate by mutableStateOf<WeekDateUiState>(
        value = WeekDateUiState(
            weekDate = getWeekDateUseCase().let {
                it.map { localDate ->
                    WeekDate(
                        day = localDate.dayOfMonth.toString(),
                        weekDay = when (localDate.dayOfWeek) {
                            DayOfWeek.MONDAY -> "월"
                            DayOfWeek.TUESDAY -> "화"
                            DayOfWeek.WEDNESDAY -> "수"
                            DayOfWeek.THURSDAY -> "목"
                            DayOfWeek.FRIDAY -> "금"
                            DayOfWeek.SATURDAY -> "토"
                            DayOfWeek.SUNDAY -> "일"
                        },
                        localDate = localDate,
                        selected = true
                    )
                }.also { weekDate -> weekDate.first().selected=false }
            })
    )

//    fun selectWeekDate(localDate: LocalDate) {
//       val a = getRoutineByDateUseCase(date = localDate).collectAsStateWithLifecycle(initialValue = ){
//
//       }
//
//
//    }


}


data class WeekDateUiState(
    val weekDate: List<WeekDate>
    )
{
}

data class WeekDate(
    val day: String,
    val weekDay: String,
    val localDate: LocalDate,
    var selected: Boolean
)

//sealed interface RoutineUiState {
//    data class Success(val userData: List<WorkPart>) : HomeUiState
//    object Loading : HomeUiState
//    object Empty : HomeUiState
//    object Error : HomeUiState
//}