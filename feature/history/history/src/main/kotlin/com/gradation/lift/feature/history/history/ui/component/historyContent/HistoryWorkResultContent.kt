package com.gradation.lift.feature.history.history.ui.component.historyContent

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.label.LiftNumberLabel
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.history.History
import com.gradation.lift.ui.mapper.toText
import com.gradation.lift.ui.mapper.toTimerText

@Composable
fun HistoryWorkResultContent(
    modifier: Modifier = Modifier,
    selectedHistory: History,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space28)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space14)
        ) {
            Icon(
                modifier = modifier.size(LiftTheme.space.space44),
                painter = painterResource(id = LiftIcon.CheckBoxChecked),
                contentDescription = "CheckBoxChecked",
                tint = Color.Unspecified
            )
            Row {
                LiftText(
                    textStyle = LiftTextStyle.No1,
                    text = "달성도 ",
                    color = LiftTheme.colorScheme.no11,
                    textAlign = TextAlign.Center
                )

                AnimatedContent(
                    targetState = selectedHistory.progress,
                    transitionSpec = {
                        slideIntoContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Up,
                            animationSpec = tween(durationMillis = 300)
                        ) togetherWith ExitTransition.None
                    }, label = ""
                ) {
                    LiftText(
                        modifier = modifier,
                        color = LiftTheme.colorScheme.no4,
                        textStyle = LiftTextStyle.No1,
                        textAlign = TextAlign.Center,
                        text = "${it}%",
                    )
                }
            }

        }
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
        ) {
            Column(modifier = modifier.padding(horizontal = LiftTheme.space.space20)) {
                LiftDefaultContainer(
                    modifier = modifier,
                    verticalPadding = LiftTheme.space.space12,
                    horizontalPadding = LiftTheme.space.space16,
                    shape = RoundedCornerShape(size = LiftTheme.space.space8)
                ) {
                    Column(
                        modifier = modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
                    ) {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Row(
                                modifier = modifier,
                                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = modifier.size(LiftTheme.space.space24),
                                    imageVector = ImageVector.vectorResource(id = LiftIcon.Weight),
                                    contentDescription = "Weight",
                                    tint = Color.Unspecified
                                )
                                LiftText(
                                    textStyle = LiftTextStyle.No4,
                                    text = "오늘 들어올린 무게",
                                    color = LiftTheme.colorScheme.no3,
                                    textAlign = TextAlign.Start
                                )
                            }
                            Row(
                                modifier = modifier,
                                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space1),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AnimatedContent(
                                    targetState = selectedHistory.historyRoutine.flatMap { historyRoutine -> historyRoutine.workSetList }
                                        .map { it.repetition.toFloat() * it.weight }.sum(),
                                    transitionSpec = {
                                        slideIntoContainer(
                                            towards = AnimatedContentTransitionScope.SlideDirection.Up,
                                            animationSpec = tween(durationMillis = 300)
                                        ) togetherWith ExitTransition.None
                                    }, label = ""
                                ) { value ->
                                    LiftText(
                                        textStyle = LiftTextStyle.No2,
                                        text = value.toText(),
                                        color = LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Start
                                    )
                                }
                                LiftText(
                                    textStyle = LiftTextStyle.No2,
                                    text = "kg",
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                modifier = modifier,
                                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = modifier.size(LiftTheme.space.space24),
                                    imageVector = ImageVector.vectorResource(id = LiftIcon.Set),
                                    contentDescription = "Set",
                                    tint = Color.Unspecified

                                )
                                LiftText(
                                    textStyle = LiftTextStyle.No4,
                                    text = "진행한 세트",
                                    color = LiftTheme.colorScheme.no3,
                                    textAlign = TextAlign.Start
                                )
                            }
                            Row(
                                modifier = modifier,
                                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space1),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AnimatedContent(
                                    targetState = selectedHistory.historyRoutine.sumOf { it.workSetList.size },
                                    transitionSpec = {
                                        slideIntoContainer(
                                            towards = AnimatedContentTransitionScope.SlideDirection.Up,
                                            animationSpec = tween(durationMillis = 300)
                                        ) togetherWith ExitTransition.None
                                    }, label = ""
                                ) { value ->
                                    LiftText(
                                        textStyle = LiftTextStyle.No2,
                                        text = "$value",
                                        color = LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Start
                                    )
                                }
                                LiftText(
                                    textStyle = LiftTextStyle.No2,
                                    text = "set",
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }
                }
            }

            Column(
                modifier = modifier.padding(start = LiftTheme.space.space20),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
            ) {
                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No3,
                        text = "운동기록",
                        color = LiftTheme.colorScheme.no3,
                        textAlign = TextAlign.Start
                    )
                    LazyRow(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                    ) {
                        item {
                            LiftDefaultContainer(
                                modifier = modifier,
                                verticalPadding = LiftTheme.space.space12,
                                horizontalPadding = LiftTheme.space.space16,
                                backGroundColor = LiftTheme.colorScheme.no47,
                            ) {
                                Column(
                                    modifier = modifier,
                                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
                                ) {
                                    Icon(
                                        modifier = modifier.size(LiftTheme.space.space24),
                                        imageVector = ImageVector.vectorResource(id = LiftIcon.TimerWhite),
                                        contentDescription = "Timer",
                                        tint = Color.Unspecified

                                    )
                                    Column(
                                        modifier = modifier,
                                        verticalArrangement = Arrangement.spacedBy(
                                            LiftTheme.space.space4
                                        )
                                    ) {
                                        LiftText(
                                            textStyle = LiftTextStyle.No6,
                                            text = "총 운동시간",
                                            color = LiftTheme.colorScheme.no5,
                                            textAlign = TextAlign.Start
                                        )
                                        LiftText(
                                            textStyle = LiftTextStyle.No1,
                                            text = selectedHistory.totalTime.toTimerText(),
                                            color = LiftTheme.colorScheme.no5,
                                            textAlign = TextAlign.Start
                                        )
                                    }
                                }
                            }
                        }
                        item {
                            LiftDefaultContainer(
                                modifier = modifier,
                                verticalPadding = LiftTheme.space.space12,
                                horizontalPadding = LiftTheme.space.space16,
                                backGroundColor = LiftTheme.colorScheme.no48,
                            ) {
                                Column(
                                    modifier = modifier,
                                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
                                ) {
                                    Icon(
                                        modifier = modifier.size(LiftTheme.space.space24),
                                        imageVector = ImageVector.vectorResource(id = LiftIcon.FireWhite),
                                        contentDescription = "Timer",
                                        tint = Color.Unspecified

                                    )
                                    Column(
                                        modifier = modifier,
                                        verticalArrangement = Arrangement.spacedBy(
                                            LiftTheme.space.space4
                                        )
                                    ) {
                                        LiftText(
                                            textStyle = LiftTextStyle.No6,
                                            text = "운동시간",
                                            color = LiftTheme.colorScheme.no5,
                                            textAlign = TextAlign.Start
                                        )
                                        LiftText(
                                            textStyle = LiftTextStyle.No1,
                                            text = selectedHistory.workTime.toTimerText(),
                                            color = LiftTheme.colorScheme.no5,
                                            textAlign = TextAlign.Start
                                        )
                                    }
                                }
                            }
                        }
                        item {
                            LiftDefaultContainer(
                                modifier = modifier,
                                verticalPadding = LiftTheme.space.space12,
                                horizontalPadding = LiftTheme.space.space16,
                                backGroundColor = LiftTheme.colorScheme.no49,
                            ) {
                                Column(
                                    modifier = modifier,
                                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
                                ) {
                                    Icon(
                                        modifier = modifier.size(LiftTheme.space.space24),
                                        imageVector = ImageVector.vectorResource(id = LiftIcon.BottleWhite),
                                        contentDescription = "Timer",
                                        tint = Color.Unspecified

                                    )
                                    Column(
                                        modifier = modifier,
                                        verticalArrangement = Arrangement.spacedBy(
                                            LiftTheme.space.space4
                                        )
                                    ) {
                                        LiftText(
                                            textStyle = LiftTextStyle.No6,
                                            text = "휴식시간",
                                            color = LiftTheme.colorScheme.no5,
                                            textAlign = TextAlign.Start
                                        )
                                        LiftText(
                                            textStyle = LiftTextStyle.No1,
                                            text = selectedHistory.restTime.toTimerText(),
                                            color = LiftTheme.colorScheme.no5,
                                            textAlign = TextAlign.Start
                                        )
                                    }
                                }
                            }
                        }
                        item {
                            LiftDefaultContainer(
                                modifier = modifier,
                                verticalPadding = LiftTheme.space.space12,
                                horizontalPadding = LiftTheme.space.space16,
                                backGroundColor = LiftTheme.colorScheme.no50,
                            ) {
                                Column(
                                    modifier = modifier,
                                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
                                ) {
                                    Icon(
                                        modifier = modifier.size(LiftTheme.space.space24),
                                        imageVector = ImageVector.vectorResource(id = LiftIcon.SweatWhite),
                                        contentDescription = "Timer",
                                        tint = Color.Unspecified

                                    )
                                    Column(
                                        modifier = modifier,
                                        verticalArrangement = Arrangement.spacedBy(
                                            LiftTheme.space.space4
                                        )
                                    ) {
                                        LiftText(
                                            textStyle = LiftTextStyle.No6,
                                            text = "가장 많이한 운동",
                                            color = LiftTheme.colorScheme.no5,
                                            textAlign = TextAlign.Start
                                        )
                                        LiftText(
                                            textStyle = LiftTextStyle.No1,
                                            text = selectedHistory.historyRoutine.maxBy { historyRoutine ->
                                                historyRoutine.workSetList.sumOf { workSet -> workSet.repetition }
                                            }.workCategoryName,
                                            color = LiftTheme.colorScheme.no5,
                                            textAlign = TextAlign.Start
                                        )
                                    }
                                }
                            }
                        }
                        item {
                            LiftDefaultContainer(
                                modifier = modifier,
                                verticalPadding = LiftTheme.space.space12,
                                horizontalPadding = LiftTheme.space.space16,
                                backGroundColor = LiftTheme.colorScheme.no51,
                            ) {
                                Column(
                                    modifier = modifier,
                                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
                                ) {
                                    Icon(
                                        modifier = modifier.size(LiftTheme.space.space24),
                                        imageVector = ImageVector.vectorResource(id = LiftIcon.DumbbellWhite),
                                        contentDescription = "Timer",
                                        tint = Color.Unspecified

                                    )
                                    Column(
                                        modifier = modifier,
                                        verticalArrangement = Arrangement.spacedBy(
                                            LiftTheme.space.space4
                                        )
                                    ) {
                                        LiftText(
                                            textStyle = LiftTextStyle.No6,
                                            text = "가장 무겁게 든 운동",
                                            color = LiftTheme.colorScheme.no5,
                                            textAlign = TextAlign.Start
                                        )
                                        LiftText(
                                            textStyle = LiftTextStyle.No1,
                                            text = selectedHistory.historyRoutine.maxBy { historyRoutine ->
                                                historyRoutine.workSetList.sumOf { workSet -> workSet.weight.toDouble() }
                                            }.workCategoryName,
                                            color = LiftTheme.colorScheme.no5,
                                            textAlign = TextAlign.Start
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                Column(
                    modifier = modifier.padding(end = LiftTheme.space.space20),
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                ) {
                    selectedHistory.historyRoutine.forEachIndexed { routineIndex, routine ->
                        LiftDefaultContainer(
                            modifier = modifier,
                            verticalPadding = LiftTheme.space.space12,
                            horizontalPadding = LiftTheme.space.space16,
                        ) {
                            Column(
                                modifier = modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                            ) {
                                Row(
                                    modifier = modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(
                                        LiftTheme.space.space12
                                    )
                                ) {
                                    LiftNumberLabel(
                                        modifier = modifier,
                                        number = routineIndex + 1
                                    )
                                    LiftText(
                                        textStyle = LiftTextStyle.No3,
                                        text = routine.workCategoryName,
                                        color = LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Left
                                    )
                                }
                                Column(
                                    modifier = modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                                ) {
                                    Row(
                                        modifier = modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        LiftText(
                                            textStyle = LiftTextStyle.No4,
                                            text = "총 세트",
                                            color = LiftTheme.colorScheme.no9,
                                            textAlign = TextAlign.Left
                                        )
                                        LiftMultiStyleText(
                                            defaultColor = LiftTheme.colorScheme.no9,
                                            defaultTextStyle = LiftTextStyle.No4,
                                            textAlign = TextAlign.Left,
                                            textWithStyleList = listOf(
                                                TextWithStyle(
                                                    text = "${routine.workSetList.size}",
                                                    style = LiftTextStyle.No3
                                                ),
                                                TextWithStyle(text = "Set")
                                            )
                                        )
                                    }
                                    Row(
                                        modifier = modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        LiftText(
                                            textStyle = LiftTextStyle.No4,
                                            text = "평균 횟수",
                                            color = LiftTheme.colorScheme.no9,
                                            textAlign = TextAlign.Left
                                        )
                                        LiftMultiStyleText(
                                            defaultColor = LiftTheme.colorScheme.no9,
                                            defaultTextStyle = LiftTextStyle.No4,
                                            textAlign = TextAlign.Left,
                                            textWithStyleList = listOf(
                                                TextWithStyle(
                                                    text = "${
                                                        routine.workSetList.map { workSet -> workSet.repetition }
                                                            .average().toInt()
                                                    }", style = LiftTextStyle.No3
                                                ),
                                                TextWithStyle(text = "Reps")
                                            )
                                        )
                                    }
                                    Row(
                                        modifier = modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        LiftText(
                                            textStyle = LiftTextStyle.No4,
                                            text = "최대 무게",
                                            color = LiftTheme.colorScheme.no9,
                                            textAlign = TextAlign.Left
                                        )
                                        LiftMultiStyleText(
                                            defaultColor = LiftTheme.colorScheme.no9,
                                            defaultTextStyle = LiftTextStyle.No4,
                                            textAlign = TextAlign.Left,
                                            textWithStyleList = listOf(
                                                TextWithStyle(
                                                    text = routine.workSetList
                                                        .maxOf { workSet -> workSet.weight }
                                                        .toText(),
                                                    style = LiftTextStyle.No3
                                                ),
                                                TextWithStyle(text = "kg")
                                            )
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
}