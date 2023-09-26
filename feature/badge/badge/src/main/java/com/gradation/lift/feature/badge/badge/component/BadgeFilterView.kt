package com.gradation.lift.feature.badge.badge.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.state.BadgeState

@Composable
fun BadgeFilterView(
    modifier: Modifier = Modifier,
    badgeState: BadgeState
) {
    Surface(
        color = LiftTheme.colorScheme.no17,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("총 ")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight(700)
                        ),
                    ) {
                        append("${badgeState.currentBadgeCount}개")
                    }
                    append("의 뱃지")
                },
                style = LiftTheme.typography.no6,
                color = LiftTheme.colorScheme.no9,
            )
            Icon(
                modifier = modifier
                    .width(16.dp)
                    .height(18.dp),
                painter = painterResource(id = LiftIcon.Filter),
                contentDescription = "filter",
                tint = Color.Unspecified
            )
        }
    }
}