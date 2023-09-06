package com.gradation.lift.feature.update_routine.routine_selection

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
internal fun UpdateRoutineRoutineSelectionRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UpdateRoutineRoutineSelectionViewModel = hiltViewModel(),
) {


    UpdateRoutineRoutineSelectionScreen(
        modifier = modifier
    )
}


@Composable
internal fun UpdateRoutineRoutineSelectionScreen(
    modifier: Modifier = Modifier,
) {


}