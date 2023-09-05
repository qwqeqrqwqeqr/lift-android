package com.gradation.lift.feature.history.daily_log.component.history_view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.history.History
import com.gradation.lift.ui.utils.toText

@Composable
fun HistoryTimeView(
    modifier: Modifier = Modifier,
    selectedHistory: History,
){
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            modifier = modifier
                .background(LiftTheme.colorScheme.no5)
                .border(
                    width = 2.dp,
                    color = LiftTheme.colorScheme.no8,
                    shape = RoundedCornerShape(16.dp)
                )
                .weight(1f)
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically

            ) {
                Icon(
                    painter = painterResource(LiftIcon.Muscle),
                    contentDescription = "",
                    tint = LiftTheme.colorScheme.no20
                )
                Spacer(modifier = modifier.padding(1.dp))
                Text(
                    text = "총 소요시간",
                    style = LiftTheme.typography.no6,
                    color = LiftTheme.colorScheme.no11
                )
            }
            Text(
                text = selectedHistory.totalTime.toText(),
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no9
            )
        }
        Column(
            modifier = modifier
                .background(LiftTheme.colorScheme.no5)
                .border(
                    width = 2.dp,
                    color = LiftTheme.colorScheme.no8,
                    shape = RoundedCornerShape(16.dp)
                )
                .weight(1f)
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically

            ) {
                Icon(
                    painter = painterResource(LiftIcon.Timer),
                    contentDescription = "",
                    tint = LiftTheme.colorScheme.no4
                )
                Spacer(modifier = modifier.padding(1.dp))
                Text(
                    text = "총 휴식시간",
                    style = LiftTheme.typography.no6,
                    color = LiftTheme.colorScheme.no11
                )
            }
            Text(
                text = selectedHistory.restTime.toText(),
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no9
            )
        }
    }
}