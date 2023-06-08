package com.gradation.lift.feature.create_routine.routile_set

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.feature.create_routine.navigateToCreateRoutineRoutineDetail


@Composable
fun CreateRoutineRoutineSetRoute(
    navController : NavController,
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineRoutineSetViewModel = hiltViewModel(),
) {
    CreateRoutineRoutineSetScreen(navController)

}



@Composable
internal fun CreateRoutineRoutineSetScreen(
    navController : NavController
){
    Text("CreateRoutineRoutineSetScreen")

    LiftButton(
        modifier = Modifier,
        onClick = { navController.navigateToCreateRoutineRoutineDetail()},
    ) {
        Text(
            text = "테스트용",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}