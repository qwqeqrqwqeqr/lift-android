package com.gradation.lift.feature.work.work.ui.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.common.utils.decimalNumberValidator
import com.gradation.lift.designsystem.component.button.LiftPrimaryButton
import com.gradation.lift.designsystem.component.button.smallButton.LiftAddWorkSetButton
import com.gradation.lift.designsystem.component.checkBox.LiftCircleCheckBoxSize
import com.gradation.lift.designsystem.component.checkBox.LiftCircleCheckbox
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.container.LiftPrimaryContainer
import com.gradation.lift.designsystem.component.label.LiftNumberLabel
import com.gradation.lift.designsystem.component.label.WorkCompleteLabel
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.snackbar.SnackBarCategory
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.textField.LiftKeyPadTextField
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.work.common.data.model.WorkRoutine
import com.gradation.lift.feature.work.common.data.model.WorkRoutineCheckedInfo
import com.gradation.lift.feature.work.common.data.state.WorkRoutineInfoState
import com.gradation.lift.feature.work.common.data.state.WorkState
import com.gradation.lift.feature.work.work.data.state.SnackBarState
import com.gradation.lift.feature.work.work.data.state.WorkScreenState
import com.gradation.lift.feature.work.work.data.state.WorkScreenUiState
import com.gradation.lift.ui.modifier.noRippleClickable
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toJavaLocalTime
import java.time.format.DateTimeFormatter

