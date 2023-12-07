package com.gradation.lift.feature.updateRoutine.routineSet.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.feature.updateRoutine.common.data.state.RoutineUiState
import com.gradation.lift.feature.updateRoutine.routineSet.ui.dialog.DeleteDialog
import com.gradation.lift.feature.updateRoutine.routineSet.data.state.RoutineSetScreenState
import com.gradation.lift.feature.updateRoutine.routineSet.ui.component.NavigationView
import com.gradation.lift.feature.updateRoutine.routineSet.ui.component.RoutineSetView
import com.gradation.lift.feature.updateRoutine.routineSet.ui.component.RoutineView
import com.gradation.lift.model.model.routine.RoutineSetRoutine


@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun RoutineSetScreen(
    modifier: Modifier = Modifier,
    currentRoutineSetRoutine: RoutineSetRoutine,
    routineSetNameValidator: Validator,
    routineSetDescriptionValidator: Validator,
    updateCondition: Boolean,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    routineUiState: RoutineUiState,
    deleteRoutineSetRoutine: (Int) -> Unit,
    updateRoutineSetRoutine: (RoutineSetRoutine) -> Unit,
    popBackStack: () -> Unit,
    navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph: () -> Unit,
    navigateRoutineSetToProfilePictureInUpdateRoutineGraph: () -> Unit,
    routineSetScreenState: RoutineSetScreenState
) {
    if (routineSetScreenState.deleteDialogView) {
        Surface(
            color = LiftTheme.colorScheme.no5.copy(alpha = 0.7f),
            modifier = modifier.fillMaxSize()
        ) {
            DeleteDialog(
                modifier = modifier,
                onClickDialogDeleteButton = { deleteRoutineSetRoutine(currentRoutineSetRoutine.id) },
                onClickDialogDismissButton = { routineSetScreenState.updateDeleteDialogView(false) },
            )
        }
    }
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "루틴 수정",
                onBackClickTopBar = popBackStack,
            )
        }
    ) { padding ->
        when (routineUiState) {
            is RoutineUiState.Fail -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(LiftTheme.colorScheme.no17)
                ) {
                }
            }

            RoutineUiState.Loading -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(LiftTheme.colorScheme.no17)
                ) {
                }
            }

            is RoutineUiState.Success -> {


                Column(
                    modifier = modifier
                        .background(LiftTheme.colorScheme.no5)
                        .padding(padding)
                ) {
                    Column(
                        modifier = modifier
                            .verticalScroll(routineSetScreenState.scrollState)
                            .weight(1f)
                    ) {
                        RoutineSetView(
                            modifier,
                            currentRoutineSetRoutine,
                            routineSetNameValidator,
                            routineSetDescriptionValidator,
                            currentRoutineSetRoutineState,
                            navigateRoutineSetToProfilePictureInUpdateRoutineGraph,
                            routineSetScreenState
                        )
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .background(LiftTheme.colorScheme.no5)
                                .padding(LiftTheme.space.paddingSpace),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            LiftText(
                                textStyle = LiftTextStyle.No3,
                                text = "운동 목록",
                                color = LiftTheme.colorScheme.no3,
                                textAlign = TextAlign.Start
                            )

                            LiftOutlineButton(
                                modifier = modifier
                                    .height(32.dp),
                                contentPadding = PaddingValues(
                                    start = 15.dp, top = 0.dp, end = 15.dp, bottom = 0.dp
                                ),
                                onClick = navigateRoutineSetToFindWorkCategoryInUpdateRoutineGraph,
                            ) {
                                Text(
                                    text = "추가",
                                    style = LiftTheme.typography.no5,
                                    color = LiftTheme.colorScheme.no4,
                                )
                                Spacer(modifier = modifier.padding(2.dp))
                                Icon(
                                    painterResource(id = LiftIcon.Plus),
                                    contentDescription = null,
                                )
                            }
                        }
                        RoutineView(modifier, currentRoutineSetRoutine)

                    }
                    Column {
                        NavigationView(
                            modifier,
                            updateCondition,
                            currentRoutineSetRoutine,
                            updateRoutineSetRoutine,
                            routineSetScreenState
                        )
                    }
                }

            }
        }
    }
}




