package com.gradation.lift.feature.home.home.ui.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.brush.workStampBrush
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.container.LiftEmptyContainer
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.progress.LiftProgressBar
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.home.data.state.WorkStampUiState
import com.gradation.lift.model.model.date.toWeekday

@Composable
fun WorkStampView(
    modifier: Modifier = Modifier,
    workStampUiState: WorkStampUiState,
) {
    when (workStampUiState) {
        is WorkStampUiState.Fail -> {}
        WorkStampUiState.Loading -> {
            Spacer(
                modifier = modifier
                    .fillMaxWidth()
                    .height(LiftTheme.space.space96)
                    .padding(horizontal = LiftTheme.space.space20)
                    .background(
                        SkeletonBrush(),
                        RoundedCornerShape(LiftTheme.space.space12)
                    )
            )
        }

        is WorkStampUiState.Success -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = LiftTheme.space.space20),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                ) {
                    LiftIconBox(
                        icon = LiftIcon.DumbbellColor,
                        iconType = IconType.Vector,
                        iconBoxSize = IconBoxSize.Size24,
                        tint = Color.Unspecified
                    )
                    LiftText(
                        textStyle = LiftTextStyle.No2,
                        text = workStampUiState.workStampState.continuousWorkCount.takeIf { it != 0 }
                            ?.let { "${it}일 연속 운동 완료!" } ?: "오늘도 운동 해볼까요?",
                        color = LiftTheme.colorScheme.no3,
                        textAlign = TextAlign.Start
                    )
                }
                LiftDefaultContainer(
                    modifier = modifier.fillMaxWidth(),
                    verticalPadding = LiftTheme.space.space10,
                    horizontalPadding = LiftTheme.space.space10,
                    backGroundBrush = workStampBrush()
                ) {
                    Column(
                        modifier = modifier,
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(horizontal = LiftTheme.space.space14),
                            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                        ) {
                            workStampUiState.workStampState.workByDay.forEach {
                                Column(
                                    modifier = modifier.weight(1f),
                                    verticalArrangement = Arrangement.spacedBy(
                                        LiftTheme.space.space5
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    LiftText(
                                        textStyle = LiftTextStyle.No8,
                                        text = it.second.toWeekday()
                                            .getWeekdayName(),
                                        color = LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Center
                                    )
                                    if (workStampUiState.workStampState.today.toEpochDays() < it.second.toEpochDays()) {
                                        Box(
                                            modifier = modifier.size(LiftTheme.space.space32),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            LiftText(
                                                textStyle = LiftTextStyle.No4,
                                                text = "${it.second.dayOfMonth}",
                                                color = LiftTheme.colorScheme.no7,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                    } else if (workStampUiState.workStampState.today.toEpochDays() == it.second.toEpochDays()) {
                                        if (it.first)
                                            LiftIconBox(
                                                icon = LiftIcon.CheckBoxChecked,
                                                iconType = IconType.Vector,
                                                iconBoxSize = IconBoxSize.Size32
                                            )
                                        else
                                            Box(
                                                modifier = modifier.size(LiftTheme.space.space32),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                LiftText(
                                                    textStyle = LiftTextStyle.No4,
                                                    text = it.second.dayOfMonth.toString(),
                                                    color = LiftTheme.colorScheme.no7,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                    } else {
                                        if (it.first)
                                            LiftIconBox(
                                                icon = LiftIcon.CheckBoxChecked,
                                                iconType = IconType.Vector,
                                                iconBoxSize = IconBoxSize.Size32
                                            )
                                        else
                                            LiftIconBox(
                                                icon = LiftIcon.CheckBoxCanceled,
                                                iconType = IconType.Vector,
                                                iconBoxSize = IconBoxSize.Size32
                                            )
                                    }
                                }
                            }
                        }
                        LiftEmptyContainer(
                            modifier = modifier,
                            backGroundColor = LiftTheme.colorScheme.no5.copy(0.4f),
                            verticalPadding = LiftTheme.space.space8,
                            shape = RoundedCornerShape(LiftTheme.space.space8)
                        ) {
                            Column(
                                modifier = modifier,
                                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space6)
                            ) {
                                Row(
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = LiftTheme.space.space12),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    LiftText(
                                        textStyle = LiftTextStyle.No8,
                                        text = "이번달 운동횟수",
                                        color = LiftTheme.colorScheme.no3,
                                        textAlign = TextAlign.Start
                                    )
                                    LiftMultiStyleText(
                                        modifier,
                                        LiftTheme.colorScheme.no9,
                                        LiftTextStyle.No8,
                                        listOf(
                                            TextWithStyle(
                                                text = "${workStampUiState.workStampState.countOfWorkInMonth}",
                                                color = LiftTheme.colorScheme.no4,
                                            ),
                                            TextWithStyle(text = "/${workStampUiState.workStampState.endOfDay}"),
                                        ),
                                        TextAlign.Start
                                    )
                                }
                                AnimatedContent(
                                    targetState = (workStampUiState.workStampState.countOfWorkInMonth.toFloat() / workStampUiState.workStampState.endOfDay.toFloat()) * 100f,
                                    label = "progressBarTransition"
                                ) { progress ->
                                    LiftProgressBar(
                                        modifier = modifier.height(LiftTheme.space.space24),
                                        progress = progress,
                                        stroke = LiftTheme.space.space16
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}