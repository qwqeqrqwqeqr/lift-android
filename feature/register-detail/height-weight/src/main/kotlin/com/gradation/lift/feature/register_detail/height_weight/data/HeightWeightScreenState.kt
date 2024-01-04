package com.gradation.lift.feature.register_detail.height_weight.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun rememberHeightWeightScreenState(
    focusManager: FocusManager = LocalFocusManager.current,
): HeightWeightScreenState =
    remember(focusManager) {
        HeightWeightScreenState(focusManager)
    }


data class HeightWeightScreenState(
    val focusManager: FocusManager
) {

}

