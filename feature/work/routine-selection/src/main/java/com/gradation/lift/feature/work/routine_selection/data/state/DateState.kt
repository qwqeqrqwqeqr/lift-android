package com.gradation.lift.feature.work.routine_selection.data.state

import android.os.Build
import androidx.annotation.RequiresApi
import com.gradation.lift.domain.usecase.date.GetThisWeekUseCase
import com.gradation.lift.domain.usecase.date.GetTodayUseCase
import com.gradation.lift.feature.work.routine_selection.data.model.WeekDateSelection
import com.gradation.lift.model.model.date.toWeekday
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.datetime.LocalDate
import javax.inject.Inject

/**
 * [DateState]
 * @property currentDate 현재 선택된 날짜 (초기값은 오늘 날짜)
 * @property weekDate 현재 날짜에 대한 한주의 요일 정보와 해당 날짜가 선택되었는지 여부
 * @since 2023-08-22 12:35:47
 */
class DateState @Inject constructor(
    getTodayUseCase: GetTodayUseCase,
    private val getThisWeekUseCase: GetThisWeekUseCase,
    private val viewModelScope: CoroutineScope,
) {


    internal val currentDate: MutableStateFlow<LocalDate> = MutableStateFlow(getTodayUseCase())


    @RequiresApi(Build.VERSION_CODES.O)
    internal val weekDate: StateFlow<List<WeekDateSelection>> = currentDate.map {
        getThisWeekUseCase(it).map { localDate ->
            WeekDateSelection(
                weekday = localDate.toWeekday(),
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

    internal fun updateCurrentDate(): (LocalDate) -> Unit = {
        currentDate.value = it
    }

}