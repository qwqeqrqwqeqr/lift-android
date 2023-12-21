package com.gradation.lift.designsystem.component.navigation

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun RowScope.LiftNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    unSelectedIcon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    enabled: Boolean = true,
    label: String = "",
    alwaysShowLabel: Boolean = true,
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }

    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else unSelectedIcon,
        modifier = modifier,
        interactionSource = interactionSource,
        enabled = enabled,
        label = @Composable {
            LiftText(
                textStyle = LiftTextStyle.No8,
                text = label,
                color = if(selected) LiftTheme.colorScheme.no4 else  LiftTheme.colorScheme.no14,
                textAlign = TextAlign.Center
            )
        },
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = LiftTheme.colorScheme.no4,
            unselectedIconColor = LiftTheme.colorScheme.no14,
            selectedTextColor = LiftTheme.colorScheme.no4,
            unselectedTextColor = LiftTheme.colorScheme.no14,
            indicatorColor = LiftTheme.colorScheme.no5,
        ),
    )
}


@Composable
fun LiftNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    NavigationBar(
        modifier
            .height(LiftTheme.space.space60)
            .clip(
                RoundedCornerShape(
                    topStart = LiftTheme.space.space14,
                    topEnd = LiftTheme.space.space14
                )
            ),
        tonalElevation = 10.dp,
        content = content,
        containerColor = LiftTheme.colorScheme.no5
    )
}


