package com.gradation.lift.create_routine.routine_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun CreateRoutineRoutineDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineRoutineDetailViewModel = hiltViewModel()

) {
    CreateRoutineRoutineDetailScreen()
}

@Composable
fun CreateRoutineRoutineDetailScreen(){
    Box(

    ){
        Text(
            text="CreateRoutineRoutineDetail",
            color = Color.Black
        )
    }
}