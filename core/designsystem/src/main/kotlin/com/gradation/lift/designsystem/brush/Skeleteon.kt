package com.gradation.lift.designsystem.brush

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun SkeletonBrush(showShimmer: Boolean = true,targetValue:Float = 100f): Brush {
    return if (showShimmer) {
        val shimmerColors = listOf(
            LiftTheme.colorScheme.no7.copy(alpha = 0.6f),
            LiftTheme.colorScheme.no7.copy(alpha = 0.2f),
            LiftTheme.colorScheme.no7.copy(alpha = 0.6f),
        )

        val skeletonTransition = rememberInfiniteTransition(label = "skeletonTransition")
        val skeletonTranslateAnimation = skeletonTransition.animateFloat(
            initialValue = 0f,
            targetValue = targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(800), repeatMode = RepeatMode.Reverse
            ), label = ""
        )
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset.Zero,
            end = Offset(x = skeletonTranslateAnimation.value, y = skeletonTranslateAnimation.value)
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent,Color.Transparent),
            start = Offset.Zero,
            end = Offset.Zero
        )
    }
}
