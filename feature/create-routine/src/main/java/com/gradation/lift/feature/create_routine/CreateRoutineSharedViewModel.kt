package com.gradation.lift.feature.create_routine

import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gradation.lift.model.routine.RoutineSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class CreateRoutineSharedViewModel @Inject constructor(
   private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _routineSetListUiState  = MutableStateFlow(emptyList<RoutineSet>())
    val routineSetListUiState = _routineSetListUiState

    var routineSetName by mutableStateOf("")
    fun updateRoutineSetName(updateText: String){
        routineSetName = updateText
    }

    fun addRoutineSet(routineSet: RoutineSet){
        _routineSetListUiState.update {
            it.plus(routineSet)
        }
    }

    fun createRoutineSet(){


    }


}

