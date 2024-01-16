package com.gradation.lift.feature.work.work.ui.list

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.progress.LiftProgressBar
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.ui.modifier.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.work.data.model.WorkRoutineIdInfo
import com.gradation.lift.feature.work.work.data.state.WorkScreenState
import com.gradation.lift.feature.work.work.data.state.WorkScreenUiState
import com.gradation.lift.feature.work.work.data.state.WorkState
import com.gradation.lift.ui.mapper.toText
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalTime
import java.time.format.DateTimeFormatter

@ExperimentalMaterial3Api
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    workTime: LocalTime,
    workProgress: Int,
    workState: WorkState,
    workScreenState: WorkScreenState,
) {
    val progress: Int by animateIntAsState(
        targetValue = workProgress,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessVeryLow,
        ),
        label = "workProgressAnimation"
    )
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "운동 조회",
                onBackClickTopBar = { workScreenState.updateWorkScreenState(WorkScreenUiState.WorkScreenUi) }
            ) {
                Icon(
                    painter = painterResource(LiftIcon.Timer),
                    contentDescription = "",
                    tint = Color.Unspecified,
                )

                Spacer(modifier = modifier.padding(5.dp))
                Text(
                    text = with(DateTimeFormatter.ofPattern("HH:mm:ss")) {
                        workTime.toJavaLocalTime().format(this)
                    },

                    style = LiftTheme.typography.no2,
                    color = LiftTheme.colorScheme.no9
                )
                Spacer(modifier = modifier.padding(8.dp))

            }
        },
    ) { it ->
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        ) {

            Row(
                modifier = modifier
                    .background(LiftTheme.colorScheme.no5)
                    .padding(LiftTheme.space.paddingSpace)
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
                    backgroundColor = LiftTheme.colorScheme.no1,
                    progressColor = LiftTheme.colorScheme.no4,
                    progress = progress
                )
                Text(
                    modifier = modifier.weight(1f),
                    text = "${workProgress}%",
                    color = LiftTheme.colorScheme.no9,
                    style = LiftTheme.typography.no3,
                    textAlign = TextAlign.Center
                )
            }

            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .background(LiftTheme.colorScheme.no17)
                    .padding(LiftTheme.space.paddingSpace),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
            ) {
                itemsIndexed(workState.currentWorkRoutineList) { workRoutineIndex, workRoutine ->
                    Column(
                        modifier = modifier
                            .shadow(
                                elevation = LiftTheme.space.space8,
                                ambientColor = LiftTheme.colorScheme.no34,
                                spotColor = LiftTheme.colorScheme.no34,
                                shape = RoundedCornerShape(size = LiftTheme.space.space12)
                            )
                            .background(
                                LiftTheme.colorScheme.no5,
                                shape = RoundedCornerShape(size = LiftTheme.space.space12)
                            )

                            .padding(vertical = LiftTheme.space.verticalPaddingSpace)

                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(LiftTheme.space.paddingSpace),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(horizontalArrangement = Arrangement.Start) {


                                LiftText(
                                    textStyle = LiftTextStyle.No3,
                                    text = workRoutine.workCategory.name,
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Left
                                )

                                if (workState.isAllChecked(workRoutine)) {
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

//
                        }
                        Column(
                            modifier = modifier.padding(LiftTheme.space.paddingSpace),
                            verticalArrangement = Arrangement.spacedBy(
                                LiftTheme.space.space8
                            )
                        ) {
                            Row(
                                modifier = modifier
                                    .padding(horizontal = LiftTheme.space.horizontalPaddingSpace),
                            ) {
                                Text(
                                    text = "Set",
                                    style = LiftTheme.typography.no3,
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Center,
                                    modifier = modifier.weight(1f)
                                )
                                Text(
                                    text = "Kg",
                                    style = LiftTheme.typography.no3,
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Center,
                                    modifier = modifier.weight(1f)
                                )
                                Text(
                                    text = "Reps",
                                    style = LiftTheme.typography.no3,
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Center,
                                    modifier = modifier.weight(1f)
                                )
                                Text(
                                    text = "완료",
                                    style = LiftTheme.typography.no3,
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Center,
                                    modifier = modifier.weight(1f)
                                )
                            }
                            workRoutine.workSetList.forEachIndexed { workSetIndex, workSet ->
                                Row(
                                    modifier = modifier
                                        .background(
                                            color = LiftTheme.colorScheme.no1,
                                            shape = RoundedCornerShape(size = LiftTheme.space.space6)
                                        )
                                        .padding(
                                            horizontal = LiftTheme.space.horizontalPaddingSpace,
                                            vertical = LiftTheme.space.space8
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        modifier = modifier.weight(1f),
                                        text = "${workSetIndex + 1}",
                                        style = LiftTheme.typography.no3,
                                        color = LiftTheme.colorScheme.no2,
                                        textAlign = TextAlign.Center,
                                    )
                                    Text(
                                        modifier = modifier.weight(1f),
                                        text = workSet.weight.toFloatOrNull()?.toText()
                                            ?: workSet.weight,
                                        style = LiftTheme.typography.no3,
                                        color = LiftTheme.colorScheme.no2,
                                        textAlign = TextAlign.Center,
                                    )
                                    Text(
                                        modifier = modifier.weight(1f),
                                        text = workSet.repetition,
                                        style = LiftTheme.typography.no3,
                                        color = LiftTheme.colorScheme.no2,
                                        textAlign = TextAlign.Center,
                                    )
                                    if (workState.isChecked(
                                            workRoutine.id,
                                            workSetIndex
                                        )
                                    ) {
                                        Icon(
                                            modifier = modifier
                                                .size(26.dp)
                                                .weight(1f)
                                                .noRippleClickable {
                                                    workState.uncheckWorkSet(
                                                        WorkRoutineIdInfo(
                                                            workRoutine.id,
                                                            workSetIndex
                                                        )
                                                    )
                                                },
                                            painter = painterResource(LiftIcon.CheckBoxChecked),
                                            contentDescription = "",
                                            tint = Color.Unspecified,
                                        )
                                    } else {
                                        Icon(
                                            modifier = modifier
                                                .size(26.dp)
                                                .weight(1f)
                                                .noRippleClickable {
                                                    workState.checkWorkSet(
                                                        WorkRoutineIdInfo(
                                                            workRoutine.id,
                                                            workSetIndex
                                                        )
                                                    )
                                                },
                                            painter = painterResource(LiftIcon.CheckBoxUnChecked),
                                            contentDescription = "",
                                            tint = Color.Unspecified,
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
