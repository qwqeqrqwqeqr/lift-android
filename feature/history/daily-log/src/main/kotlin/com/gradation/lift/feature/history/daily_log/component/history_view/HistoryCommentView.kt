package com.gradation.lift.feature.history.daily_log.component.history_view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.history.History

@Composable
fun HistoryCommentView(
    modifier: Modifier = Modifier,
    selectedHistory: History,
){
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "한 줄 메모",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no9,
            modifier = modifier.align(Alignment.Start)
        )

        Spacer(modifier = modifier.padding(4.dp))
        Column(
            modifier = modifier
                .border(
                    width = 1.5.dp,
                    color = LiftTheme.colorScheme.no8,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .background(
                    color = LiftTheme.colorScheme.no1,
                    shape = RoundedCornerShape(size = 12.dp)
                )
                .height(48.dp)
                .padding(horizontal = 14.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = selectedHistory.comment ?: "",
                color = LiftTheme.colorScheme.no9,
                style = LiftTheme.typography.no6
            )
        }
    }
}