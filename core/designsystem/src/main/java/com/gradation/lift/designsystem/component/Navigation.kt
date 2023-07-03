package com.gradation.lift.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun RowScope.LiftNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    unSelectedIcon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else unSelectedIcon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = LiftTheme.colorScheme.no4,
            unselectedIconColor = LiftTheme.colorScheme.no5,
            selectedTextColor = LiftTheme.colorScheme.no4,
            unselectedTextColor = LiftTheme.colorScheme.no5,
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
        modifier = modifier,
        tonalElevation = 0.dp,
        content = content,
        containerColor = LiftTheme.colorScheme.no5
    )
}

