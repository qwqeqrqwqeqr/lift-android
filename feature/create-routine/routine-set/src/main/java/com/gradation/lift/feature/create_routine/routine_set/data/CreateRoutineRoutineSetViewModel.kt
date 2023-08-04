package com.gradation.lift.feature.create_routine.routine_set.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class CreateRoutineRoutineSetViewModel  @Inject constructor(

) : ViewModel() {


    val onVisibleCancelDialog = MutableStateFlow(false)


    fun visibleCancelDialog() : () -> Unit = {
        onVisibleCancelDialog.value=true
    }

    fun invisibleCancelDialog() : () -> Unit = {
        onVisibleCancelDialog.value=false
    }

}

