package com.gradation.lift.feature.home.badge.data.state

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.gradation.lift.feature.home.badge.data.model.TwinkleAnimation

@Composable
fun rememberBadgeScreenState(
    infiniteTransition: InfiniteTransition = rememberInfiniteTransition(label = "infiniteTransition"),
): BadgeScreenState {

    val haloTranslateAnimation: Float by infiniteTransition.animateFloat(
        initialValue = 1.2f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000), repeatMode = RepeatMode.Reverse
        ), label = "haloTranslateAnimation"
    )

    val twinkleAnimationList: List<State<Float>> = listOf(
        TwinkleAnimation(0.5f, 1f, 1000),
        TwinkleAnimation(1f, 0.2f, 2000),
        TwinkleAnimation(1f, 0.3f, 1000),
        TwinkleAnimation(0.8f, 1f, 1500),
        TwinkleAnimation(1f, 0.4f, 1000)
    ).map {
        infiniteTransition.animateFloat(
            initialValue = it.initialValue,
            targetValue = it.targetValue,
            animationSpec = infiniteRepeatable(
                animation = tween(it.duration), repeatMode = RepeatMode.Reverse
            ), label = "${it.targetValue}/${it.initialValue}twinkleTransitionAnimation"
        )
    }

    return remember(infiniteTransition, haloTranslateAnimation, twinkleAnimationList) {
        BadgeScreenState(infiniteTransition, haloTranslateAnimation, twinkleAnimationList)
    }


}


data class BadgeScreenState(
    val infiniteTransition: InfiniteTransition,
    val haloTranslateAnimation: Float,
    val twinkleAnimationList: List<State<Float>>,
) {

}

