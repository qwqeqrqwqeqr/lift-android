package com.gradation.lift.feature.create_routine.routile_set

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gradation.lift.domain.usecase.routine.CreateRoutineSetUseCase
import com.gradation.lift.model.routine.RoutineSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class CreateRoutineRoutineSetViewModel @Inject constructor(
   private val createRoutineSetUseCase: CreateRoutineSetUseCase,
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

