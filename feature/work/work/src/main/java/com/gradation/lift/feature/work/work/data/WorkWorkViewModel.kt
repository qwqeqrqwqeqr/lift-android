package com.gradation.lift.feature.work.work.data

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class WorkWorkViewModel @Inject constructor(
) : ViewModel() {

    val  workScreenState = MutableStateFlow(WorkScreenState.WorkScreen)

}



sealed interface WorkScreenState {

    data class ListScreen(val previousState: Boolean) : WorkScreenState
    object WorkScreen : WorkScreenState
    object RestScreen: WorkScreenState
}
