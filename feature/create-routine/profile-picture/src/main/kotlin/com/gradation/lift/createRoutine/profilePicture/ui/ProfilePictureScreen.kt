package com.gradation.lift.createRoutine.profilePicture.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.feature.createRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.createRoutine.profilePicture.ui.component.LoadingRoutineProfilePictureList
import com.gradation.lift.createRoutine.profilePicture.data.state.RoutineSetPictureUiState
import com.gradation.lift.createRoutine.profilePicture.ui.component.RoutineProfilePictureList
import com.gradation.lift.model.model.routine.RoutineSetRoutine


@Composable
fun ProfilePictureScreen(
    modifier: Modifier = Modifier,
    currentRoutineSetRoutine: RoutineSetRoutine,
    routineSetPictureUiState: RoutineSetPictureUiState,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateProfilePictureToRoutineSetInCreateRoutineGraph: () -> Unit,
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "프로필 등록하기",
                onBackClickTopBar = navigateProfilePictureToRoutineSetInCreateRoutineGraph,
            )
        }
    ) { padding ->
        Column(
            modifier = modifier.padding(padding)
        ) {
            when (routineSetPictureUiState) {
                RoutineSetPictureUiState.Fail -> {}
                RoutineSetPictureUiState.Loading -> {
                    LoadingRoutineProfilePictureList(modifier)
                }

                is RoutineSetPictureUiState.Success -> {
                    RoutineProfilePictureList(
                        modifier,
                        currentRoutineSetRoutine,
                        routineSetPictureUiState.routineSetPictureList,
                        currentRoutineSetRoutineState,
                        navigateProfilePictureToRoutineSetInCreateRoutineGraph
                    )
                }
            }

        }
    }
}


