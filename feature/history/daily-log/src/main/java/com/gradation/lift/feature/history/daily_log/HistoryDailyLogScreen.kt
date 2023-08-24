package com.gradation.lift.feature.history.daily_log

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun HistoryDailyLogRoute(
    modifier: Modifier =Modifier,
    viewModel: HistoryDailyLogViewModel = hiltViewModel(),
){

    HistoryDailyLogScreen(modifier)

}

@Composable
fun HistoryDailyLogScreen(modifier:Modifier=Modifier){
    Surface(color = LiftTheme.colorScheme.no5, modifier = modifier.fillMaxSize()) {

    }
}