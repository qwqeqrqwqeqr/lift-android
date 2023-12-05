package com.gradation.lift.feature.routine_detail.routine.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.routine_detail.routine.data.RoutineUiState
import com.gradation.lift.model.model.date.getWeekdayEntries
import com.gradation.lift.model.model.routine.Label
import com.gradation.lift.ui.mapper.toText


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RoutineScreen(
    modifier: Modifier = Modifier,
    routineUiState: RoutineUiState,
    popBackStack: () -> Unit,
    navigateRoutineDetailGraphToUpdateRoutineGraph: (Int) -> Unit,
    navigateRoutineDetailGraphToWorkWorkRouter: (Int) -> Unit
) {

    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "루틴 상세",
                onBackClickTopBar = popBackStack,
            )
        }
    ) { padding ->
        when (routineUiState) {
            is RoutineUiState.Fail -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(LiftTheme.colorScheme.no17)
                ) {
                }
            }

            RoutineUiState.Loading -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(LiftTheme.colorScheme.no17)
                ) {
                }
            }

            is RoutineUiState.Success -> {
                Column(
                    modifier = modifier.padding(padding)
                ) {
                    Column(
                        modifier = modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .background(LiftTheme.colorScheme.no5)
                                .padding(LiftTheme.space.paddingSpace),
                            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
                        ) {
                            LiftText(
                                textStyle = LiftTextStyle.No3,
                                text = "루틴 프로필",
                                color = LiftTheme.colorScheme.no3,
                                textAlign = TextAlign.Start
                            )
                            GlideImage(
                                modifier = modifier
                                    .size(LiftTheme.space.space96)
                                    .align(Alignment.CenterHorizontally),
                                model = routineUiState.routineSetRoutine.picture,
                                contentDescription = "RoutineProfilePicture"
                            )
                            LiftText(
                                textStyle = LiftTextStyle.No3,
                                text = "루틴 이름",
                                color = LiftTheme.colorScheme.no3,
                                textAlign = TextAlign.Start
                            )
                            Box(
                                modifier = modifier
                                    .border(
                                        width = LiftTheme.space.space2,
                                        color = LiftTheme.colorScheme.no8,
                                        shape = RoundedCornerShape(size = LiftTheme.space.space12)
                                    )
                                    .height(LiftTheme.space.space48)
                                    .background(
                                        color = LiftTheme.colorScheme.no1,
                                        shape = RoundedCornerShape(size = LiftTheme.space.space12)
                                    )
                                    .fillMaxWidth()
                                    .padding(horizontal = LiftTheme.space.space14),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                LiftText(
                                    textStyle = LiftTextStyle.No6,
                                    text = routineUiState.routineSetRoutine.name,
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Start
                                )
                            }
                            if (routineUiState.routineSetRoutine.description.isNotEmpty()) {
                                LiftText(
                                    textStyle = LiftTextStyle.No3,
                                    text = "루틴 설명",
                                    color = LiftTheme.colorScheme.no3,
                                    textAlign = TextAlign.Start
                                )

                                Box(
                                    modifier = modifier
                                        .border(
                                            width = LiftTheme.space.space2,
                                            color = LiftTheme.colorScheme.no8,
                                            shape = RoundedCornerShape(size = LiftTheme.space.space12)
                                        )
                                        .height(LiftTheme.space.space48)
                                        .background(
                                            color = LiftTheme.colorScheme.no1,
                                            shape = RoundedCornerShape(size = LiftTheme.space.space12)
                                        )
                                        .fillMaxWidth()
                                        .padding(horizontal = LiftTheme.space.space14),
                                    contentAlignment = Alignment.CenterStart
                                ) {
                                    LiftText(
                                        textStyle = LiftTextStyle.No6,
                                        text = routineUiState.routineSetRoutine.description,
                                        color = LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Start
                                    )
                                }
                            }
                            LiftText(
                                textStyle = LiftTextStyle.No3,
                                text = "루틴 요일",
                                color = LiftTheme.colorScheme.no3,
                                textAlign = TextAlign.Start
                            )
                            LazyRow(
                                modifier = modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                            ) {
                                item {
                                    Box(
                                        modifier = modifier
                                            .background(
                                                color = if (routineUiState.routineSetRoutine.weekday.size == getWeekdayEntries().size) LiftTheme.colorScheme.no13 else LiftTheme.colorScheme.no1,
                                                shape = RoundedCornerShape(size = LiftTheme.space.space6)
                                            )
                                            .width(LiftTheme.space.space44)
                                            .padding(vertical = LiftTheme.space.space10),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        LiftText(
                                            textStyle = if (routineUiState.routineSetRoutine.weekday.size == getWeekdayEntries().size) LiftTextStyle.No5 else LiftTextStyle.No6,
                                            text = "전체",
                                            color = if (routineUiState.routineSetRoutine.weekday.size == getWeekdayEntries().size) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no9,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                                items(getWeekdayEntries()) {
                                    Box(
                                        modifier = modifier
                                            .background(
                                                color = if (
                                                    routineUiState.routineSetRoutine.weekday.contains(
                                                        it
                                                    ) &&
                                                    routineUiState.routineSetRoutine.weekday.size != getWeekdayEntries().size
                                                ) LiftTheme.colorScheme.no13 else LiftTheme.colorScheme.no1,
                                                shape = RoundedCornerShape(size = LiftTheme.space.space6)
                                            )
                                            .width(LiftTheme.space.space40)
                                            .padding(LiftTheme.space.space10),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        LiftText(
                                            textStyle = if (
                                                routineUiState.routineSetRoutine.weekday.contains(it) &&
                                                routineUiState.routineSetRoutine.weekday.size != getWeekdayEntries().size
                                            ) LiftTextStyle.No5 else LiftTextStyle.No6,
                                            text = it.getWeekdayName(),
                                            color = if (
                                                routineUiState.routineSetRoutine.weekday.contains(it) &&
                                                routineUiState.routineSetRoutine.weekday.size != getWeekdayEntries().size
                                            ) LiftTheme.colorScheme.no5 else LiftTheme.colorScheme.no9,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                            LiftText(
                                textStyle = LiftTextStyle.No3,
                                text = "루틴 라벨",
                                color = LiftTheme.colorScheme.no3,
                                textAlign = TextAlign.Start
                            )

                            Row(
                                modifier = modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                listOf(
                                    Triple(
                                        Label.LABEL1,
                                        LiftIcon.RoutineLabel1,
                                        LiftIcon.CheckedRoutineLabel1
                                    ),
                                    Triple(
                                        Label.LABEL2,
                                        LiftIcon.RoutineLabel2,
                                        LiftIcon.CheckedRoutineLabel2
                                    ),
                                    Triple(
                                        Label.LABEL3,
                                        LiftIcon.RoutineLabel3,
                                        LiftIcon.CheckedRoutineLabel3
                                    ),
                                    Triple(
                                        Label.LABEL4,
                                        LiftIcon.RoutineLabel4,
                                        LiftIcon.CheckedRoutineLabel4
                                    ),
                                    Triple(
                                        Label.LABEL5,
                                        LiftIcon.RoutineLabel5,
                                        LiftIcon.CheckedRoutineLabel5
                                    )
                                ).forEach {
                                    if (routineUiState.routineSetRoutine.label.contains(it.first))
                                        Icon(
                                            modifier = modifier.size(LiftTheme.space.space28),
                                            painter = painterResource(id = it.third),
                                            contentDescription = "",
                                            tint = Color.Unspecified
                                        )
                                    else Icon(
                                        modifier = modifier.size(LiftTheme.space.space28),
                                        painter = painterResource(id = it.second),
                                        contentDescription = "",
                                        tint = Color.Unspecified
                                    )
                                }
                            }
                            LiftText(
                                textStyle = LiftTextStyle.No3,
                                text = "운동 목록",
                                color = LiftTheme.colorScheme.no3,
                                textAlign = TextAlign.Start
                            )
                        }
                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .background(LiftTheme.colorScheme.no17)
                                .padding(LiftTheme.space.paddingSpace),
                            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20)
                        ) {
                            routineUiState.routineSetRoutine.routine.forEach { routine ->
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
                                        LiftText(
                                            textStyle = LiftTextStyle.No3,
                                            text = routine.workCategory.name,
                                            color = LiftTheme.colorScheme.no9,
                                            textAlign = TextAlign.Left
                                        )
                                    }
                                    Column(
                                        modifier = modifier.padding(LiftTheme.space.paddingSpace),
                                        verticalArrangement = Arrangement.spacedBy(
                                            LiftTheme.space.space8
                                        )
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(
                                                24.dp
                                            )
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
                                        }
                                        routine.workSetList.forEachIndexed { index, workSet ->
                                            Row(
                                                modifier = modifier
                                                    .background(
                                                        color = LiftTheme.colorScheme.no1,
                                                        shape = RoundedCornerShape(size = 6.dp)
                                                    )
                                                    .padding(vertical = LiftTheme.space.space12),
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    24.dp
                                                )
                                            ) {
                                                Text(
                                                    modifier = modifier.weight(1f),
                                                    text = "${index + 1}",
                                                    style = LiftTheme.typography.no3,
                                                    color = LiftTheme.colorScheme.no2,
                                                    textAlign = TextAlign.Center,
                                                )
                                                Text(
                                                    modifier = modifier.weight(1f),
                                                    text = workSet.weight.toText(),
                                                    style = LiftTheme.typography.no3,
                                                    color = LiftTheme.colorScheme.no2,
                                                    textAlign = TextAlign.Center,
                                                )
                                                Text(
                                                    modifier = modifier.weight(1f),
                                                    text = "${workSet.repetition}",
                                                    style = LiftTheme.typography.no3,
                                                    color = LiftTheme.colorScheme.no2,
                                                    textAlign = TextAlign.Center,
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Row(
                        modifier = modifier
                            .background(LiftTheme.colorScheme.no17)
                            .padding(LiftTheme.space.paddingSpace),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        LiftOutlineButton(
                            modifier = modifier.weight(1f),
                            shape = RoundedCornerShape(size = 12.dp),
                            onClick = {
                                navigateRoutineDetailGraphToUpdateRoutineGraph(
                                    routineUiState.routineSetRoutine.id
                                )
                            },
                        ) {
                            Text(
                                text = "루틴 수정",
                                style = LiftTheme.typography.no3,
                                color = LiftTheme.colorScheme.no4,
                            )
                        }
                        LiftButton(
                            modifier = modifier.weight(1f),
                            onClick = { navigateRoutineDetailGraphToWorkWorkRouter(routineUiState.routineSetRoutine.id) },
                        ) {
                            Text(
                                text = "운동 시작",
                                style = LiftTheme.typography.no3,
                                color = LiftTheme.colorScheme.no5,
                            )
                        }
                    }
                }
            }
        }
    }
}




