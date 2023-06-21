package com.gradation.lift.feature.create_routine.routile_set

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftTopBar
import com.gradation.lift.feature.create_routine.routile_set.component.RoutineSetListView
import com.gradation.lift.feature.create_routine.routile_set.component.RoutineSetNameView
import com.gradation.lift.feature.create_routine.routile_set.component.RoutineSetWeekDateView
import com.gradation.lift.navigation.navigation.navigateToCreateRoutineRoutineDetail


@Composable
fun CreateRoutineRoutineSetRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineRoutineSetViewModel = hiltViewModel(),
) {

    val routineSetName = viewModel.routineSetName

    CreateRoutineRoutineSetScreen(
        navController,
        modifier,
        routineSetName
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CreateRoutineRoutineSetScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    routineSetName: TextFieldValue,
) {
    Surface(color = MaterialTheme.colorScheme.surface) {

        Scaffold(
            topBar = {
                LiftTopBar(
                    title = "루틴리스트 만들기",
                    onBackClick = navController::popBackStack,
                )
            },
        ) { padding ->
            Column(modifier.padding(padding)) {
                LiftButton(
                    modifier = Modifier,
                    onClick = { navController.navigateToCreateRoutineRoutineDetail() },
                ) {
                    Text(
                        text = "루틴셋",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
                RoutineSetNameView(routineSetName)
                RoutineSetListView()
                RoutineSetWeekDateView()

                LiftButton(
                    modifier = Modifier,
                    onClick = { navController.navigateToCreateRoutineRoutineDetail() },
                ) {
                    Text(
                        text = "운동시작하기",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }

            }
        }
    }

}

