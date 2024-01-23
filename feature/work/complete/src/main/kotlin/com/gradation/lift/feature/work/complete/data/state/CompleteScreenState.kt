package com.gradation.lift.feature.work.complete.data.state

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember


@Composable
internal fun rememberCompleteScreenState(
    scrollState: ScrollState = rememberScrollState(),
):CompleteScreenState = remember(scrollState) {
    CompleteScreenState(scrollState)
}
@Stable
internal class CompleteScreenState(
    val scrollState: ScrollState,
)

