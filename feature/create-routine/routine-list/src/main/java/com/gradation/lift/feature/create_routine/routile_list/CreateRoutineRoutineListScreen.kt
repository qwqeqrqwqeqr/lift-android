package com.gradation.lift.feature.create_routine.routile_list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
internal fun CreateRoutineRoutineListRoute(
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineRoutineListViewModel = hiltViewModel(),
) {
    CreateRoutineRoutineListScreen()

}



@Composable
internal fun CreateRoutineRoutineListScreen(
){
    Text("CreateRoutineRoutineListScreen")
}