package com.gradation.lift.feature.update_routine.routine_set

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.update_routine.routine_set.data.UpdateRoutineState
import com.gradation.lift.feature.update_routine.routine_selection.data.UpdateRoutineSharedViewModel
import com.gradation.lift.model.model.routine.UpdateRoutine
import com.gradation.lift.model.model.routine.UpdateRoutineSetRoutine
import com.gradation.lift.navigation.Router

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun UpdateRoutineRoutineSetRoute(
    navController: NavController,
    navigateRoutineSetToRoutineSelectionInUpdateRoutineGraph: () -> Unit,
    navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph: () -> Unit,
    navigateRoutineSetToProfilePictureInUpdateRoutineGraph: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateRoutineRoutineSetViewModel = hiltViewModel(),
) {
    val workBackStackEntry: NavBackStackEntry =
        remember { navController.getBackStackEntry(Router.UPDATE_ROUTINE_GRAPH_NAME) }
    val sharedViewModel: UpdateRoutineSharedViewModel = hiltViewModel(workBackStackEntry)

    val updateRoutineState: UpdateRoutineState by viewModel.updateRoutineState.collectAsStateWithLifecycle()
    val selectedRoutineSetRoutine: UpdateRoutineSetRoutine by sharedViewModel.selectedRoutineSetRoutine.collectAsStateWithLifecycle()


    val deleteRoutineSetRoutine: (Int) -> Unit = viewModel.deleteRoutineSetRoutine()
    val updateRoutineSetRoutine: (UpdateRoutineSetRoutine) -> Unit = viewModel.updateRoutineSetRoutine()


    val updateUpdateRoutineState: (UpdateRoutineState) -> Unit =
        viewModel.updateUpdateRoutineState()
    val updateSelectedRoutineSetRoutine: (UpdateRoutineSetRoutine) -> Unit =
        sharedViewModel.updateSelectedRoutineSetRoutine()
    val removeRoutine: (UpdateRoutine) -> Unit = sharedViewModel.removeRoutine()


    val snackbarHostState: SnackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
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
                navigateRoutineSetToRoutineSelectionInUpdateRoutineGraph()
            }
        }
    }

    BackHandler(enabled = true, onBack = navigateRoutineSetToRoutineSelectionInUpdateRoutineGraph)


}


@Composable
internal fun UpdateRoutineRoutineSetScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "루틴리스트 수정",
                onBackClickTopBar = {}
            )
        }
    ) {
        Surface(
            color = LiftTheme.colorScheme.no17,
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {

        }
    }

}