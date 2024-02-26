package com.gradation.lift.designsystem.component.card.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftDefaultBadgeSmallIcon(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(LiftTheme.space.space48)
            .background(LiftTheme.colorScheme.no1, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        LiftIconBox(
            modifier = modifier,
            icon = LiftIcon.BadgeDisabled,
            iconType = IconType.Vector,
            iconBoxSize = IconBoxSize.Size24
        )
    }
}

@Composable
fun LiftDefaultBadgeLargeIcon(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(LiftTheme.space.space116)
            .background(LiftTheme.colorScheme.no5, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        LiftIconBox(
            modifier = modifier,
            icon = LiftIcon.BadgeDisabled,
            iconType = IconType.Vector,
            iconBoxSize = IconBoxSize.Size44
        )
    }
}