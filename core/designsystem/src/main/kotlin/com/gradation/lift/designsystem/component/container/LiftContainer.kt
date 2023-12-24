package com.gradation.lift.designsystem.component.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftDefaultContainer(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
    horizontalPadding: Dp = LiftTheme.space.paddingSpace,
    verticalPadding: Dp = LiftTheme.space.paddingSpace,
    shape: RoundedCornerShape = RoundedCornerShape(size = LiftTheme.space.space12),
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = LiftTheme.space.space4,
                spotColor = LiftTheme.colorScheme.no34,
                ambientColor = LiftTheme.colorScheme.no34,
                shape = shape
            )
            .background(
                color = LiftTheme.colorScheme.no5,
                shape = shape
            )
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        content = content
    )
}


@Composable
fun LiftPrimaryContainer(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
    horizontalPadding: Dp = LiftTheme.space.paddingSpace,
    verticalPadding: Dp = LiftTheme.space.paddingSpace,
    shape: RoundedCornerShape = RoundedCornerShape(size = LiftTheme.space.space12),

) {
    Box(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.no1,
                shape = shape
            )
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        content = content
    )
}

@Composable
fun LiftSecondaryContainer(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
    horizontalPadding: Dp = LiftTheme.space.paddingSpace,
    verticalPadding: Dp = LiftTheme.space.paddingSpace,
    shape: RoundedCornerShape = RoundedCornerShape(size = LiftTheme.space.space12),

) {
    Box(
        modifier = modifier
            .background(
                color = LiftTheme.colorScheme.no17,
                shape = shape
            )
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        content = content
    )
}


@Composable
fun LiftEmptyContainer(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
    horizontalPadding: Dp = LiftTheme.space.paddingSpace,
    verticalPadding: Dp = LiftTheme.space.paddingSpace,
    shape: RoundedCornerShape = RoundedCornerShape(size = LiftTheme.space.space12),
) {
    Box(
        modifier = modifier
            .clip(shape = shape)
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        content = content
    )
}