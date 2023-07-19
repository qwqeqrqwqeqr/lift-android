package com.gradation.lift.feature.ready_work.change_order.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradation.lift.common.model.DataState
import com.gradation.lift.domain.usecase.routine.GetRoutineSetRoutineByRoutineSetIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ReadyWorkChangeOrderViewModel @Inject constructor(
    getRoutineSetRoutineByRoutineSetIdUseCase: GetRoutineSetRoutineByRoutineSetIdUseCase
) : ViewModel() {


    val routineSetIdList = MutableStateFlow(emptyList<Int>())


    val routineSetRoutine = routineSetIdList.flatMapLatest {
        getRoutineSetRoutineByRoutineSetIdUseCase(it).map {  result ->
            when(result){
                is DataState.Fail -> RoutineSetRoutineUiState.Fail(message = result.message)
                is DataState.Success -> {
                    if(result.data.isEmpty()){
                        RoutineSetRoutineUiState.Empty
                    }else{
                        RoutineSetRoutineUiState.Success(result.data)
                    }
                }
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RoutineSetRoutineUiState.Loading
    )

    fun updateSelectedRoutineSetIdList(SelectedRoutineSetIdList: List<Int>?) {
        SelectedRoutineSetIdList?.let { selectedRoutineSetIdList ->
            routineSetIdList.update { list ->
                list.plus(selectedRoutineSetIdList)
            }
        }
    }
}