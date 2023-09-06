package com.gradation.lift.feature.update_routine.routine_selection.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.gradation.lift.feature.update_routine.routine_selection.data.model.RoutineSetRoutineSelection
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * [UpdateRoutineSharedViewModel]
 * @property selectedRoutineSetRoutine 현재 수정을 하기 위해 선택된 루틴
 * @since 2023-09-06 21:07:27
 */
@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class UpdateRoutineSharedViewModel @Inject constructor(
) : ViewModel() {


    val selectedRoutineSetRoutine: MutableStateFlow<RoutineSetRoutine> =
        MutableStateFlow(
            RoutineSetRoutine()
        )


    fun updateSelectedRoutine(): (RoutineSetRoutineSelection) -> Unit = {
        selectedRoutineSetRoutine.value = RoutineSetRoutine(
            id = it.id,
            name = it.name,
            description = it.description,
            weekday = it.weekday,
            picture = it.picture,
            routine = it.routine.map { routine -> routine.routine }
        )
    }

}