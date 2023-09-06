package com.gradation.lift.feature.update_routine.routine

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun UpdateRoutineRoutineRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UpdateRoutineRoutineViewModel = hiltViewModel(),
) {


    UpdateRoutineRoutineScreen(
        modifier = modifier
    )
}


@Composable
internal fun UpdateRoutineRoutineScreen(
    modifier: Modifier = Modifier,
) {


}