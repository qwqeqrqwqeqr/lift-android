package com.gradation.lift.feature.work.complete.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import com.gradation.lift.feature.work.work.data.model.WorkRestTime
import com.gradation.lift.ui.mapper.toText

@Composable
fun TimeView(
    modifier: Modifier=Modifier,
    historyWorkRestTime: WorkRestTime,
){
    Row(
        modifier.padding(horizontal =  16.dp),
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
                text = historyWorkRestTime.totalTime.toText(),
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
                text = historyWorkRestTime.restTime.toText(),
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no9
            )
        }
    }
}