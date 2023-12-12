package com.gradation.lift.feature.work.work.ui.work

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftCloseTopBar
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.work.data.model.WorkRoutine
import com.gradation.lift.feature.work.work.data.state.WorkDialogUiState
import com.gradation.lift.feature.work.work.data.state.WorkScreenState
import com.gradation.lift.feature.work.work.data.state.WorkScreenUiState
import com.gradation.lift.feature.work.work.data.state.WorkState
import com.gradation.lift.feature.work.work.ui.component.common.WorkProgressCircle
import kotlinx.datetime.LocalTime


@ExperimentalMaterial3Api
@Composable
fun WorkScreen(
    modifier: Modifier = Modifier,
    currentWork: WorkRoutine?,
    preWork: WorkRoutine?,
    nextWork: WorkRoutine?,
    workTime: LocalTime,
    workProgress: Int,
    workState: WorkState,
    workScreenState: WorkScreenState,
) {
    Scaffold(
        topBar = {
            LiftCloseTopBar(
                onCloseClickTopBar = { workScreenState.updateWorkDialogState(WorkDialogUiState.SuspendDialogUi) }
            ) {
                IconButton(onClick = {
                    workScreenState.updateWorkScreenState(WorkScreenUiState.ListScreenUi(true))
                }) {
                    Icon(
                        painter = painterResource(LiftIcon.List),
                        contentDescription = "",
                        tint = Color.Unspecified,
                    )
                }
            }
        }
    ) {
        Column(
            modifier = modifier.padding(it)
        ) {
            Column(
                modifier = modifier
                    .background(LiftTheme.colorScheme.no5)
                    .padding(LiftTheme.space.paddingSpace)
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
            ) {
                WorkProgressCircle(modifier, workState, currentWork, workProgress,workTime)
                WorkSetListView(modifier, currentWork, workState)
            }
            Column(
                modifier = modifier
                    .background(LiftTheme.colorScheme.no5)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(LiftTheme.space.paddingSpace),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)) {
                        preWork?.let {
                            IconButton(
                                modifier = modifier
                                    .align(Alignment.Top)
                                    .size(LiftTheme.space.space32),
                                onClick = workState.pre
                            ) {
                                Icon(
                                    painter = painterResource(LiftIcon.LeftArrowCircle),
                                    contentDescription = "",
                                    tint = Color.Unspecified,
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "이전운동",
                                    color = LiftTheme.colorScheme.no9,
                                    style = LiftTheme.typography.no2,
                                )
                                Text(
                                    modifier = modifier.align(Alignment.Start),
                                    text = it.workCategory.name,
                                    color = LiftTheme.colorScheme.no9,
                                    style = LiftTheme.typography.no6,
                                    textAlign = TextAlign.Left,
                                    overflow = Ellipsis
                                )
                            }

                        }
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)) {
                        nextWork?.let {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = "다음운동",
                                    color = LiftTheme.colorScheme.no9,
                                    style = LiftTheme.typography.no2,
                                )
                                Text(
                                    modifier = modifier.align(Alignment.End),
                                    text = it.workCategory.name,
                                    color = LiftTheme.colorScheme.no9,
                                    style = LiftTheme.typography.no6,
                                    textAlign = TextAlign.Left,
                                    overflow = Ellipsis
                                )
                            }
                            IconButton(
                                modifier = modifier
                                    .align(Alignment.Top)
                                    .size(LiftTheme.space.space32),
                                onClick = workState.next
                            ) {
                                Icon(
                                    painter = painterResource(LiftIcon.RightArrowCircle),
                                    contentDescription = "",
                                    tint = Color.Unspecified,
                                )
                            }
                        }
                    }
                }


                Row(
                    modifier = modifier
                        .background(LiftTheme.colorScheme.no5)
                        .fillMaxWidth()
                        .padding(LiftTheme.space.paddingSpace),
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space10),
                    verticalAlignment = Alignment.Bottom
                ) {
                    LiftOutlineButton(
                        modifier = modifier.weight(1f),
                        shape = RoundedCornerShape(size = LiftTheme.space.space12),
                        enabled = workProgress != 0,
                        onClick = {
                            workScreenState.updateWorkDialogState(WorkDialogUiState.CompleteDialogUi)
                        }
                    ) {
                        Text(
                            text = "운동 완료",
                            style = LiftTheme.typography.no3,
                        )
                    }
                    LiftButton(
                        modifier = modifier.weight(1f),
                        onClick = {
                            workState.updateWorkFlag(false)
                            workScreenState.updateWorkScreenState(WorkScreenUiState.RestScreenUi)
                        },
                    ) {
                        Text(
                            text = "휴식",
                            style = LiftTheme.typography.no3,
                            color = LiftTheme.colorScheme.no5,
                        )
                    }
                }
            }
        }
    }
}