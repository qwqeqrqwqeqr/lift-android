package com.gradation.feature.workReady.ready.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.feature.workReady.ready.data.state.WorkRoutineInfoState
import com.gradation.feature.workReady.ready.data.state.ReadyScreenState
import com.gradation.feature.workReady.ready.data.state.SnackBarState
import com.gradation.feature.workReady.ready.data.viewModel.ReadyViewModel
import com.gradation.feature.workReady.ready.data.state.rememberReadyScreenState
import com.gradation.feature.workReady.ready.ui.ReadyScreen
import com.gradation.lift.feature.workReady.common.WorkReadySharedViewModel
import com.gradation.lift.feature.workReady.common.WorkRoutineState
import com.gradation.lift.feature.workReady.common.model.WorkRoutine
import com.gradation.lift.navigation.Route

@Composable
internal fun ReadyRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    routineSetIdList: List<Int>,
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

    val createWork: (List<WorkRoutine>)->Unit = viewModel.createWork()

    LaunchedEffect(routineSetIdList) { sharedViewModel.initRoutineList(routineSetIdList) }
    BackHandler(onBack = popBackStack)


    when (val state = readyScreenState.snackBarState) {
        SnackBarState.CanNotRemove -> {
            LaunchedEffect(true) {
                readyScreenState.snackbarHostState.showSnackbar(
                    message = "세트 수가 1개 이하여서 삭제할 수 없어요",
                    duration = SnackbarDuration.Short
                )
                readyScreenState.updateSnackBarState(SnackBarState.None)
            }
        }

        SnackBarState.None -> {}
        is SnackBarState.RemoveUndo -> {
            LaunchedEffect(true) {
                readyScreenState.snackbarHostState.showSnackbar(
                    message = "${state.size}개의 목록이 삭제됐어요.",
                    actionLabel = "되돌리기",
                    duration = SnackbarDuration.Short
                )
                readyScreenState.updateSnackBarState(SnackBarState.None)
            }
        }
    }

    ReadyScreen(
        modifier,
        createWork,
        popBackStack,
        navigateReadyToFindWorkCategoryInWorkReadyGraph,
        navigateWorkReadyGraphToWorkGraph,
        currentWorkRoutine,
        workRoutineState,
        workRoutineInfoState,
        readyScreenState
    )

}


