package com.gradation.lift.feature.updateRoutine.profilePicture.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.updateRoutine.profilePicture.data.state.RoutineSetPictureUiState
import com.gradation.lift.model.model.routine.RoutineSetRoutine


@Composable
fun ProfilePictureScreen(
    modifier: Modifier = Modifier,
    currentRoutineSetRoutine: RoutineSetRoutine,
    routineSetPictureUiState: RoutineSetPictureUiState,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateProfilePictureToRoutineSetInUpdateRoutineGraph: () -> Unit,
) {
    Scaffold(
        topBar = {
            LiftTopBar(
                title = "프로필 사진 등록하기",
                onClick = navigateProfilePictureToRoutineSetInUpdateRoutineGraph,
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(padding)
                .padding(
                    start = LiftTheme.space.space20,
                    end = LiftTheme.space.space20,
                    top = LiftTheme.space.space36
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space36)
        ) {
            LiftMultiStyleText(
                modifier = modifier,
                defaultColor = LiftTheme.colorScheme.no11,
                defaultTextStyle = LiftTextStyle.No1,
                textAlign = TextAlign.Center,
                textWithStyleList = listOf(
                    TextWithStyle(text = "프로필 사진을", color = LiftTheme.colorScheme.no4),
                    TextWithStyle(text = " 선택해주세요"),
                )
            )

            when (routineSetPictureUiState) {
                RoutineSetPictureUiState.Fail -> {}
                RoutineSetPictureUiState.Loading -> {}
                is RoutineSetPictureUiState.Success -> {
                    RoutineProfilePictureList(
                        modifier,
                        currentRoutineSetRoutine,
                        routineSetPictureUiState.routineSetPictureList,
                        currentRoutineSetRoutineState,
                        navigateProfilePictureToRoutineSetInUpdateRoutineGraph
                    )
                }
            }

        }
    }
}


