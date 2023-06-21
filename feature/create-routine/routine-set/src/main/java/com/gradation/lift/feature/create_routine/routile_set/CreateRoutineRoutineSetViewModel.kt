package com.gradation.lift.feature.create_routine.routile_set

import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.gradation.lift.domain.usecase.routine.CreateRoutineSetUseCase
import com.gradation.lift.model.routine.RoutineSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class CreateRoutineRoutineSetViewModel @Inject constructor(
   private val createRoutineSetUseCase: CreateRoutineSetUseCase

) : ViewModel() {


    private val _routineSetList  = MutableStateFlow(emptyList<RoutineSet>())
    val routineSetList = _routineSetList

    val routineSetName by mutableStateOf(TextFieldValue(""))


    fun createRoutineSet(){


    }


    var testValue by  mutableStateOf(0)

    fun upCount(): () -> Unit = { testValue += 1 }
}

