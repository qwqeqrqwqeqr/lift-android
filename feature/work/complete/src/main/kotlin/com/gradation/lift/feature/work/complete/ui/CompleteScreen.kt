package com.gradation.lift.feature.work.complete.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.label.LiftNumberLabel
import com.gradation.lift.designsystem.component.progress.LiftProgressCircle
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.resource.LiftImage
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.common.data.model.WorkRestTime
import com.gradation.lift.feature.work.complete.data.state.CompleteScreenState
import com.gradation.lift.feature.work.complete.data.state.rememberCompleteScreenState
import com.gradation.lift.model.model.history.CreateHistoryRoutine
import com.gradation.lift.ui.mapper.toText
import com.gradation.lift.ui.mapper.toTimerText
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime


@Composable
internal fun CompleteScreen(
    modifier: Modifier = Modifier,
    historyRoutineList: List<CreateHistoryRoutine>,
    historyWorkRestTime: WorkRestTime,
    historyProgress: Float,
    currentTime: LocalDateTime,
    navigateCompleteToCompleteDetailInWorkGraph: () -> Unit,
    completeScreenState: CompleteScreenState,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            LiftDefaultContainer(
                modifier = modifier
                    .background(LiftTheme.colorScheme.no5)
                    .fillMaxWidth(),
                shape = RectangleShape,
                verticalPadding = LiftTheme.space.space10,
                horizontalPadding = LiftTheme.space.space20
            ) {

                LiftSolidButton(
                    modifier = modifier,
                    text = "다음",
                    onClick = navigateCompleteToCompleteDetailInWorkGraph

                )
            }
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            LiftTheme.colorScheme.no5,
                            LiftTheme.colorScheme.no17
                        )
                    )
                )
                .padding(it)
                .verticalScroll(completeScreenState.scrollState),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space24)
        ) {
            Box(modifier = modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = LiftImage.CompleteHalo),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = "completeHalo"
                )
                Column(
                    modifier = modifier.padding(top = LiftTheme.space.space60),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space40)
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                    ) {

                        Image(
                            modifier = modifier.width(LiftTheme.space.space84),
                            painter = painterResource(id = LiftImage.Logo),
                            contentScale = ContentScale.FillWidth,
                            contentDescription = "completeHalo"
                        )
                        LiftText(
                            modifier = modifier,
                            textStyle = LiftTextStyle.No1,
                            text = "${currentTime.monthNumber}월 ${currentTime.dayOfMonth}일 운동완료!",
                            color = LiftTheme.colorScheme.no9,
                            textAlign = TextAlign.Center
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = modifier.fillMaxWidth()
                    ) {
                        LiftProgressCircle(
                            modifier = modifier,
                            progress = historyProgress,
                            stroke = 64f,
                            size = LiftTheme.space.space196
                        )
                        Column(
                            modifier = modifier.width(LiftTheme.space.space148),
                            verticalArrangement = Arrangement.spacedBy(
                                LiftTheme.space.space8,
                                Alignment.Top
                            ),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                modifier = modifier.size(LiftTheme.space.space44),
                                painter = painterResource(id = LiftIcon.CheckBoxChecked),
                                contentDescription = "CheckBoxChecked",
                                tint = Color.Unspecified
                            )
                            Column(
                                modifier = modifier,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                AnimatedContent(
                                    targetState = historyProgress,
                                    label = "historyProgressAnimation"
                                ) { value ->
                                    LiftText(
                                        modifier = modifier,
                                        textStyle = LiftTextStyle.No1,
                                        text = "${value.toInt()}%",
                                        color = LiftTheme.colorScheme.no4,
                                        textAlign = TextAlign.Center
                                    )
                                }
                                LiftText(
                                    modifier = modifier,
                                    textStyle = LiftTextStyle.No1,
                                    text = "달성!",
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
            Column(
                modifier = modifier.padding(horizontal = LiftTheme.space.space20),
            ) {
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
                                verticalAlignment = Alignment.CenterVertically,

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
                                    targetState = historyRoutineList.flatMap { historyRoutine -> historyRoutine.workSetList }
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
                                    targetState = historyRoutineList.sumOf { it.workSetList.size },
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
                                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                                    ) {
                                        LiftText(
                                            textStyle = LiftTextStyle.No6,
                                            text = "총 운동시간",
                                            color = LiftTheme.colorScheme.no5,
                                            textAlign = TextAlign.Start
                                        )
                                        LiftText(
                                            textStyle = LiftTextStyle.No1,
                                            text = historyWorkRestTime.totalTime.toTimerText(),
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
                                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                                    ) {
                                        LiftText(
                                            textStyle = LiftTextStyle.No6,
                                            text = "운동시간",
                                            color = LiftTheme.colorScheme.no5,
                                            textAlign = TextAlign.Start
                                        )
                                        LiftText(
                                            textStyle = LiftTextStyle.No1,
                                            text = historyWorkRestTime.workTime.toTimerText(),
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
                                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                                    ) {
                                        LiftText(
                                            textStyle = LiftTextStyle.No6,
                                            text = "휴식시간",
                                            color = LiftTheme.colorScheme.no5,
                                            textAlign = TextAlign.Start
                                        )
                                        LiftText(
                                            textStyle = LiftTextStyle.No1,
                                            text = historyWorkRestTime.restTime.toTimerText(),
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
                                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                                    ) {
                                        LiftText(
                                            textStyle = LiftTextStyle.No6,
                                            text = "가장 많이한 운동",
                                            color = LiftTheme.colorScheme.no5,
                                            textAlign = TextAlign.Start
                                        )
                                        LiftText(
                                            textStyle = LiftTextStyle.No1,
                                            text = historyRoutineList.maxBy { historyRoutine ->
                                                historyRoutine.workSetList.sumOf { workSet -> workSet.repetition }
                                            }.workCategory,
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
                                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
                                    ) {
                                        LiftText(
                                            textStyle = LiftTextStyle.No6,
                                            text = "가장 무겁게 든 운동",
                                            color = LiftTheme.colorScheme.no5,
                                            textAlign = TextAlign.Start
                                        )
                                        LiftText(
                                            textStyle = LiftTextStyle.No1,
                                            text = historyRoutineList.maxBy { historyRoutine ->
                                                historyRoutine.workSetList.sumOf { workSet -> workSet.weight.toDouble() }
                                            }.workCategory,
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
                    modifier = modifier.padding(
                        end = LiftTheme.space.space20,
                        bottom = LiftTheme.space.space20
                    ),
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                ) {
                    historyRoutineList.forEachIndexed { routineIndex, routine ->
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
                                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
                                ) {
                                    LiftNumberLabel(
                                        modifier = modifier,
                                        number = routineIndex + 1
                                    )
                                    LiftText(
                                        textStyle = LiftTextStyle.No3,
                                        text = routine.workCategory,
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


@Composable
@Preview
fun CompleteScreenPreview(modifier: Modifier = Modifier) {
    LiftMaterialTheme {
        CompleteScreen(
            modifier = modifier,
            historyRoutineList = emptyList(),
            historyWorkRestTime = WorkRestTime(),
            historyProgress = 55f,
            currentTime = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
            navigateCompleteToCompleteDetailInWorkGraph = {},
            completeScreenState = rememberCompleteScreenState(),
        )
    }

}
