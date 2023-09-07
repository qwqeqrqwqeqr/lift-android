package com.gradation.lift.feature.update_routine.routine

import androidx.lifecycle.ViewModel
import com.gradation.lift.feature.update_routine.routine.data.UpdateRoutineState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class UpdateRoutineRoutineViewModel @Inject constructor(

) : ViewModel() {

    var updateRoutineState: MutableStateFlow<UpdateRoutineState> =
        MutableStateFlow(UpdateRoutineState.None)


    fun updateUpdateRoutineState(): (UpdateRoutineState) -> Unit = {
        updateRoutineState.value = it
    }


}
