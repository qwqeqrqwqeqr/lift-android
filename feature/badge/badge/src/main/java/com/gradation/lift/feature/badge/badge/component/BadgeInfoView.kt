package com.gradation.lift.feature.badge.badge.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.canvas.LiftProgressBar
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.state.BadgeState


@Composable
fun BadgeInfoView(
    modifier:Modifier=Modifier,
    badgeState: BadgeState
){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                LiftTheme.colorScheme.no5,
                RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .padding(top=16.dp, start = 16.dp, end = 16.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight(800)),
                ) {
                    append("전체 뱃지 ")
                }
                append("(${badgeState.totalBadgeCount}개)")
            },
            color = LiftTheme.colorScheme.no9,
            style = LiftTheme.typography.no4
        )
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = modifier
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        color = LiftTheme.colorScheme.no8,
                        shape = RoundedCornerShape(size = 12.dp)
                    )
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = modifier
                            .width(12.dp)
                            .height(16.dp),
                        painter = painterResource(id = LiftIcon.Badge),
                        contentDescription = "getBadge",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    Text(
                        text = "획득 뱃지",
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no6
                    )
                }
                Text(
                    text = "${badgeState.acquiredBadgeCount}개", color = LiftTheme.colorScheme.no9,
                    style = LiftTheme.typography.no3
                )
            }
            Row(
                modifier = modifier
                    .weight(1f)
                    .border(
                        width = 1.dp,
                        color = LiftTheme.colorScheme.no8,
                        shape = RoundedCornerShape(size = 12.dp)
                    )
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = modifier
                            .width(12.dp)
                            .height(16.dp),
                        painter = painterResource(id = LiftIcon.BadgeDisabled),
                        contentDescription = "notGetBadge",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    Text(
                        text = "미획득 뱃지",
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no6
                    )
                }
                Text(
                    text = "${badgeState.unacquiredBadgeCount}개",
                    color = LiftTheme.colorScheme.no9,
                    style = LiftTheme.typography.no3
                )

            }
        }
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                modifier = modifier.padding(end = 8.dp),
                text = "획득률",
                color = LiftTheme.colorScheme.no9,
                style = LiftTheme.typography.no3
            )
            LiftProgressBar(
                modifier = modifier
                    .weight(1f),
                progress = badgeState.badgeProgress
            )
            Text(
                modifier = modifier.padding(start = 8.dp),
                text = "${badgeState.badgeProgress}%",
                color = LiftTheme.colorScheme.no9,
                style = LiftTheme.typography.no3
            )
        }
    }
}