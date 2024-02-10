package com.gradation.lift.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import com.gradation.lift.designsystem.component.navigation.LiftNavigationItem
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    destinations: List<TopLevelNavDestination>,
    onNavigateToDestination: (TopLevelNavDestination) -> Unit,
    currentDestination: NavDestination?,
) {
    Row(
        modifier
            .height(LiftTheme.space.space60)

            .fillMaxWidth()
            .shadow(
                elevation = LiftTheme.space.space6,
                spotColor = LiftTheme.colorScheme.no11,
                ambientColor = LiftTheme.colorScheme.no11,
                shape = RoundedCornerShape(
                    topStart = LiftTheme.space.space14,
                    topEnd = LiftTheme.space.space14
                )
            )
            .background(
                color = LiftTheme.colorScheme.no5,
                shape = RoundedCornerShape(
                    topStart = LiftTheme.space.space14,
                    topEnd = LiftTheme.space.space14
                )
            )
            .padding(horizontal = LiftTheme.space.space24),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        destinations.forEach { destination ->
            val interactionSource: MutableInteractionSource =
                remember { MutableInteractionSource() }
            val isSelected: Boolean = currentDestination?.route == destination.route
            val contentColor: Color by animateColorAsState(
                if (isSelected) LiftTheme.colorScheme.no4
                else LiftTheme.colorScheme.no13,
                label = "contentColor"
            )
            LiftNavigationItem(
                modifier = modifier.weight(1f),
                interactionSource = interactionSource,
                onClick = { onNavigateToDestination(destination) },
                icon = if (isSelected) destination.selectedIcon else destination.unSelectedIcon,
                text = destination.displayName,
                color = contentColor
            )
        }
    }
}