@ExperimentalMaterial3Api
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    progress: Float,
    workTime: LocalTime,
    workState: WorkState,
    navigateWorkToFindWorkCategoryInWorkGraph: () -> Unit,
    workRoutineInfoState: WorkRoutineInfoState,
    workScreenState: WorkScreenState,
) {
    Scaffold(
        topBar = {
            LiftTopBar(
                title = "운동 조회",
                backgroundColor = LiftTheme.colorScheme.no5,
                onClick = {
                    if (
                        workState.workRoutineList.flatMap { it.workSetList }
                            .none { workSet ->
                                workSet.weight.isEmpty() || workSet.weight.toFloatOrNull() == 0f || !decimalNumberValidator(
                                    workSet.weight
                                )
                            }
                        &&
                        workState.workRoutineList.flatMap { it.workSetList }
                            .none { workSet ->
                                workSet.repetition.isEmpty() || workSet.repetition.toIntOrNull() == 0 || !decimalNumberValidator(
                                    workSet.repetition
                                )
                            }
                    ) {
                        workScreenState.updateWorkScreenState(WorkScreenUiState.WorkScreenUi)
                    } else {
                        workScreenState.updateSnackBarState(SnackBarState.Success("잘못 입력한 값이 존재합니다.\n수정 후 다시 시도해주세요."))
                    }
                },
                actions = { LiftAddWorkSetButton(modifier.noRippleClickable(onClick = navigateWorkToFindWorkCategoryInWorkGraph)) }
            )
        },
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarCategory = SnackBarCategory.Warn,
                snackbarHostState = workScreenState.snackbarHostState
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .background(LiftTheme.colorScheme.no5)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = LiftTheme.space.space20,
                        vertical = LiftTheme.space.space20
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LiftText(
                        modifier = modifier,
                        textStyle = LiftTextStyle.No3,
                        text = "달성도",
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center
                    )
                    LiftText(
                        modifier = modifier,
                        textStyle = LiftTextStyle.No3,
                        text = "${progress.toInt()}%",
                        color = LiftTheme.colorScheme.no4,
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = modifier.size(LiftTheme.space.space24),
                        imageVector = ImageVector.vectorResource(id = LiftIcon.Timer),
                        contentDescription = "Timer",
                        tint = LiftTheme.colorScheme.no6
                    )
                    LiftText(
                        modifier = modifier,
                        textStyle = LiftTextStyle.No3,
                        text = with(DateTimeFormatter.ofPattern("HH:mm:ss")) {
                            workTime.toJavaLocalTime().format(this)
                        },
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Center
                    )
                }
            }
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no17)
                    .padding(LiftTheme.space.space20),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20),
            ) {
                itemsIndexed(
                    items = workState.workRoutineList,
                    key = { index: Int, item: WorkRoutine -> "$index${item.id}" })
                { workRoutineIndex, workRoutine ->
                    LiftDefaultContainer(
                        modifier = modifier,
                        shape = RoundedCornerShape(size = LiftTheme.space.space12),
                        horizontalPadding = LiftTheme.space.space16,
                        verticalPadding = LiftTheme.space.space16
                    ) {
                        Column(
                            modifier = modifier,
                            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
                        ) {
                            Row(
                                modifier = modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    modifier = modifier,
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
                                ) {
                                    AnimatedVisibility(
                                        workRoutineInfoState.isAllCheckedWorkRoutine(
                                            workRoutine
                                        )
                                    ) {
                                        WorkCompleteLabel(modifier)
                                    }
                                    LiftNumberLabel(
                                        modifier = modifier,
                                        number = workRoutineIndex + 1
                                    )
                                    LiftText(
                                        textStyle = LiftTextStyle.No3,
                                        text = workRoutine.workCategory.name,
                                        color = LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Left
                                    )
                                }

                                if (workRoutineInfoState.isOpened(workRoutine.id)) {
                                    Icon(
                                        modifier = modifier.noRippleClickable {
                                            workRoutineInfoState.closeRoutineInfo(workRoutine.id)
                                        },
                                        painter = painterResource(id = LiftIcon.ChevronUp),
                                        contentDescription = "${workRoutine}ChevronUp",
                                        tint = LiftTheme.colorScheme.no9
                                    )
                                } else {
                                    Icon(
                                        modifier = modifier.noRippleClickable {
                                            workRoutineInfoState.openRoutineInfo(
                                                workRoutine.id
                                            )
                                        },
                                        painter = painterResource(id = LiftIcon.ChevronDown),
                                        contentDescription = "${workRoutine}ChevronDown",
                                        tint = LiftTheme.colorScheme.no9
                                    )
                                }
                            }
                            AnimatedVisibility(
                                workRoutineInfoState.isOpened(workRoutine.id)
                            ) {
                                Column(
                                    modifier = modifier,
                                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                                ) {
                                    Row(
                                        modifier = modifier
                                            .fillMaxWidth()
                                            .padding(
                                                start = LiftTheme.space.space16,
                                                end = LiftTheme.space.space8
                                            ),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Spacer(modifier = modifier.size(LiftTheme.space.space12))
                                        Row(
                                            modifier = modifier.weight(1f),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                                        ) {
                                            LiftText(
                                                modifier = modifier.weight(1f),
                                                textStyle = LiftTextStyle.No3,
                                                text = "Set",
                                                color = LiftTheme.colorScheme.no3,
                                                textAlign = TextAlign.Center
                                            )
                                            LiftText(
                                                modifier = modifier.weight(1f),
                                                textStyle = LiftTextStyle.No3,
                                                text = "Kg",
                                                color = LiftTheme.colorScheme.no3,
                                                textAlign = TextAlign.Center
                                            )
                                            LiftText(
                                                modifier = modifier.weight(1f),
                                                textStyle = LiftTextStyle.No3,
                                                text = "Reps",
                                                color = LiftTheme.colorScheme.no3,
                                                textAlign = TextAlign.Center
                                            )
                                        }
                                        Row(
                                            modifier = modifier,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Spacer(modifier = modifier.padding(LiftTheme.space.space16))
                                            LiftText(
                                                modifier = modifier,
                                                textStyle = LiftTextStyle.No3,
                                                text = "완료",
                                                color = LiftTheme.colorScheme.no3,
                                                textAlign = TextAlign.Left
                                            )
                                        }
                                    }
                                    workRoutine.workSetList.forEachIndexed { index, workSet ->
                                        LiftPrimaryContainer(
                                            modifier = modifier,
                                            verticalPadding = LiftTheme.space.space8,
                                            shape = RoundedCornerShape(size = LiftTheme.space.space8)
                                        ) {
                                            Row(
                                                modifier = modifier.padding(
                                                    start = LiftTheme.space.space16,
                                                    end = LiftTheme.space.space8
                                                ),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Icon(
                                                    modifier = modifier
                                                        .size(LiftTheme.space.space12)
                                                        .noRippleClickable {
                                                            if (workState.workRoutineList[workRoutineIndex].workSetList.size > 1) {
                                                                workRoutineInfoState.uncheckWorkSet(
                                                                    WorkRoutineCheckedInfo(
                                                                        workRoutine.id,
                                                                        index
                                                                    )
                                                                )
                                                                workState.removeWorkSet(
                                                                    workRoutine.id,
                                                                    workSet
                                                                )
                                                            }
                                                        },
                                                    painter = painterResource(id = LiftIcon.Close),
                                                    tint = LiftTheme.colorScheme.no10,
                                                    contentDescription = "${workRoutine}remove"
                                                )

                                                Row(
                                                    modifier = modifier.weight(1f),
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(
                                                        LiftTheme.space.space8
                                                    )
                                                ) {
                                                    LiftText(
                                                        modifier = modifier.weight(1f),
                                                        textStyle = LiftTextStyle.No3,
                                                        text = "${index + 1}",
                                                        color = LiftTheme.colorScheme.no2,
                                                        textAlign = TextAlign.Center
                                                    )
                                                    LiftKeyPadTextField(
                                                        modifier = modifier
                                                            .height(LiftTheme.space.space28)
                                                            .weight(1f),
                                                        value = workSet.weight,
                                                        onValueChange = {
                                                            workState.updateWorkSet(
                                                                workRoutine.id,
                                                                index,
                                                                workSet.copy(weight = it)
                                                            )
                                                        },
                                                        isError = !decimalNumberValidator(workSet.weight)
                                                    )
                                                    LiftKeyPadTextField(
                                                        modifier = modifier
                                                            .height(LiftTheme.space.space28)
                                                            .weight(1f),
                                                        value = workSet.repetition,
                                                        onValueChange = {
                                                            workState.updateWorkSet(
                                                                workRoutine.id,
                                                                index,
                                                                workSet.copy(repetition = it)
                                                            )
                                                        },
                                                        isError = !decimalNumberValidator(workSet.repetition) || workSet.repetition == "0"
                                                    )
                                                }

                                                Row(
                                                    modifier = modifier,
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Spacer(modifier = modifier.padding(LiftTheme.space.space16))
                                                    LiftCircleCheckbox(
                                                        modifier = modifier,
                                                        checked = workRoutineInfoState.isChecked(
                                                            workRoutine.id,
                                                            index
                                                        ),
                                                        liftCircleCheckBoxSize = LiftCircleCheckBoxSize.Size28,
                                                        onCheckedChange = {
                                                            if (workRoutineInfoState.isChecked(
                                                                    workRoutine.id,
                                                                    index
                                                                )
                                                            )
                                                                workRoutineInfoState.uncheckWorkSet(
                                                                    WorkRoutineCheckedInfo(
                                                                        workRoutine.id,
                                                                        index
                                                                    )
                                                                )
                                                            else workRoutineInfoState.checkWorkSet(
                                                                WorkRoutineCheckedInfo(
                                                                    workRoutine.id,
                                                                    index
                                                                )
                                                            )
                                                        }
                                                    )
                                                }
                                            }
                                        }
                                    }
                                    LiftPrimaryButton(
                                        modifier = modifier,
                                        text = "세트추가",
                                        onClick = {
                                            workState.addWorkSet(
                                                workRoutine.id,
                                                workRoutine.workSetList.last()
                                            )
                                        }
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
