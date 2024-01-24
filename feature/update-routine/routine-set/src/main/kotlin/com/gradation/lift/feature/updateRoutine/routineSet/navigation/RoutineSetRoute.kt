package com.gradation.lift.feature.updateRoutine.routineSet.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.gradation.lift.feature.updateRoutine.common.data.state.RoutineUiState
import com.gradation.lift.feature.updateRoutine.common.data.UpdateRoutineSharedViewModel
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.updateRoutine.routineSet.ui.RoutineSetScreen
import com.gradation.lift.feature.updateRoutine.routineSet.data.state.RoutineSetScreenState
import com.gradation.lift.feature.updateRoutine.routineSet.data.RoutineSetViewModel
import com.gradation.lift.feature.updateRoutine.routineSet.data.state.UpdateRoutineState
import com.gradation.lift.feature.updateRoutine.routineSet.data.state.rememberRoutineSetScreenState
import com.gradation.lift.feature.updateRoutine.routineSet.ui.dialog.DeleteDialog
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.navigation.Route.UPDATE_ROUTINE_GRAPH_NAME
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.UpdateRoutine.UPDATE_ROUTINE_SET_ID_KEY
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
internal fun RoutineSetRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    popBackStack: () -> Unit,
    navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph: () -> Unit,
    navigateRoutineSetToProfilePictureInUpdateRoutineGraph: () -> Unit,
    navigateUpdateRoutineGraphToRoutineDetailGraph: () -> Unit,
    navigateUpdateRoutineRoutineSetRouterToRoutineDetailRoutineRouter: (Int) -> Unit,
    navigateRoutineSetToUpdateWorkSetInUpdateRoutineGraph: (Int) -> Unit,
    navigateRoutineSetToChangeOrderInUpdateRoutineGraph:()-> Unit,
    viewModel: RoutineSetViewModel = hiltViewModel(),
    sharedViewModel: UpdateRoutineSharedViewModel = hiltViewModel(remember {
        navController.getBackStackEntry(
            UPDATE_ROUTINE_GRAPH_NAME
        )
    }),
    routineSetScreenState: RoutineSetScreenState = rememberRoutineSetScreenState()
) {
    LaunchedEffect(Unit) {
        with(navController.getValueSavedStateHandle<Int>(UPDATE_ROUTINE_SET_ID_KEY)) {
            sharedViewModel.setRoutineSetId(this)
        }
    }

    val currentRoutineSetRoutine: RoutineSetRoutine by sharedViewModel.currentRoutineSetRoutineState.currentRoutineSetRoutine.collectAsStateWithLifecycle()
    val routineSetNameValidator: Validator by sharedViewModel.currentRoutineSetRoutineState.routineSetNameValidator.collectAsStateWithLifecycle()
    val routineSetDescriptionValidator: Validator by sharedViewModel.currentRoutineSetRoutineState.routineSetDescriptionValidator.collectAsStateWithLifecycle()
    val updateCondition: Boolean by sharedViewModel.currentRoutineSetRoutineState.updateCondition.collectAsStateWithLifecycle()
    val currentRoutineSetRoutineState: CurrentRoutineSetRoutineState = sharedViewModel.currentRoutineSetRoutineState
    val routineUiState: RoutineUiState by sharedViewModel.routineUiState.collectAsStateWithLifecycle()

    val deleteRoutineSetRoutine: (Int) -> Unit = viewModel.deleteRoutineSetRoutine()
    val updateRoutineSetRoutine: (RoutineSetRoutine) -> Unit = viewModel.updateRoutineSetRoutine()


    val updateRoutineState: UpdateRoutineState by viewModel.updateRoutineState.collectAsStateWithLifecycle()
    val updateUpdateRoutineState: (UpdateRoutineState) -> Unit =
        viewModel.updateUpdateRoutineState()
    when (val result = updateRoutineState) {
        is UpdateRoutineState.Fail -> {
            LaunchedEffect(result) {
                routineSetScreenState.snackbarHostState.showSnackbar(
                    message = result.message, duration = SnackbarDuration.Short
                )
                updateUpdateRoutineState(UpdateRoutineState.None)
            }
        }

        UpdateRoutineState.None -> {}
        is UpdateRoutineState.Success -> {
            LaunchedEffect(true) {
                if (result.isDeleted) navigateUpdateRoutineGraphToRoutineDetailGraph()
                else navigateUpdateRoutineRoutineSetRouterToRoutineDetailRoutineRouter(currentRoutineSetRoutine.id)
            }
        }
    }

    BackHandler(onBack = popBackStack)


    AnimatedVisibility(visible = routineSetScreenState.deleteDialogView) {
        DeleteDialog(
            modifier = modifier,
            onClickDialogDeleteButton = { deleteRoutineSetRoutine(currentRoutineSetRoutine.id) },
            onClickDialogDismissButton = { routineSetScreenState.updateDeleteDialogView(false) },
        )
    }


    RoutineSetScreen(
        modifier,
        currentRoutineSetRoutine,
        routineSetNameValidator,
        routineSetDescriptionValidator,
        updateCondition,
        currentRoutineSetRoutineState,
        routineUiState,
        updateRoutineSetRoutine,
        popBackStack,
        navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph,
        navigateRoutineSetToProfilePictureInUpdateRoutineGraph,
        navigateRoutineSetToUpdateWorkSetInUpdateRoutineGraph,
        navigateRoutineSetToChangeOrderInUpdateRoutineGraph,
        routineSetScreenState
    )
}