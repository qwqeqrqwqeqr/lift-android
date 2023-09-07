package com.gradation.lift.feature.update_routine.routine

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.update_routine.routine.data.UpdateRoutineState

@Composable
fun UpdateRoutineRoutineRoute(
    navController: NavController,
    navigateRoutineToRoutineSelection: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateRoutineRoutineViewModel = hiltViewModel(),
) {

    val updateRoutineState : UpdateRoutineState by viewModel.updateRoutineState.collectAsStateWithLifecycle()

    val updateUpdateRoutineState :(UpdateRoutineState) -> Unit = viewModel.updateUpdateRoutineState()

    UpdateRoutineRoutineScreen(
        modifier = modifier
    )

    val snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val focusManager: FocusManager = LocalFocusManager.current



    when (val updateRoutineStateResult = updateRoutineState) {
        is UpdateRoutineState.Fail -> {
            LaunchedEffect(true) {
                snackbarHostState.showSnackbar(
                    message = updateRoutineStateResult.message, duration = SnackbarDuration.Short
                )
                updateUpdateRoutineState(UpdateRoutineState.None)
            }
        }
        UpdateRoutineState.None -> {}
        is UpdateRoutineState.Success -> {
            LaunchedEffect(true) {
                navigateRoutineToRoutineSelection()
            }
        }
    }
}


@Composable
internal fun UpdateRoutineRoutineScreen(
    modifier: Modifier = Modifier,
) {


}