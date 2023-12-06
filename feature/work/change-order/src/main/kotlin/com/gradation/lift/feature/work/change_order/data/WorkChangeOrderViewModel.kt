package com.gradation.lift.feature.work.change_order.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRoutineSetIdUseCase
import com.gradation.lift.domain.usecase.work.CreateWorkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class WorkChangeOrderViewModel @Inject constructor(
) : ViewModel() {


}