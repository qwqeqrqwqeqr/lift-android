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
    val getWeekDateUseCase: GetWeekDateUseCase,
    val getCurrentDateUseCase: GetCurrentDateUseCase
) : ViewModel() {

    val currentDate by mutableStateOf<String>(getCurrentDateUseCase().let { "${it.month}월 ${it.dayOfMonth}일" })

    val weekDate by mutableStateOf<List<Pair<String, String>>>(
        value = getWeekDateUseCase().map {
            Pair(
                it.dayOfMonth.toString(), when (it.dayOfWeek) {
                    DayOfWeek.MONDAY -> "월"
                    DayOfWeek.TUESDAY -> "화"
                    DayOfWeek.WEDNESDAY -> "수"
                    DayOfWeek.THURSDAY -> "목"
                    DayOfWeek.FRIDAY -> "금"
                    DayOfWeek.SATURDAY -> "토"
                    DayOfWeek.SUNDAY -> "일"
                }
            )
        })


}



