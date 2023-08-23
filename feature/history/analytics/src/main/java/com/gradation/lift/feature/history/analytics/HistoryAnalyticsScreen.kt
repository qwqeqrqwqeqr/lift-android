package com.gradation.lift.feature.history.analytics

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun HistoryAnalyticsRoute(
    modifier: Modifier = Modifier,
    viewModel: HistoryAnalyticsViewModel = hiltViewModel(),
) {
    HistoryAnalyticsScreen(
        modifier = modifier
    )
}


@Composable
internal fun HistoryAnalyticsScreen(
    modifier: Modifier = Modifier,
) {
    Surface(color = LiftTheme.colorScheme.no5, modifier = modifier.fillMaxSize()) {

    }
}