package com.gradation.lift.feature.badge.setting.component.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LoadingMainBadgeView(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(
                LiftTheme.colorScheme.no5,
                RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .padding(16.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            repeat(5) {
                Column(
                    modifier = modifier.weight(1f)

                ) {
                    Spacer(
                        modifier = modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(SkeletonBrush())
                    )
                }
            }
        }
    }
}