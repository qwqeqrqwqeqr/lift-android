package com.gradation.lift.ui.state

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun rememberDragDropListState(
    lazyListState: LazyListState = rememberLazyListState(),
    scope: CoroutineScope,
    hapticFeedback: HapticFeedback,
    onMove: (Int, Int) -> Unit,
): DragDropListState {
    return remember {
        DragDropListState(
            lazyListState = lazyListState,
            scope = scope,
            hapticFeedback = hapticFeedback,
            onMove = onMove
        )
    }
}

fun Modifier.dragAndDrop(dragDropState: DragDropListState): Modifier {
    return then(
        Modifier.pointerInput(
            Unit
        ) {
            detectDragGesturesAfterLongPress(
                onDrag = dragDropState::onDrag,
                onDragStart = dragDropState::onDragStart,
                onDragEnd = dragDropState::onDragInterrupted,
                onDragCancel = dragDropState::onDragInterrupted
            )
        }
    )
}

class DragDropListState(
    val lazyListState: LazyListState,
    val scope: CoroutineScope,
    val hapticFeedback: HapticFeedback,
    private val onMove: (Int, Int) -> Unit,
) {
    private var initiallyDraggedElement by mutableStateOf<LazyListItemInfo?>(null)

    private var draggedDistance by mutableFloatStateOf(0f)

    var currentIndexOfDraggedItem by mutableStateOf<Int?>(null)

    private var overScrollJob by mutableStateOf<Job?>(null)

    private val initialOffsets: Pair<Int, Int>?
        get() = initiallyDraggedElement?.let {
            Pair(it.offset, it.offsetEnd)
        }
    val elementDisplacement: Float?
        get() = currentIndexOfDraggedItem
            ?.let {
                lazyListState.getVisibleItemInfoFor(absoluteIndex = it)
            }
            ?.let { item ->
                (initiallyDraggedElement?.offset ?: 0f).toFloat() + draggedDistance - item.offset
            }

    private val currentElement: LazyListItemInfo?
        get() = currentIndexOfDraggedItem?.let {
            lazyListState.getVisibleItemInfoFor(absoluteIndex = it)
        }

    fun onDragStart(offset: Offset) {
        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
        lazyListState.layoutInfo.visibleItemsInfo
            .firstOrNull { item ->
                offset.y.toInt() in item.offset..(item.offset + item.size)
            }?.also {
                currentIndexOfDraggedItem = it.index
                initiallyDraggedElement = it
            }
    }

    fun startDrag(currentIndexOfDraggedItem: Int, initiallyDraggedElement: LazyListItemInfo) {
        this.currentIndexOfDraggedItem = currentIndexOfDraggedItem
        this.initiallyDraggedElement = initiallyDraggedElement
    }


    fun onDragInterrupted() {
        draggedDistance = 0f
        currentIndexOfDraggedItem = null
        initiallyDraggedElement = null
        overScrollJob?.cancel()
    }

    fun onDrag(change: PointerInputChange, offset: Offset) {
        change.consume()
        draggedDistance += offset.y

        initialOffsets?.let { (topOffset, bottomOffset) ->
            val startOffset = topOffset + draggedDistance
            val endOffset = bottomOffset + draggedDistance

            currentElement?.let { hovered ->
                lazyListState.layoutInfo.visibleItemsInfo
                    .filterNot { item ->
                        item.offsetEnd < startOffset || item.offset > endOffset || hovered.index == item.index
                    }
                    .firstOrNull { item ->
                        val delta = startOffset - hovered.offset
                        when {
                            delta > 0 -> (endOffset > item.offsetEnd)
                            else -> (startOffset < item.offset)
                        }
                    }?.also { item ->
                        currentIndexOfDraggedItem?.let { current ->
                            hapticFeedback.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onMove.invoke(current, item.index)

                        }
                        currentIndexOfDraggedItem = item.index


                    }
            }
        }
        if (overScrollJob?.isActive == true) {
            return
        }
        checkForOverScroll().takeIf { offSet -> offSet != 0f }
            ?.let {
                overScrollJob = scope.launch {
                    lazyListState.animateScrollBy(it * 2f, tween(easing = LinearOutSlowInEasing))

                }
            } ?: run { overScrollJob?.cancel() }
    }

    private fun checkForOverScroll(): Float {
        return initiallyDraggedElement?.let {
            val startOffset = it.offset + draggedDistance
            val endOffset = it.offsetEnd + draggedDistance

            when {
                draggedDistance > 0 -> (endOffset - lazyListState.layoutInfo.viewportEndOffset).takeIf { diff -> diff > 0 }
                draggedDistance < 0 -> (startOffset - lazyListState.layoutInfo.viewportStartOffset).takeIf { diff -> diff < 0 }
                else -> null
            }
        } ?: 0f
    }

    private val LazyListItemInfo.offsetEnd: Int
        get() = this.offset + this.size
}


fun LazyListState.getVisibleItemInfoFor(absoluteIndex: Int): LazyListItemInfo? {
    return this.layoutInfo.visibleItemsInfo.getOrNull(absoluteIndex - this.layoutInfo.visibleItemsInfo.first().index)
}