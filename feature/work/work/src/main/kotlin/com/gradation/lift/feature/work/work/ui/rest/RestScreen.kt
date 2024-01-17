package com.gradation.lift.feature.work.work.ui.rest

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
import androidx.compose.ui.unit.sp
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftCloseTopBar
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.common.data.model.WorkRoutine
import com.gradation.lift.feature.work.work.data.state.WorkDialogUiState
import com.gradation.lift.feature.work.work.data.state.WorkScreenState
import com.gradation.lift.feature.work.work.data.state.WorkScreenUiState
import com.gradation.lift.feature.work.work.data.state.WorkState
import com.gradation.lift.feature.work.work.ui.component.common.WorkProgressCircle
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalTime
import java.time.format.DateTimeFormatter

@ExperimentalMaterial3Api
@Composable
fun RestScreen(
    modifier: Modifier = Modifier,
    currentWork: WorkRoutine?,
    workTime: LocalTime,
    restTime: LocalTime,
    workProgress:Int,
    workState: WorkState,
    workScreenState: WorkScreenState,
) {
    Scaffold(
        topBar = {
            LiftCloseTopBar(
                onCloseClickTopBar = { workScreenState.updateWorkDialogState(WorkDialogUiState.SuspendDialogUi) }
            ) {
                IconButton(onClick = {
                    workScreenState.updateWorkScreenState(
                        WorkScreenUiState.ListScreenUi(
                            false
                        )
                    )
                }) {
                    Icon(
                        painter = painterResource(LiftIcon.List),
                        contentDescription = "",
                        tint = Color.Unspecified,
                    )
                }
            }
        },
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
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space72)
            ) {
                WorkProgressCircle(modifier, workState, currentWork,workProgress, workTime)

                Column(
                    modifier = modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                ) {
                    Text(
                        text = "휴식중",
                        color = LiftTheme.colorScheme.no4,
                        style = LiftTheme.typography.no1.copy(fontSize = 28.sp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = with(DateTimeFormatter.ofPattern("HH:mm:ss")) {
                            restTime.toJavaLocalTime().format(this)
                        },
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no4.copy(fontSize = 32.sp),
                        textAlign = TextAlign.Center
                    )

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
                    enabled = workProgress != 0,
                    shape = RoundedCornerShape(size = LiftTheme.space.space12),
                    onClick = { workScreenState.updateWorkDialogState(WorkDialogUiState.CompleteDialogUi) },
                ) {
                    Text(
                        text = "운동완료",
                        style = LiftTheme.typography.no3,
                    )
                }
                LiftButton(
                    modifier = modifier.weight(1f),
                    onClick = {
                        workState.updateWorkFlag(true)
                        workScreenState.updateWorkScreenState(WorkScreenUiState.WorkScreenUi)
                    },
                ) {
                    Text(
                        text = "운동재개",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
            }

        }
    }
}

