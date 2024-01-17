package com.gradation.lift.feature.updateRoutine.changeOrder.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.container.LiftDefaultContainer
import com.gradation.lift.designsystem.component.container.LiftEmptyContainer
import com.gradation.lift.designsystem.component.container.LiftPrimaryContainer
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.updateRoutine.common.data.state.CurrentRoutineSetRoutineState
import com.gradation.lift.model.model.routine.RoutineSetRoutine
import com.gradation.lift.ui.mapper.toText
import com.gradation.lift.ui.state.rememberDragDropListState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ChangeOrderScreen(
    modifier: Modifier,
    currentRoutineSetRoutine: RoutineSetRoutine,
    currentRoutineSetRoutineState: CurrentRoutineSetRoutineState,
    navigateChangeOrderToRoutineSetInUpdateRoutineGraph: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    var overScrollJob by remember { mutableStateOf<Job?>(null) }
    val dragDropListState =
        rememberDragDropListState(onMove = currentRoutineSetRoutineState.moveRoutine)


    Scaffold(
        topBar = {
            LiftTopBar(
                title = "순서 변경",
                backgroundColor = LiftTheme.colorScheme.no5,
                onClick = navigateChangeOrderToRoutineSetInUpdateRoutineGraph,
            )
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
                itemsIndexed(currentRoutineSetRoutine.routine) { index, routine ->
                    val offsetOrNull = dragDropListState.elementDisplacement.takeIf {
                        index == dragDropListState.currentIndexOfDraggedItem
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
                                LiftText(
                                    textStyle = LiftTextStyle.No3,
                                    text = routine.workCategory.name,
                                    color = LiftTheme.colorScheme.no9,
                                    textAlign = TextAlign.Left
                                )
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
                                                            .visibleItemsInfo[index - dragDropListState.lazyListState.firstVisibleItemIndex]
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
                                    modifier = modifier.padding(horizontal = LiftTheme.space.space16),
                                    horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space24),
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
                                routine.workSetList.forEachIndexed { index, workSet ->
                                    LiftPrimaryContainer(
                                        modifier = modifier,
                                        horizontalPadding = LiftTheme.space.space16,
                                        verticalPadding = LiftTheme.space.space8,
                                        shape = RoundedCornerShape(size = LiftTheme.space.space8)
                                    ) {
                                        Row(
                                            modifier = modifier,
                                            horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space24),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            LiftText(
                                                modifier = modifier.weight(1f),
                                                textStyle = LiftTextStyle.No3,
                                                text = "${index + 1}",
                                                color = LiftTheme.colorScheme.no2,
                                                textAlign = TextAlign.Center
                                            )
                                            LiftEmptyContainer(
                                                modifier = modifier.weight(1f),
                                                verticalPadding = LiftTheme.space.space6,
                                                shape = RoundedCornerShape(size = LiftTheme.space.space8)
                                            ) {
                                                LiftText(
                                                    modifier = modifier.align(Alignment.Center),
                                                    textStyle = LiftTextStyle.No3,
                                                    text = workSet.weight.toText(),
                                                    color = LiftTheme.colorScheme.no10,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                            LiftEmptyContainer(
                                                modifier = modifier.weight(1f),
                                                verticalPadding = LiftTheme.space.space6,
                                                shape = RoundedCornerShape(size = LiftTheme.space.space8)
                                            ) {
                                                LiftText(
                                                    modifier = modifier.align(Alignment.Center),
                                                    textStyle = LiftTextStyle.No3,
                                                    text = "${workSet.repetition}",
                                                    color = LiftTheme.colorScheme.no10,
                                                    textAlign = TextAlign.Center
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
            LiftDefaultContainer(
                modifier = modifier
                    .background(LiftTheme.colorScheme.no5)
                    .fillMaxWidth()
                  ,
                shape = RectangleShape,
                verticalPadding = LiftTheme.space.space10,
                horizontalPadding = LiftTheme.space.space20
            ) {
                LiftSolidButton(
                    modifier = modifier,
                    text = "확인",
                    onClick = navigateChangeOrderToRoutineSetInUpdateRoutineGraph
                )
            }
        }
    }
}