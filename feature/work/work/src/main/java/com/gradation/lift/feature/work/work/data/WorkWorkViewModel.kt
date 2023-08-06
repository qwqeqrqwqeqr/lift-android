package com.gradation.lift.feature.work.work.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WorkWorkViewModel @Inject constructor(
) : ViewModel() {


    internal var onVisibleSuspendDialog = MutableStateFlow(false)
    internal var onVisibleCompleteDialog = MutableStateFlow(false)




    val time = MutableStateFlow(System.currentTimeMillis())
    val restTime = MutableStateFlow(0L)


    val workScreenState: MutableStateFlow<WorkScreenState> =
        MutableStateFlow(WorkScreenState.WorkScreen)


    private var oldTimeMills: Long = 0


    fun updateWorkScreenState(): (WorkScreenState) -> Unit = { state ->
        when (workScreenState.value) {
            WorkScreenState.ListScreen(true) -> {
                workScreenState.update { WorkScreenState.WorkScreen }
            }
            WorkScreenState.ListScreen(false) -> {
                workScreenState.update { WorkScreenState.RestScreen }
            }
            else -> {
                workScreenState.update { state }
            }
        }
    }


    fun visibleSuspendDialog() : () -> Unit = {
        onVisibleSuspendDialog.value=true
    }

    fun invisibleSuspendDialog() : () -> Unit = {
        onVisibleSuspendDialog.value=false
    }

    fun visibleCompleteDialog() : () -> Unit = {
        onVisibleCompleteDialog.value=true
    }

    fun invisibleCompleteDialog() : () -> Unit = {
        onVisibleCompleteDialog.value=false
    }


    fun startRestTime() {

    }

    fun pauseRestTime() {


    }


}



