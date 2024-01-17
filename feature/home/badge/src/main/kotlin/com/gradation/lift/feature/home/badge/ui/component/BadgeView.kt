package com.gradation.lift.feature.home.badge.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.resource.LiftImage
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.badge.data.state.BadgeScreenState
import com.gradation.lift.model.model.badge.Badge

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BadgeView(
    modifier: Modifier = Modifier,
    badge: Badge,
    badgeScreenState: BadgeScreenState,
) {
    BoxWithConstraints(contentAlignment = Alignment.Center) {
        Image(
            modifier = modifier.fillMaxWidth().scale(0.8f),
            painter = painterResource(LiftImage.Halo),
            contentDescription = "halo",
        )
        GlideImage(
            model = badge.url,
            modifier = modifier.size(LiftTheme.space.space100),
            contentDescription = "badge"
        )
        listOf(
            Triple(maxWidth / 3, 0.dp, 32.dp),
            Triple(maxWidth / 8, maxHeight / 10, 24.dp),
            Triple(-maxWidth / 3, 0.dp, 16.dp),
            Triple(-maxWidth / 4, maxHeight / 8, 24.dp),
            Triple(maxWidth / 4, -maxHeight / 8, 16.dp)
        ).zip(badgeScreenState.twinkleAnimationList).forEach {
            Icon(
                modifier = modifier
                    .size(it.first.third)
                    .offset(x = it.first.first, y = it.first.second),
                painter = painterResource(id = LiftIcon.Glitter),
                contentDescription = "",
                tint = LiftTheme.colorScheme.no20.copy(alpha = it.second.value)
            )
        }
    }
}