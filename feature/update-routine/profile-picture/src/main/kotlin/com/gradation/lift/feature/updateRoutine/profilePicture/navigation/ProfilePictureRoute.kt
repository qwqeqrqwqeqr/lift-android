package com.gradation.lift.feature.updateRoutine.profilePicture.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.updateRoutine.common.data.UpdateRoutineSharedViewModel
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.updateRoutine.profilePicture.ui.ProfilePictureScreen
import com.gradation.lift.feature.updateRoutine.profilePicture.data.state.RoutineSetPictureUiState
import com.gradation.lift.feature.updateRoutine.profilePicture.data.viewmodel.ProfilePictureViewModel
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.navigation.Route

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun ProfilePictureRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateProfilePictureToRoutineSetInUpdateRoutineGraph: () -> Unit,
    viewModel: ProfilePictureViewModel = hiltViewModel(),
    sharedViewModel: UpdateRoutineSharedViewModel = hiltViewModel(remember {
        navController.getBackStackEntry(
            Route.UPDATE_ROUTINE_GRAPH_NAME
        )
    }),
) {

    val currentRoutineSetRoutine : RoutineSetRoutine by sharedViewModel.currentRoutineSetRoutineState.currentRoutineSetRoutine.collectAsStateWithLifecycle()
    val routineSetPictureUiState: RoutineSetPictureUiState by viewModel.routineSetPictureUiState.collectAsStateWithLifecycle()
    val currentRoutineSetRoutineState: CurrentRoutineSetRoutineState =
        sharedViewModel.currentRoutineSetRoutineState


    ProfilePictureScreen(
        modifier,
        currentRoutineSetRoutine,
        routineSetPictureUiState,
        currentRoutineSetRoutineState,
        navigateProfilePictureToRoutineSetInUpdateRoutineGraph
    )

    BackHandler(onBack = navigateProfilePictureToRoutineSetInUpdateRoutineGraph)
}
