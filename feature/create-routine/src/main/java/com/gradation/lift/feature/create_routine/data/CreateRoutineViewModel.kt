package com.gradation.lift.feature.create_routine.data

import androidx.lifecycle.ViewModel
import com.gradation.lift.domain.usecase.routine.CreateRoutineSetUseCase
import kotlinx.coroutines.flow.MutableStateFlow
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