package com.gradation.lift.create_routine.routine

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CreateRoutineRoutineRoute(
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineRoutineViewModel = hiltViewModel()
    ) {
    CreateRoutineRoutineScreen()
}

@Composable
fun CreateRoutineRoutineScreen(){
    Box(

    ){
        Text(
            text="CreateRoutineRoutine",
            color = Color.Black
        )
    }
}