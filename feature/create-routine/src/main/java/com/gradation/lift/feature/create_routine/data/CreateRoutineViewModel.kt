package com.gradation.lift.feature.create_routine.data

import androidx.lifecycle.ViewModel
import com.gradation.lift.domain.usecase.routine.CreateRoutineSetUseCase
import com.gradation.lift.model.common.Weekday
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import javax.inject.Inject

class CreateRoutineViewModel  @Inject constructor(

) : ViewModel() {


    val onVisibleCancelDialog = MutableStateFlow(false)


    fun visibleCancelDialog() : () -> Unit = {
        onVisibleCancelDialog.value=true
    }

    fun invisibleCancelDialog() : () -> Unit = {
        onVisibleCancelDialog.value=false
    }

}

