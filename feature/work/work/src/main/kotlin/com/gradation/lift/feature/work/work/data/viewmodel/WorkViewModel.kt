package com.gradation.lift.feature.work.work.data.viewmodel

import androidx.lifecycle.ViewModel
import com.gradation.lift.feature.work.work.data.state.WorkRoutineInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * [WorkViewModel]
 */
@HiltViewModel
class WorkViewModel @Inject constructor(
) : ViewModel() {




    val workRoutineInfoState = WorkRoutineInfoState()
}





