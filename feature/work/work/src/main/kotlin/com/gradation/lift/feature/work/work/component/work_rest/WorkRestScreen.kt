package com.gradation.lift.feature.work.work.component.work_rest

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gradation.lift.designsystem.canvas.LiftProgressCircle
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftCloseTopBar
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.work.data.model.WorkRestTime
import com.gradation.lift.feature.work.work.data.model.WorkRoutineSelection
import com.gradation.lift.feature.work.work.data.model.WorkSetSelection
import com.gradation.lift.model.utils.ModelDataGenerator

@ExperimentalMaterial3Api
@Composable
fun WorkRestScreen(
    modifier: Modifier = Modifier,
    onCloseClickTopBar: () -> Unit,
    onListClickTopBar: () -> Unit,
    onClickWorkCompleteButton: () -> Unit,
    onClickWorkButton: () -> Unit,
    updateWorkState: () -> Unit,
    workProgress: Int,
    workTime: WorkRestTime,
    currentWork: WorkRoutineSelection,
) {
    Scaffold(
        topBar = {
            LiftCloseTopBar(
                title = null,
                onCloseClickTopBar = onCloseClickTopBar
            ) {
                IconButton(onClick = onListClickTopBar) {
                    Icon(
                        painter = painterResource(LiftIcon.List),
                        contentDescription = "",
                        tint = Color.Unspecified,
                    )
                }
            }
        },
        floatingActionButton = {
            Row(horizontalArrangement = Arrangement.spacedBy(1.dp)) {
                LiftOutlineButton(
                    modifier = modifier
                        .weight(1f)
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                        ),
                    shape = RoundedCornerShape(size = 12.dp),
                    onClick = onClickWorkCompleteButton,
                ) {
                    Text(
                        text = "운동완료",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no4,
                    )
                }
                LiftButton(

                    modifier = modifier
                        .weight(1f)
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        ),
                    onClick = {
                        updateWorkState()
                        onClickWorkButton()
                    },
                ) {
                    Text(
                        text = "운동재개",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
            }

        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Surface(
            color = LiftTheme.colorScheme.no5,
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .padding(16.dp)
                    .padding(it)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = modifier.fillMaxWidth()
                ) {
                    LiftProgressCircle(
                        modifier = modifier.align(Alignment.Center),
                        progress = workProgress,
                    )
                    Column(
                        modifier = modifier.width(128.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = currentWork.workCategory.name,
                            style = LiftTheme.typography.no1,
                            color = LiftTheme.colorScheme.no11,
                            textAlign = TextAlign.Center
                        )
                        Row(
                            modifier = modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Icon(
                                painter = painterResource(LiftIcon.Timer),
                                contentDescription = "",
                                modifier = modifier.align(Alignment.CenterVertically)
                            )
                            Spacer(modifier = modifier.padding(8.dp))
                            Text(
                                modifier = modifier.align(Alignment.CenterVertically),
                                text = workTime.workTime.toString(),
                                style = LiftTheme.typography.no2,
                                color = LiftTheme.colorScheme.no9,
                                textAlign = TextAlign.Right
                            )
                        }
                        Row(
                            modifier = modifier.align(Alignment.CenterHorizontally),
                        ) {
                            Text(
                                text = "달성도",
                                style = LiftTheme.typography.no2,
                                color = LiftTheme.colorScheme.no9,
                            )
                            Spacer(modifier = modifier.padding(8.dp))
                            Surface(
                                color = LiftTheme.colorScheme.no9,
                                modifier = modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .align(
                                        Alignment.CenterVertically
                                    )
                            ) {
                                Text(
                                    modifier = modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                                    text = "$workProgress%",
                                    style = LiftTheme.typography.no5,
                                    color = LiftTheme.colorScheme.no5,
                                )
                            }

                        }
                    }
                }
                Spacer(modifier = modifier.padding(72.dp))
                Column(
                    modifier= modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "휴식중",
                        color = LiftTheme.colorScheme.no4,
                        style = LiftTheme.typography.no1.copy(
                            fontSize = 28.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = workTime.restTime.toString(),
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no1.copy(
                            fontSize = 32.sp
                        ),
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun WorkRestScreenPreview() {
    LiftMaterialTheme {
        WorkRestScreen(
            modifier = Modifier,
            onCloseClickTopBar = {},
            onListClickTopBar = {},
            onClickWorkCompleteButton = {},
            onClickWorkButton = {},
            updateWorkState = {},
            workTime = WorkRestTime(),
            workProgress = 50,
            currentWork = WorkRoutineSelection(
                index = 2,
                workCategory = ModelDataGenerator.WorkCategory.workCategoryModel1,
                workSetList = listOf(
                    WorkSetSelection(
                        set = Pair(2, 1),
                        weight = 40f,
                        repetition = 12,
                        selected = true
                    ),
                    WorkSetSelection(
                        set = Pair(2, 2),
                        weight = 40f,
                        repetition = 12,
                        selected = false
                    ),
                    WorkSetSelection(
                        set = Pair(2, 3),
                        weight = 40f,
                        repetition = 12,
                        selected = false
                    )
                )
            )
        )
    }
}

