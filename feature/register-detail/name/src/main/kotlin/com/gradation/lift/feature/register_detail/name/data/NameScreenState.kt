package com.gradation.lift.feature.register_detail.name.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun rememberNameScreenState(
    focusManager: FocusManager = LocalFocusManager.current,
): NameScreenState =
    remember(focusManager) {
        NameScreenState(focusManager)
    }


data class NameScreenState(
    val focusManager: FocusManager
) {

}

