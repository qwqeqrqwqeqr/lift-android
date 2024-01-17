package com.gradation.lift.feature.dailyLog.dailyLog.ui.component.history_view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.model.model.history.History

@Composable
fun HistoryScoreView(
    modifier: Modifier = Modifier,
    selectedHistory: History,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            8.dp,
            Alignment.CenterHorizontally
        ),
        modifier = modifier.fillMaxWidth(),
    ) {
        repeat(5) {
            Image(
                painter = if (it < selectedHistory.score) painterResource(R.drawable.star_on) else painterResource(
                    R.drawable.star_off
                ),
                contentDescription = "",
                modifier = modifier
                    .size(36.dp)
            )
        }
    }
}