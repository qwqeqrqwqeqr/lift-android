package com.gradation.lift.feature.create_routine.routile_set

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun CreateRoutineRoutineSetRoute(
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineRoutineSetViewModel = hiltViewModel(),
) {
    CreateRoutineRoutineSetScreen()

}



@Composable
internal fun CreateRoutineRoutineSetScreen(
){
    Text("CreateRoutineRoutineSetScreen")
}