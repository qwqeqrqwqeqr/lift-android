package com.gradation.lift.feature.work.common.data

import androidx.lifecycle.ViewModel
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class WorkSharedViewModel @Inject constructor(
) : ViewModel() {

    val historyRoutineList: MutableStateFlow<List<CreateHistoryRoutine>> = MutableStateFlow(emptyList())
    val historyWorkRestTime : MutableStateFlow<WorkRestTime> = MutableStateFlow(WorkRestTime())

    val setHistoryRoutineList: (List<CreateHistoryRoutine>) -> Unit = {
        historyRoutineList.value =it
    }
    val setHistoryWorkRestTime: (WorkRestTime) -> Unit = {
        historyWorkRestTime.value =it
    }
}