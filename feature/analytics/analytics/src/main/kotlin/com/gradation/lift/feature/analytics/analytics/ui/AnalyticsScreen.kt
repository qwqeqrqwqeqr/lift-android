package com.gradation.lift.feature.analytics.analytics.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
internal fun AnalyticsScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(LiftTheme.colorScheme.no17)
            .fillMaxSize()
            .padding(top = 16.dp, bottom = LiftTheme.space.space72)
    ) {
    }
}

@Composable
fun HistoryAnalyticsScreenPreview() {
    LiftMaterialTheme {
        AnalyticsScreen(

        )
    }
}
