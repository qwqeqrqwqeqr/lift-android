package com.gradation.lift.feature.history.daily_log

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.history.daily_log.data.HistoryDailyLogViewModel

@Composable
fun HistoryDailyLogRoute(
    modifier: Modifier = Modifier,
    viewModel: HistoryDailyLogViewModel = hiltViewModel(),
) {


    val scrollState: ScrollState = rememberScrollState()


    HistoryDailyLogScreen(modifier, scrollState)

}

@Composable
fun HistoryDailyLogScreen(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
) {
    Surface(color = LiftTheme.colorScheme.no17, modifier = modifier.fillMaxSize()) {

        Column(
            modifier = modifier
                .padding(top = 16.dp)
                .verticalScroll(scrollState)
        ) {

        }
    }
}

@Composable
@Preview
fun HistoryDailyLogScreenPreview() {
    LiftMaterialTheme {
        HistoryDailyLogScreen(
            Modifier,
            rememberScrollState()
        )
    }
}