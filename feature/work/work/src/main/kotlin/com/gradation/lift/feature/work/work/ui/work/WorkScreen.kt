package com.gradation.lift.feature.work.work.ui.work

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.progress.LiftProgressCircleLabel
import com.gradation.lift.designsystem.component.progress.ProgressCircleState
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.work.data.state.WorkDialogUiState
import com.gradation.lift.feature.work.work.data.state.WorkRoutineInfoState
import com.gradation.lift.feature.work.work.data.state.WorkScreenState
import com.gradation.lift.feature.work.work.data.state.WorkScreenUiState
import com.gradation.lift.feature.work.common.data.WorkState
import com.gradation.lift.feature.work.work.navigation.getProgress
import com.gradation.lift.feature.work.work.ui.component.common.WorkProgressCircle
import com.gradation.lift.ui.modifier.noRippleClickable
import kotlinx.datetime.LocalTime


@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun WorkScreen(
    modifier: Modifier = Modifier,
    progress: Float,
    currentWorkRoutineIndex: Int,
    workTime: LocalTime,
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
                    ),
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space24),
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
                LazyRow(
                    modifier = modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(
                        LiftTheme.space.space10,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    itemsIndexed(workState.workRoutineList) { index, workRoutine ->
                        LiftProgressCircleLabel(
                            modifier = modifier.noRippleClickable {
                                workState.updateCurrentWorkRoutineIndex(index)
                            },
                            number = index+1,
                            state =
                            if (index == currentWorkRoutineIndex) ProgressCircleState.Current
                            else if (workRoutineInfoState.isAllCheckedWorkRoutine(workRoutine)) ProgressCircleState.Done
                            else ProgressCircleState.None
                        )
                    }
                }
                Icon(
                    modifier = modifier
                        .noRippleClickable {
                            workScreenState.updateWorkScreenState(
                                WorkScreenUiState.ListScreenUi(
                                    true
                                )
                            )
                        }
                        .size(LiftTheme.space.space20),
                    painter = painterResource(LiftIcon.List),
                    contentDescription = "list",
                    tint = LiftTheme.colorScheme.no9,
                )
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(it)
                .padding(top = LiftTheme.space.space24)
        ) {
            HorizontalPager(
                modifier = modifier.weight(1f),
                state = workScreenState.pagerState
            ) {
                Column(
                    modifier = modifier
                        .background(LiftTheme.colorScheme.no5)
                        .padding(LiftTheme.space.space20)
                        .fillMaxSize(),
                ) {
                    WorkProgressCircle(
                        modifier,
                        progress,
                        workTime,
                        workState,
                    )
                    WorkSetListView(modifier, workRoutineInfoState, workState)
                }
            }
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space36)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = LiftTheme.space.space20),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        workState.getPreWorkRoutine()?.let { workRoutine ->
                            Icon(
                                modifier = modifier
                                    .align(Alignment.Top)
                                    .size(LiftTheme.space.space32)
                                    .noRippleClickable { workState.minusWorkRoutineIndex() },
                                painter = painterResource(LiftIcon.LeftArrowCircle),
                                contentDescription = "",
                                tint = Color.Unspecified,
                            )
                            LiftText(
                                modifier = modifier,
                                textStyle = LiftTextStyle.No5,
                                text = workRoutine.workCategory.name,
                                color = LiftTheme.colorScheme.no9,
                                textAlign = TextAlign.Left,
                                overflow = Ellipsis
                            )
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        workState.getNextWorkRoutine()?.let { workRoutine ->
                            LiftText(
                                modifier = modifier,
                                textStyle = LiftTextStyle.No5,
                                text = workRoutine.workCategory.name,
                                color = LiftTheme.colorScheme.no9,
                                textAlign = TextAlign.Left,
                                overflow = Ellipsis
                            )
                            Icon(
                                modifier = modifier
                                    .align(Alignment.Top)
                                    .size(LiftTheme.space.space32)
                                    .noRippleClickable { workState.plusWorkRoutineIndex() },
                                painter = painterResource(LiftIcon.RightArrowCircle),
                                contentDescription = "",
                                tint = Color.Unspecified,
                            )
                        }
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
                            text = "휴식",
                            onClick = {
                                workState.updateWorkFlag(false)
                                workScreenState.updateWorkScreenState(WorkScreenUiState.RestScreenUi)
                            },
                        )
                    }
                }
            }
        }
    }
}







