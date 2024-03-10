package com.gradation.feature.workReady.ready.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import com.gradation.feature.workReady.ready.data.state.ReadyScreenState
import com.gradation.feature.workReady.ready.data.state.SnackBarState
import com.gradation.feature.workReady.ready.data.state.WorkRoutineInfoState
import com.gradation.lift.common.utils.decimalNumberValidator
import com.gradation.lift.designsystem.component.bottomBar.LiftDefaultBottomBar
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.button.LiftPrimaryButton
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.button.smallButton.LiftAddWorkSetButton
import com.gradation.lift.designsystem.component.checkBox.LiftCircleCheckBoxSize
import com.gradation.lift.designsystem.component.checkBox.LiftCircleCheckbox
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.container.LiftPrimaryContainer
import com.gradation.lift.designsystem.component.label.LiftNumberLabel
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.snackbar.SnackBarCategory
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.textField.LiftKeyPadTextField
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.workReady.common.WorkRoutineState
import com.gradation.lift.feature.workReady.common.model.WorkReadyRoutine
import com.gradation.lift.ui.mapper.toText
import com.gradation.lift.ui.modifier.noRippleClickable
import com.gradation.lift.ui.state.dragAndDrop
import com.gradation.lift.ui.state.rememberDragDropListState

@SuppressLint("UnnecessaryComposedModifier")
@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ReadyScreen(
    modifier: Modifier,
    routineSetIdSet: Set<Int>,
    createWork: (List<Int>, List<WorkReadyRoutine>) -> Unit,
    popBackStack: () -> Unit,
    navigateReadyToFindWorkCategoryInWorkReadyGraph: () -> Unit,
    navigateWorkReadyGraphToWorkGraph: () -> Unit,
    currentWorkReadyRoutine: List<WorkReadyRoutine>,
    workRoutineState: WorkRoutineState,
    workRoutineInfoState: WorkRoutineInfoState,
    readyScreenState: ReadyScreenState,
) {

    val hapticFeedback = LocalHapticFeedback.current
    val scope = rememberCoroutineScope()
    val dragDropListState =
        rememberDragDropListState(
            scope = scope,
            hapticFeedback = hapticFeedback,
            onMove = workRoutineState.moveRoutine
        )

    Scaffold(
        topBar = {
            LiftTopBar(
                title = "운동 준비",
                backgroundColor = LiftTheme.colorScheme.no5,
                onClick = popBackStack,
                actions = { LiftAddWorkSetButton(modifier.noRippleClickable(onClick = navigateReadyToFindWorkCategoryInWorkReadyGraph)) }
            )
        },
        snackbarHost = {
            when (readyScreenState.snackBarState) {
                SnackBarState.None -> {}
                SnackBarState.CanNotRemove -> LiftSnackBar(
                    modifier = modifier,
                    snackbarCategory = SnackBarCategory.Warn,
                    snackbarHostState = readyScreenState.snackbarHostState
                )

                is SnackBarState.RemoveUndo -> LiftSnackBar(
                    modifier = modifier,
                    snackbarHostState = readyScreenState.snackbarHostState,
                    snackbarCategory = SnackBarCategory.Info,
                    onClick = workRoutineState.undo
                )
            }
        },
        bottomBar = {
            LiftDefaultBottomBar(
                modifier = modifier
            ) {
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12)
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                    ) {
                        Row(
                            modifier = modifier.weight(1f),
                            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            Row(
                                modifier = modifier,
                                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                                verticalAlignment = Alignment.CenterVertically,

                                ) {
                                androidx.compose.material.Icon(
                                    modifier = modifier.size(LiftTheme.space.space24),
                                    imageVector = ImageVector.vectorResource(id = LiftIcon.Weight),
                                    contentDescription = "Weight",
                                    tint = Color.Unspecified
                                )
                                LiftText(
                                    textStyle = LiftTextStyle.No3,
                                    text = "총 무게",
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
                                    targetState = currentWorkReadyRoutine.flatMap { it.workSetList }
                                        .mapNotNull {
                                            if (it.repetition.toIntOrNull() != null && it.weight.toFloatOrNull() != null)
                                                it.repetition.toInt()
                                                    .toFloat() * it.weight.toFloat()
                                            else null
                                        }.sum(),
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
                                        color = LiftTheme.colorScheme.no4,
                                        textAlign = TextAlign.Start
                                    )
                                }
                                LiftText(
                                    textStyle = LiftTextStyle.No2,
                                    text = "kg",
                                    color = LiftTheme.colorScheme.no4,
                                    textAlign = TextAlign.Start
                                )
                            }


                        }
                        Row(
                            modifier = modifier.weight(1f),
                            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            Row(
                                modifier = modifier,
                                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space4),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                androidx.compose.material.Icon(
                                    modifier = modifier.size(LiftTheme.space.space24),
                                    imageVector = ImageVector.vectorResource(id = LiftIcon.Set),
                                    contentDescription = "Set",
                                    tint = Color.Unspecified

                                )
                                LiftText(
                                    textStyle = LiftTextStyle.No3,
                                    text = "총 세트",
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
                                    targetState = currentWorkReadyRoutine.sumOf { it.workSetList.size },
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
                                        color = LiftTheme.colorScheme.no4,
                                        textAlign = TextAlign.Start
                                    )
                                }
                                LiftText(
                                    textStyle = LiftTextStyle.No2,
                                    text = "set",
                                    color = LiftTheme.colorScheme.no4,
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        LiftDefaultButton(
                            modifier = modifier.weight(1f),
                            text = "삭제",
                            enabled = workRoutineInfoState.isNotEmptySelectedRoutineList(),
                            onClick = {
                                workRoutineInfoState.selectedRoutineList.toList().apply {
                                    workRoutineState.removeRoutines(this)
                                    readyScreenState.updateSnackBarState(
                                        SnackBarState.RemoveUndo(
                                            this.size
                                        )
                                    )

                                }
                                workRoutineInfoState.clearSelectedRoutineList()
                            }
                        )
                        LiftSolidButton(
                            modifier = modifier.weight(1f),
                            enabled =
                            (
                                    currentWorkReadyRoutine.isNotEmpty() &&
                                            currentWorkReadyRoutine.flatMap { it.workSetList }
                                                .none { workSet ->
                                                    workSet.weight.isEmpty() || workSet.weight.toFloatOrNull() == 0f || !decimalNumberValidator(
                                                        workSet.weight
                                                    )
                                                }
                                            &&
                                            currentWorkReadyRoutine.flatMap { it.workSetList }
                                                .none { workSet ->
                                                    workSet.repetition.isEmpty() || workSet.repetition.toIntOrNull() == 0 || !decimalNumberValidator(
                                                        workSet.repetition
                                                    )
                                                }
                                    ),
                            text = "운동 시작하기",
                            onClick = {
                                createWork(
                                    routineSetIdSet.toList(),
                                    currentWorkReadyRoutine.toList()
                                )
                                navigateWorkReadyGraphToWorkGraph()
                            }
                        )
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no17)
                .padding(padding)
                .padding(LiftTheme.space.space20)
                .dragAndDrop(dragDropListState),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20),
            state = dragDropListState.lazyListState
        ) {
            itemsIndexed(
                items = currentWorkReadyRoutine,
                key = { index, item -> "${item.id}${item.workCategoryName}" }
            ) { routineIndex, routine ->

                val isDragging = routineIndex == dragDropListState.currentIndexOfDraggedItem
                val zIndex = if (isDragging) 1f else 0f
                val borderColor: Color by animateColorAsState(
                    targetValue =
                    if (isDragging) LiftTheme.colorScheme.no4.copy(
                        0.2f
                    ) else Color.Transparent,
                    label = "borderColorAnimation",
                )

                LiftDefaultContainer(
                    modifier = modifier
                        .zIndex(zIndex)
                        .composed {
                            if (routineIndex == dragDropListState.currentIndexOfDraggedItem) {
                                graphicsLayer {
                                    translationY = dragDropListState.elementDisplacement ?: 0f
                                }
                            } else {
                                animateItemPlacement()
                            }
                        }
                        .border(
                            width = LiftTheme.space.space2,
                            color = borderColor,
                            shape = RoundedCornerShape(size = LiftTheme.space.space12)
                        ),
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
                                LiftCircleCheckbox(
                                    modifier = modifier,
                                    checked = workRoutineInfoState.isSelected(routine.id),
                                    liftCircleCheckBoxSize = LiftCircleCheckBoxSize.Size24,
                                    onCheckedChange = {
                                        if (workRoutineInfoState.isSelected(routine.id))
                                            workRoutineInfoState.unselectRoutineInfo(routine.id)
                                        else
                                            workRoutineInfoState.selectRoutineInfo(routine.id)
                                    }
                                )
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
                                        text = routine.workCategoryName,
                                        color = LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Left
                                    )
                                }
                            }
                            Icon(
                                modifier = modifier
                                    .size(LiftTheme.space.space12),
                                painter = painterResource(id = LiftIcon.EqualBlack),
                                contentDescription = "changeOrder",
                                tint = LiftTheme.colorScheme.no3,
                            )
                        }

                        Column(
                            modifier = modifier,
                            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                        ) {
                            Row(
                                modifier = modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space28)
                            ) {
                                Row(
                                    modifier = modifier.weight(1f),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                                ) {
                                    LiftText(
                                        modifier = modifier.weight(1f),
                                        textStyle = LiftTextStyle.No3,
                                        text = "Set",
                                        color = LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Center
                                    )
                                    LiftText(
                                        modifier = modifier.weight(1f),
                                        textStyle = LiftTextStyle.No3,
                                        text = "Kg",
                                        color = LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Center
                                    )
                                    LiftText(
                                        modifier = modifier.weight(1f),
                                        textStyle = LiftTextStyle.No3,
                                        text = "Reps",
                                        color = LiftTheme.colorScheme.no9,
                                        textAlign = TextAlign.Center
                                    )
                                }
                                if (workRoutineInfoState.isOpened(routine.id)) {
                                    Icon(
                                        modifier = modifier.noRippleClickable {
                                            workRoutineInfoState.closeRoutineInfo(routine.id)
                                        },
                                        painter = painterResource(id = LiftIcon.ChevronUp),
                                        contentDescription = "${routine}ChevronUp",
                                        tint = LiftTheme.colorScheme.no9
                                    )
                                } else {
                                    Icon(
                                        modifier = modifier.noRippleClickable {
                                            workRoutineInfoState.openRoutineInfo(
                                                routine.id
                                            )
                                        },
                                        painter = painterResource(id = LiftIcon.ChevronDown),
                                        contentDescription = "${routine}ChevronDown",
                                        tint = LiftTheme.colorScheme.no9
                                    )
                                }
                            }
                            AnimatedVisibility(
                                workRoutineInfoState.isOpened(routine.id),
                                label = "workSetAnimation"
                            ) {
                                Column(
                                    modifier = modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
                                ) {


                                    routine.workSetList.forEachIndexed { index, workSet ->
                                        LiftPrimaryContainer(
                                            modifier = modifier,
                                            verticalPadding = LiftTheme.space.space8,
                                            shape = RoundedCornerShape(size = LiftTheme.space.space8)
                                        ) {
                                            Row(
                                                modifier = modifier,
                                                horizontalArrangement = Arrangement.spacedBy(
                                                    LiftTheme.space.space16
                                                ),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
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
                                                            workRoutineState.updateWorkSet(
                                                                routineIndex,
                                                                index,
                                                                workSet.copy(weight = it)
                                                            )
                                                        },
                                                        isError = !decimalNumberValidator(
                                                            workSet.weight
                                                        )
                                                    )

                                                    LiftKeyPadTextField(
                                                        modifier = modifier
                                                            .height(LiftTheme.space.space28)
                                                            .weight(1f),
                                                        value = workSet.repetition,
                                                        onValueChange = {
                                                            workRoutineState.updateWorkSet(
                                                                routineIndex,
                                                                index,
                                                                workSet.copy(repetition = it)
                                                            )
                                                        },
                                                        isError = !decimalNumberValidator(
                                                            workSet.repetition
                                                        ) || workSet.repetition == "0"
                                                    )
                                                }
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Icon(
                                                        modifier = modifier
                                                            .size(LiftTheme.space.space12)
                                                            .noRippleClickable {
                                                                dragDropListState.onDragInterrupted()
                                                                if (workRoutineState.currentWorkReadyRoutineList[routineIndex].workSetList.size > 1)
                                                                    workRoutineState.removeWorkSet(
                                                                        routineIndex,
                                                                        workSet
                                                                    )
                                                                else {
                                                                    workRoutineInfoState.selectedRoutineList
                                                                        .toList()
                                                                        .apply {
                                                                            readyScreenState.updateSnackBarState(
                                                                                SnackBarState.CanNotRemove
                                                                            )
                                                                        }
                                                                }
                                                            },
                                                        painter = painterResource(id = LiftIcon.Close),
                                                        tint = LiftTheme.colorScheme.no10,
                                                        contentDescription = "${routine}ChevronDown"
                                                    )
                                                    Spacer(modifier = modifier.width(LiftTheme.space.space12))
                                                }
                                            }
                                        }
                                    }
                                    LiftPrimaryButton(
                                        modifier = modifier,
                                        text = "세트추가",
                                        onClick = {
                                            workRoutineState.addWorkSet(
                                                routineIndex,
                                                routine.workSetList.last()
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