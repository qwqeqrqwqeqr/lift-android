package com.gradation.lift.feature.work.work.component.work_work

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
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.gradation.lift.feature.work.work.data.state.WorkScreenState
import com.gradation.lift.model.data_generator.ModelDataGenerator.WorkCategory.workCategoryModel1
import com.gradation.lift.model.data_generator.ModelDataGenerator.WorkCategory.workCategoryModel2

@ExperimentalMaterial3Api
@Composable
fun WorkWorkScreen(
    modifier: Modifier = Modifier,
    onCloseClickTopBar: () -> Unit,
    onListClickTopBar: (WorkScreenState) -> Unit,
    onClickWorkCompleteButton: () -> Unit,
    onClickRestButton: () -> Unit,
    updateWorkState: () -> Unit,
    workTime: WorkRestTime,
    updateCheckedWorkSet: ((Pair<Int, Int>), Boolean) -> Unit,

    updateWorkIndexToPreviousIndex: () -> Unit,
    updateWorkIndexToNextIndex: () -> Unit,
    workProgress: Int,
    currentWork: WorkRoutineSelection,
    previousWork: WorkRoutineSelection?,
    nextWork: WorkRoutineSelection?,
) {
    Scaffold(
        topBar = {
            LiftCloseTopBar(
                title = null,
                onCloseClickTopBar = onCloseClickTopBar
            ) {
                IconButton(onClick = { onListClickTopBar(WorkScreenState.ListScreen(true)) }) {
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
                            bottom = 16.dp
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
                        ),
                    onClick = {
                        updateWorkState()
                        onClickRestButton()
                    },
                ) {
                    Text(
                        text = "휴식",
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
                            modifier = modifier.align(Alignment.CenterHorizontally)
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



                WorkSetListView(
                    modifier = modifier,
                    updateCheckedWorkSet = updateCheckedWorkSet,
                    workSetList = currentWork.workSetList
                )

                Spacer(modifier = modifier.padding(16.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column() {
                        if (previousWork != null) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = updateWorkIndexToPreviousIndex) {
                                    Icon(
                                        painter = painterResource(LiftIcon.LeftArrowCircle),
                                        contentDescription = "",
                                        tint = Color.Unspecified,
                                    )
                                }
                                Spacer(modifier = modifier.padding(2.dp))
                                Text(
                                    text = "이전운동",
                                    color = LiftTheme.colorScheme.no9,
                                    style = LiftTheme.typography.no2,
                                )
                            }
                            Text(
                                text = previousWork.workCategory.name,
                                color = LiftTheme.colorScheme.no9,
                                style = LiftTheme.typography.no6,
                                textAlign = TextAlign.Right,
                                modifier = modifier.align(Alignment.End),
                                overflow = Ellipsis
                            )
                        }
                    }

                    Column {
                        if (nextWork != null) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "다음운동",
                                    color = LiftTheme.colorScheme.no9,
                                    style = LiftTheme.typography.no2,
                                )
                                Spacer(modifier = modifier.padding(2.dp))
                                IconButton(onClick = updateWorkIndexToNextIndex) {
                                    Icon(
                                        painter = painterResource(LiftIcon.RightArrowCircle),
                                        contentDescription = "",
                                        tint = Color.Unspecified,
                                    )
                                }
                            }
                            Text(
                                modifier = modifier.align(Alignment.Start),
                                text = nextWork.workCategory.name,
                                color = LiftTheme.colorScheme.no9,
                                style = LiftTheme.typography.no6,
                                textAlign = TextAlign.Left,
                                overflow = Ellipsis
                            )
                        }
                    }
                }

            }
        }
    }
}


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun WorkWorkScreenPreview() {
    LiftMaterialTheme {
        WorkWorkScreen(
            modifier = Modifier,
            onCloseClickTopBar = {},
            onListClickTopBar = {},
            onClickWorkCompleteButton = {},
            onClickRestButton = {},
            updateWorkState = { },
            workTime = WorkRestTime(),
            updateCheckedWorkSet = { _, _ -> },
            updateWorkIndexToPreviousIndex = {},
            updateWorkIndexToNextIndex = {},
            workProgress = 50,
            currentWork = WorkRoutineSelection(
                index = 2,
                workCategory = workCategoryModel1,
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
            ),
            previousWork = WorkRoutineSelection(
                index = 1,
                workCategory = workCategoryModel2,
                workSetList = listOf()
            ),
            nextWork = WorkRoutineSelection(
                index = 3,
                workCategory = workCategoryModel2,
                workSetList = listOf()
            )
        )
    }
}