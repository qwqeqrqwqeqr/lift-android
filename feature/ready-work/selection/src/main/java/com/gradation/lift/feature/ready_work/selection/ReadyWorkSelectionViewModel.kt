package com.gradation.lift.feature.ready_work.selection

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.domain.usecase.date.GetWeekDateUseCase
import com.gradation.lift.feature.ready_work.selection.data.WeekdayCard
import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.common.toWeekday
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class ReadyWorkSelectionViewModel @Inject constructor(
    private val getWeekDateUseCase: GetWeekDateUseCase,
) : ViewModel() {

    private val currentDate =
        MutableStateFlow(Clock.System.todayIn(TimeZone.currentSystemDefault()))

    internal val weekDate = currentDate.map {
        getWeekDateUseCase(it).map { localDate ->
            WeekdayCard(
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

    internal fun onClickWeekDayCard(): (LocalDate) -> Unit = {
        currentDate.value = it
    }
}