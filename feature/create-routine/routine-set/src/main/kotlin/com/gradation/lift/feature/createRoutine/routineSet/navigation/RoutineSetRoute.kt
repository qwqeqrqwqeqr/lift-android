package com.gradation.lift.feature.createRoutine.routineSet.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.feature.createRoutine.common.data.CreateRoutineSharedViewModel
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.createRoutine.routineSet.ui.RoutineSetScreen
import com.gradation.lift.feature.createRoutine.routineSet.data.state.RoutineSetScreenState
import com.gradation.lift.feature.createRoutine.routineSet.data.RoutineSetViewModel
import com.gradation.lift.feature.createRoutine.routineSet.data.state.CreateRoutineState
import com.gradation.lift.feature.createRoutine.routineSet.data.state.rememberRoutineSetScreenState
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_GRAPH_NAME

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
internal fun RoutineSetRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph: () -> Unit,
    navigateRoutineSetToProfilePictureInCreateRoutineGraph: () -> Unit,
    navigateCreateRoutineGraphToRoutineDetailGraph: () -> Unit,
    viewModel: RoutineSetViewModel = hiltViewModel(),
    sharedViewModel: CreateRoutineSharedViewModel = hiltViewModel(remember {
        navController.getBackStackEntry(
            CREATE_ROUTINE_GRAPH_NAME
        )
    }),
    routineSetScreenState: RoutineSetScreenState = rememberRoutineSetScreenState(),
) {

    val currentRoutineSetRoutine: RoutineSetRoutine by sharedViewModel.currentRoutineSetRoutineState.currentRoutineSetRoutine.collectAsStateWithLifecycle()
    val routineSetNameValidator: Validator by sharedViewModel.currentRoutineSetRoutineState.routineSetNameValidator.collectAsStateWithLifecycle()
    val routineSetDescriptionValidator: Validator by sharedViewModel.currentRoutineSetRoutineState.routineSetDescriptionValidator.collectAsStateWithLifecycle()
    val updateCondition: Boolean by sharedViewModel.currentRoutineSetRoutineState.updateCondition.collectAsStateWithLifecycle()
    val currentRoutineSetRoutineState: CurrentRoutineSetRoutineState =
        sharedViewModel.currentRoutineSetRoutineState
    val createRoutineSetRoutine: (RoutineSetRoutine) -> Unit = viewModel.createRoutineSetRoutine()


    val createRoutineState: CreateRoutineState by viewModel.createRoutineState.collectAsStateWithLifecycle()
    val updateUpdateRoutineState: (CreateRoutineState) -> Unit =
        viewModel.updateCreateRoutineState()
    when (val result = createRoutineState) {
        is CreateRoutineState.Fail -> {
            LaunchedEffect(result) {
                routineSetScreenState.snackbarHostState.showSnackbar(
                    message = result.message, duration = SnackbarDuration.Short
                )
                updateUpdateRoutineState(CreateRoutineState.None)
            }
        }

        CreateRoutineState.None -> {}
        is CreateRoutineState.Success -> {
            LaunchedEffect(true) {
                navigateCreateRoutineGraphToRoutineDetailGraph()
            }
        }
    }

    BackHandler(onBack = {
        if (currentRoutineSetRoutine.routine.isEmpty()) {
            navigateCreateRoutineGraphToRoutineDetailGraph()
        } else {
            routineSetScreenState.updateCancelDialogView(true)
        }
    })


    RoutineSetScreen(
        modifier,
        currentRoutineSetRoutine,
        routineSetNameValidator,
        routineSetDescriptionValidator,
        updateCondition,
        currentRoutineSetRoutineState,
        createRoutineSetRoutine,
        navigateRoutineSetToFindWorkCategoryInCreateRoutineGraph,
        navigateRoutineSetToProfilePictureInCreateRoutineGraph,
        navigateCreateRoutineGraphToRoutineDetailGraph,
        routineSetScreenState
    )
}