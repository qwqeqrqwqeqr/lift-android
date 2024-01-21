package com.gradation.feature.workReady.ready.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.scrollBy
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.feature.workReady.ready.data.state.WorkRoutineInfoState
import com.gradation.feature.workReady.ready.data.state.ReadyScreenState
import com.gradation.feature.workReady.ready.data.state.SnackBarState
import com.gradation.lift.common.utils.decimalNumberValidator
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.button.LiftPrimaryButton
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.checkBox.LiftCircleCheckbox
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.container.LiftPrimaryContainer
import com.gradation.lift.designsystem.component.filter.LiftAddWorkSetContainer
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
import com.gradation.lift.feature.workReady.common.model.WorkRoutine
import com.gradation.lift.ui.modifier.noRippleClickable
import com.gradation.lift.ui.state.rememberDragDropListState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
internal fun ReadyScreen(
    modifier: Modifier,
    createWork: (List<WorkRoutine>) -> Unit,
    popBackStack: () -> Unit,
    navigateReadyToFindWorkCategoryInWorkReadyGraph: () -> Unit,
    navigateWorkReadyGraphToWorkGraph: () -> Unit,
    currentWorkRoutine: SnapshotStateList<WorkRoutine>,
    workRoutineState: WorkRoutineState,
    workRoutineInfoState: WorkRoutineInfoState,
    readyScreenState: ReadyScreenState,
) {
    val scope = rememberCoroutineScope()
    var overScrollJob by remember { mutableStateOf<Job?>(null) }
    val dragDropListState =
        rememberDragDropListState(onMove = workRoutineState.moveRoutine)

    Scaffold(
        topBar = {
            LiftTopBar(
                title = "운동 준비",
                backgroundColor = LiftTheme.colorScheme.no5,
                onClick = popBackStack,
                actions = { LiftAddWorkSetContainer(modifier.noRippleClickable(onClick = navigateReadyToFindWorkCategoryInWorkReadyGraph)) }
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


        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .background(LiftTheme.colorScheme.no5)
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(LiftTheme.colorScheme.no17)
                    .padding(LiftTheme.space.space20)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space20),
                state = dragDropListState.lazyListState
            ) {
                itemsIndexed(items= currentWorkRoutine,
                    key= {index: Int, item: WorkRoutine -> "$index${item.id}" }) { routineIndex, routine ->
                    val offsetOrNull = dragDropListState.elementDisplacement.takeIf {
                        routineIndex == dragDropListState.currentIndexOfDraggedItem
                    }
                    LiftDefaultContainer(
                        modifier = modifier.graphicsLayer { translationY = offsetOrNull ?: 0f },
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
                                        modifier = modifier.size(LiftTheme.space.space24),
                                        checked = workRoutineInfoState.isSelected(routine.id),
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
                                            text = routine.workCategory.name,
                                            color = LiftTheme.colorScheme.no9,
                                            textAlign = TextAlign.Left
                                        )
                                    }
                                }
                                Icon(
                                    modifier = modifier
                                        .size(LiftTheme.space.space12)
                                        .pointerInput(Unit) {
                                            detectDragGestures(
                                                onDrag = { change, offset ->

                                                    change.consume()
                                                    dragDropListState.onDrag(offset = offset)

                                                    if (overScrollJob?.isActive == true)
                                                        return@detectDragGestures

                                                    dragDropListState
                                                        .checkForOverScroll()
                                                        .takeIf { it != 0f }
                                                        ?.let {
                                                            overScrollJob = scope.launch {
                                                                dragDropListState.lazyListState.scrollBy(
                                                                    it
                                                                )
                                                            }
                                                        } ?: run { overScrollJob?.cancel() }
                                                },
                                                onDragStart = { _ ->
                                                    dragDropListState.onDragStart(
                                                        dragDropListState
                                                            .lazyListState
                                                            .layoutInfo
                                                            .visibleItemsInfo[routineIndex - dragDropListState.lazyListState.firstVisibleItemIndex]
                                                    )
                                                },
                                                onDragEnd = { dragDropListState.onDragInterrupted() },
                                                onDragCancel = { dragDropListState.onDragInterrupted() }
                                            )
                                        },
                                    painter = painterResource(id = LiftIcon.Order),
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
                                if (workRoutineInfoState.isOpened(routine.id)) {
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
                                                        isError = !decimalNumberValidator(workSet.weight) || workSet.weight == "0"
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
                                                        isError = !decimalNumberValidator(workSet.repetition) || workSet.repetition == "0"
                                                    )
                                                }
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Icon(
                                                        modifier = modifier
                                                            .size(LiftTheme.space.space12)
                                                            .noRippleClickable {
                                                                if (workRoutineState.currentWorkRoutine[routineIndex].workSetList.size > 1)
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
                                        text = "루틴추가",
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
            LiftDefaultContainer(
                modifier = modifier
                    .background(LiftTheme.colorScheme.no5)
                    .fillMaxWidth(),
                shape = RectangleShape,
                verticalPadding = LiftTheme.space.space10,
                horizontalPadding = LiftTheme.space.space20
            ) {
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
                                readyScreenState.updateSnackBarState(SnackBarState.RemoveUndo(this.size))

                            }
                            workRoutineInfoState.clearSelectedRoutineList()
                        }
                    )
                    LiftSolidButton(
                        modifier = modifier.weight(1f),
                        enabled =
                        (
                                currentWorkRoutine.isNotEmpty() &&
                                        currentWorkRoutine.flatMap { it.workSetList }
                                            .none { workSet ->
                                                workSet.weight.isEmpty() || workSet.weight.toFloatOrNull() == 0f || !decimalNumberValidator(
                                                    workSet.weight
                                                )
                                            }
                                        &&
                                        currentWorkRoutine.flatMap { it.workSetList }
                                            .none { workSet ->
                                                workSet.repetition.isEmpty() || workSet.repetition.toIntOrNull() == 0 || !decimalNumberValidator(
                                                    workSet.repetition
                                                )
                                            }
                                ),
                        text = "운동 시작하기",
                        onClick = {
                            createWork(currentWorkRoutine.toList())
                            navigateWorkReadyGraphToWorkGraph()
                        }
                    )
                }
            }

        }
    }
}