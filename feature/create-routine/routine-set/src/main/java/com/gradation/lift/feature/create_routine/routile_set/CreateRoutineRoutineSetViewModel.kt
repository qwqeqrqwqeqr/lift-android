package com.gradation.lift.feature.create_routine.routile_set

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CreateRoutineRoutineSetViewModel @Inject constructor(
) : ViewModel() {



    var testValue by  mutableStateOf(0)

    fun upCount(): () -> Unit = { testValue += 1 }
}

