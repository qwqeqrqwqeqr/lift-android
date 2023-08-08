package com.gradation.lift.feature.work.work.component.work_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.gradation.lift.designsystem.canvas.LiftProgressBar
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.ToggleCheckbox
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.work.data.model.WorkRestTime
import com.gradation.lift.feature.work.work.data.model.WorkRoutineSelection
import com.gradation.lift.feature.work.work.data.model.WorkSetSelection
import com.gradation.lift.feature.work.work.data.model.initModel
import com.gradation.lift.model.utils.ModelDataGenerator.WorkCategory.workCategoryModel1
import com.gradation.lift.model.utils.ModelDataGenerator.WorkCategory.workCategoryModel2
import com.gradation.lift.ui.utils.toText

@ExperimentalMaterial3Api
@Composable
fun WorkListScreen(
    modifier: Modifier = Modifier,
    onCloseClickTopBar: () -> Unit,
    workTime: WorkRestTime,
    workProgress: Int,
    workList: List<WorkRoutineSelection>,
    updateOpenedWorkRoutine: (WorkRoutineSelection) -> Unit,
    updateCheckedWorkSet: (Pair<Int, Int>, Boolean) -> Unit,
    checkAllSelectedWorkSet: (List<WorkSetSelection>) -> Boolean

) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "운동 조회",
                onBackClickTopBar = onCloseClickTopBar
            ) {
                Icon(
                    painter = painterResource(LiftIcon.Timer),
                    contentDescription = "",
                    tint = Color.Unspecified,
                )
                Spacer(modifier = modifier.padding(5.dp))
                Text(
                    text = workTime.workTime.toString(),
                    style = LiftTheme.typography.no2,
                    color = LiftTheme.colorScheme.no9
                )
                Spacer(modifier = modifier.padding(8.dp))

            }
        },
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Surface(
                color = LiftTheme.colorScheme.no5,
            ) {
                Row(
                    modifier = modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        text = "달성도",
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no3,
                        textAlign = TextAlign.Center
                    )
                    LiftProgressBar(
                        modifier = modifier
                            .fillMaxWidth()
                            .weight(5f),
                        progress = workProgress,
                    )
                    Text(
                        modifier = modifier.weight(1f),
                        text = "$workProgress%",
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no3,
                        textAlign = TextAlign.Center
                    )
                }
            }
            LazyColumn(
                modifier = modifier
                    .padding(16.dp)
            ) {
                itemsIndexed(workList) { index, workRoutine ->
                    Column(
                        modifier = modifier
                            .background(LiftTheme.colorScheme.no5)
                            .border(
                                width = 1.dp,
                                color = LiftTheme.colorScheme.no8,
                                shape =
                                if (workList.size == 1) {
                                    RoundedCornerShape(16.dp)
                                } else if (index == 0) {
                                    RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp)
                                } else if (index == workList.size - 1) {
                                    RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                                } else {
                                    RoundedCornerShape(0.dp)
                                }
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row {
                                Text(
                                    text = workRoutine.workCategory.name,
                                    style = LiftTheme.typography.no3,
                                    color = LiftTheme.colorScheme.no9
                                )

                                if (checkAllSelectedWorkSet(workRoutine.workSetList)) {
                                    Spacer(modifier = modifier.padding(4.dp))
                                    Surface(
                                        color = LiftTheme.colorScheme.no25,
                                        modifier = modifier
                                            .clip(RoundedCornerShape(12.dp))
                                            .align(
                                                Alignment.CenterVertically
                                            )
                                    ) {
                                        Text(
                                            modifier = modifier.padding(
                                                horizontal = 12.dp,
                                                vertical = 2.dp
                                            ),
                                            text = "완료",
                                            style = LiftTheme.typography.no5,
                                            color = LiftTheme.colorScheme.no24,
                                        )
                                    }
                                }
                            }

                            IconButton(onClick = { updateOpenedWorkRoutine(workRoutine) }) {
                                Icon(
                                    painterResource(id = if (workRoutine.opened) LiftIcon.ChevronDown else LiftIcon.ChevronUp),
                                    contentDescription = null,
                                    modifier = modifier
                                )
                            }

                        }
                        if (workRoutine.opened) {
                            Column(
                                modifier = modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(4.dp)

                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                                    modifier = modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                                ) {
                                    Text(
                                        text = "Set",
                                        style = LiftTheme.typography.no2,
                                        color = LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Center,
                                        modifier = modifier.weight(1f)
                                    )
                                    Text(
                                        text = "Kg",
                                        style = LiftTheme.typography.no2,
                                        color = LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Center,
                                        modifier = modifier.weight(1f)
                                    )
                                    Text(
                                        text = "Reps",
                                        style = LiftTheme.typography.no2,
                                        color = LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Center,
                                        modifier = modifier.weight(1f)
                                    )
                                    Text(
                                        text = "완료",
                                        style = LiftTheme.typography.no2,
                                        color = LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Center,
                                        modifier = modifier.weight(1f)
                                    )
                                }
                                workRoutine.workSetList.forEach { workSet ->
                                    Row(
                                        modifier = modifier
                                            .background(LiftTheme.colorScheme.no5)
                                            .border(
                                                width = 1.dp,
                                                color = LiftTheme.colorScheme.no8,
                                                shape = RoundedCornerShape(size = 8.dp)
                                            )
                                            .padding(horizontal = 12.dp, vertical = 8.dp)
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(24.dp)
                                    ) {
                                        Text(
                                            text = "${workSet.set.second}",
                                            style = LiftTheme.typography.no2,
                                            color = if (workSet.selected) LiftTheme.colorScheme.no2 else LiftTheme.colorScheme.no9,
                                            textAlign = TextAlign.Center,
                                            modifier = modifier
                                                .weight(1f)
                                                .align(Alignment.CenterVertically)
                                        )
                                        Text(
                                            text = workSet.weight.toText(),
                                            style = LiftTheme.typography.no2,
                                            color = if (workSet.selected) LiftTheme.colorScheme.no2 else LiftTheme.colorScheme.no9,
                                            textAlign = TextAlign.Center,
                                            modifier = modifier
                                                .weight(1f)
                                                .align(Alignment.CenterVertically)
                                        )

                                        Text(
                                            text = "${workSet.repetition}",
                                            style = LiftTheme.typography.no2,
                                            color = if (workSet.selected) LiftTheme.colorScheme.no2 else LiftTheme.colorScheme.no9,
                                            textAlign = TextAlign.Center,
                                            modifier = modifier
                                                .weight(1f)
                                                .align(Alignment.CenterVertically)
                                        )
                                        ToggleCheckbox(
                                            checked = workSet.selected,
                                            onCheckedChange = {
                                                updateCheckedWorkSet(
                                                    workSet.set,
                                                    it
                                                )
                                            },
                                            modifier = modifier
                                                .size(22.dp)
                                                .weight(1f)

                                        )
                                    }
                                }
                            }
                            Spacer(modifier = modifier.padding(8.dp))
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
fun WorkListScreenPreview() {
    LiftMaterialTheme {
        WorkListScreen(
            modifier = Modifier,
            onCloseClickTopBar = { },
            workTime = WorkRestTime(),
            workProgress = 30,
            workList = listOf(
                initModel.copy(
                    workCategory = workCategoryModel1,
                    opened = false,
                    workSetList = listOf(
                        WorkSetSelection(
                            set = Pair(2, 1),
                            weight = 40f,
                            repetition = 12,
                            selected = false
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
                initModel.copy(
                    workCategory = workCategoryModel2,
                    opened = true,
                    workSetList = listOf(
                        WorkSetSelection(
                            set = Pair(2, 1),
                            weight = 45.5f,
                            repetition = 12,
                            selected = true
                        ),
                        WorkSetSelection(
                            set = Pair(2, 2),
                            weight = 52f,
                            repetition = 12,
                            selected = false
                        ),
                        WorkSetSelection(
                            set = Pair(2, 3),
                            weight = 48.3f,
                            repetition = 12,
                            selected = false
                        )
                    )
                ),
                initModel.copy(
                    workCategory = workCategoryModel1,
                    opened = true,
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
                initModel.copy(
                    workCategory = workCategoryModel2,
                    opened = false,
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
                            selected = true
                        ),
                        WorkSetSelection(
                            set = Pair(2, 3),
                            weight = 40f,
                            repetition = 12,
                            selected = true
                        )
                    )
                )
            ),
            updateOpenedWorkRoutine = { },
            updateCheckedWorkSet = { _, _ -> },
            checkAllSelectedWorkSet = { false },
        )
    }
}