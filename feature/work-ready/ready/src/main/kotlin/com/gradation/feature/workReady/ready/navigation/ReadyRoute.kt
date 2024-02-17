package com.gradation.feature.workReady.ready.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.feature.workReady.ready.data.state.ReadyScreenState
import com.gradation.feature.workReady.ready.data.state.SnackBarState
import com.gradation.feature.workReady.ready.data.state.WorkRoutineInfoState
import com.gradation.feature.workReady.ready.data.state.rememberReadyScreenState
import com.gradation.feature.workReady.ready.data.viewModel.ReadyViewModel
import com.gradation.feature.workReady.ready.ui.ReadyScreen
import com.gradation.lift.feature.workReady.common.WorkReadySharedViewModel
import com.gradation.lift.feature.workReady.common.WorkRoutineState
import com.gradation.lift.feature.workReady.common.model.WorkRoutine
import com.gradation.lift.navigation.Route
import com.gradation.lift.ui.extensions.showImmediatelySnackbar

@Composable
internal fun ReadyRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    popBackStack: () -> Unit,
    navigateReadyToFindWorkCategoryInWorkReadyGraph: () -> Unit,
    navigateWorkReadyGraphToWorkGraph: () -> Unit,
    viewModel: ReadyViewModel = hiltViewModel(),
    @SuppressLint("UnrememberedGetBackStackEntry") sharedViewModel: WorkReadySharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.WORK_READY_GRAPH_NAME) }),
    workRoutineState: WorkRoutineState = sharedViewModel.workRoutineState,
    workRoutineInfoState: WorkRoutineInfoState = viewModel.workRoutineInfoState,
    readyScreenState: ReadyScreenState = rememberReadyScreenState(),
) {
    val currentWorkRoutine: SnapshotStateList<WorkRoutine> = workRoutineState.currentWorkRoutine
    val createWork: (List<Int>, List<WorkRoutine>) -> Unit = viewModel.createWork()
    val routineSetIdSet: Set<Int> by sharedViewModel.routineSetIdSet.collectAsStateWithLifecycle()

    LaunchedEffect(routineSetIdSet) {
        sharedViewModel.initRoutineList()
    }
    BackHandler(onBack = popBackStack)


    when (val state = readyScreenState.snackBarState) {
        SnackBarState.CanNotRemove -> {
            LaunchedEffect(true) {
                readyScreenState.snackbarHostState.showImmediatelySnackbar(
                    message = "세트 수가 1개 이하여서 삭제할 수 없어요",

                    )
                readyScreenState.updateSnackBarState(SnackBarState.None)
            }
        }

        SnackBarState.None -> {}
        is SnackBarState.RemoveUndo -> {
            LaunchedEffect(true) {
                readyScreenState.snackbarHostState.showImmediatelySnackbar(
                    message = "${state.size}개의 목록이 삭제되었어요.",
                    actionLabel = "되돌리기",
                    duration = SnackbarDuration.Short,
                )
                readyScreenState.updateSnackBarState(SnackBarState.None)
            }
        }
    }




    ReadyScreen(
        modifier,
        routineSetIdSet,
        createWork,
        popBackStack,
        navigateReadyToFindWorkCategoryInWorkReadyGraph,
        navigateWorkReadyGraphToWorkGraph,
        currentWorkRoutine,
        workRoutineState,
        workRoutineInfoState,
        readyScreenState,
    )

}


