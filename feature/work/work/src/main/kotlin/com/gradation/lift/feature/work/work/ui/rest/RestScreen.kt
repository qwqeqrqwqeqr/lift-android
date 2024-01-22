package com.gradation.lift.feature.work.work.ui.rest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.work.data.state.WorkDialogUiState
import com.gradation.lift.feature.work.work.data.state.WorkRoutineInfoState
import com.gradation.lift.feature.work.work.data.state.WorkScreenState
import com.gradation.lift.feature.work.work.data.state.WorkScreenUiState
import com.gradation.lift.feature.work.work.data.state.WorkState
import com.gradation.lift.feature.work.work.navigation.getProgress
import com.gradation.lift.feature.work.work.ui.component.common.WorkProgressCircle
import com.gradation.lift.ui.modifier.noRippleClickable
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalTime
import java.time.format.DateTimeFormatter

@ExperimentalMaterial3Api
@Composable
fun RestScreen(
    modifier: Modifier = Modifier,
    progress: Float,
    workTime: LocalTime,
    restTime: LocalTime,
    workState: WorkState,
    workRoutineInfoState: WorkRoutineInfoState,
    workScreenState: WorkScreenState,
) {
    Scaffold(
        topBar = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = LiftTheme.space.space40,
                        start = LiftTheme.space.space20,
                        end = LiftTheme.space.space20,
                        bottom = LiftTheme.space.space4
                    )
                    .height(LiftTheme.space.space32),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = modifier
                        .noRippleClickable {
                            workScreenState.updateWorkDialogState(WorkDialogUiState.SuspendDialogUi)
                        }
                        .size(LiftTheme.space.space20),
                    painter = painterResource(LiftIcon.Close),
                    contentDescription = "close",
                    tint = LiftTheme.colorScheme.no9,
                )
                Icon(
                    modifier = modifier
                        .noRippleClickable {
                            workScreenState.updateWorkScreenState(
                                WorkScreenUiState.ListScreenUi(
                                    false
                                )
                            )
                        }
                        .size(LiftTheme.space.space20),
                    painter = painterResource(LiftIcon.List),
                    contentDescription = "list",
                    tint = LiftTheme.colorScheme.no9,
                )
            }
        },
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(it)
                .padding(top = LiftTheme.space.space24)
        ) {
            Column(
                modifier = modifier
                    .weight(1f)
                    .padding(LiftTheme.space.space20),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space36)
            ) {
                WorkProgressCircle(
                    modifier,
                    progress,
                    workTime,
                    workState,
                )
                Column(
                    modifier = modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                ) {
                    LiftText(
                        modifier = modifier,
                        textStyle = LiftTextStyle.No1,
                        text = "휴식중",
                        color = LiftTheme.colorScheme.no4,
                        textAlign = TextAlign.Center,
                    )

                    LiftText(
                        modifier = modifier,
                        textStyle = LiftTextStyle.No1,
                        text = with(DateTimeFormatter.ofPattern("HH:mm:ss")) {
                            restTime.toJavaLocalTime().format(this)
                        },
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center,
                    )
                }
            }

            LiftDefaultContainer(
                modifier = modifier
                    .background(LiftTheme.colorScheme.no5)
                    .fillMaxWidth(),
                shape = RectangleShape,
                verticalPadding = LiftTheme.space.space10,
                horizontalPadding = LiftTheme.space.space20
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LiftDefaultButton(
                        modifier = modifier.weight(1f),
                        enabled = getProgress(workState, workRoutineInfoState) != 0f,
                        text = "운동완료",
                        onClick = {
                            workScreenState.updateWorkDialogState(WorkDialogUiState.CompleteDialogUi)
                        }
                    )
                    LiftSolidButton(
                        modifier = modifier.weight(1f),
                        text = "운동재개",
                        onClick = {
                            workState.updateWorkFlag(true)
                            workScreenState.updateWorkScreenState(WorkScreenUiState.WorkScreenUi)
                        },
                    )
                }
            }
        }
    }
}

