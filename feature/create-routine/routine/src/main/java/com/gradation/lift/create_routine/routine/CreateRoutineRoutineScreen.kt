package com.gradation.lift.create_routine.routine

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
internal fun CreateRoutineRoutineRoute(
    modifier: Modifier = Modifier) {
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