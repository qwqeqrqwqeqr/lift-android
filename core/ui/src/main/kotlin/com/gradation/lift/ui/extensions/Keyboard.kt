package com.gradation.lift.ui.extensions

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput

fun Modifier.focusClearManager(
    focusManager: FocusManager,
    onClear: (() -> Unit)? = null,
): Modifier =
    this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            focusManager.clearFocus()
            onClear?.invoke()
        })
    }
