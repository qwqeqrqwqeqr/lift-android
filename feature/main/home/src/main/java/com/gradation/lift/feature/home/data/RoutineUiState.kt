package com.gradation.lift.feature.home.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.gradation.lift.common.model.DataState
import com.gradation.lift.model.common.Weekday
import com.gradation.lift.model.routine.RoutineSetRoutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate

data class WeekDate(
    var day: String,
    val weekDay: Weekday,
    var localDate: LocalDate?,
    var selected: Boolean,
)


data class WeekDateRoutine(
    val weekDateRoutine: List<RoutineSetRoutine>,
)




