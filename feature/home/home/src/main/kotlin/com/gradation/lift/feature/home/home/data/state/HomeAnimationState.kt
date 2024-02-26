package com.gradation.lift.feature.home.home.data.state

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

@Composable
fun rememberHomeAnimationState(): HomeAnimationState {

    val backgroundBadgeEffectTransition =
        rememberInfiniteTransition(label = "backgroundBadgeEffectTransition")
    val backgroundBadgeEffectColor: Color by backgroundBadgeEffectTransition.animateColor(
        initialValue = Color.Transparent,
        targetValue = Color(0x55CFD6E3),
        animationSpec = infiniteRepeatable(
            animation = tween(4000), repeatMode = RepeatMode.Reverse
        ), label = "backgroundBadgeEffectColor"
    )
    return remember(backgroundBadgeEffectColor) {
        HomeAnimationState(backgroundBadgeEffectColor)
    }
}


class HomeAnimationState(val backgroundBadgeEffectColor: Color)