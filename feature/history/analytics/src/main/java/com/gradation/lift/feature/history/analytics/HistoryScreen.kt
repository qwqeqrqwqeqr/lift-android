package com.gradation.lift.feature.history.analytics

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HistoryRoute(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    HistoryScreen(
        modifier = modifier
    )
}


@Composable
internal fun HistoryScreen(
    modifier: Modifier = Modifier,
) {

}