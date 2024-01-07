package com.gradation.lift.feature.home.badge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.zIndex
import com.gradation.lift.designsystem.resource.LiftImage.BadgeCongrats
import com.gradation.lift.designsystem.resource.LiftImage.BadgeHalo
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.badge.data.state.BadgeScreenState
import com.gradation.lift.feature.home.badge.ui.component.BadgeView
import com.gradation.lift.feature.home.badge.ui.component.ContentView
import com.gradation.lift.feature.home.badge.ui.component.HeaderView
import com.gradation.lift.model.model.badge.Badge


@Composable
fun BadgeScreen(
    modifier: Modifier = Modifier,
    badge: Badge,
    createUserBadge: (Int) -> Unit,
    badgeScreenState: BadgeScreenState,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LiftTheme.colorScheme.no30)
            .padding(horizontal = LiftTheme.space.space42)
    ) {
        Image(
            modifier = modifier
                .fillMaxSize()
                .scale(badgeScreenState.haloTranslateAnimation)
                .zIndex(1f),
            painter = painterResource(id = BadgeHalo),
            contentDescription = "badgeHalo"
        )
        Column(
            modifier
                .clip(RoundedCornerShape(LiftTheme.space.space24))
                .background(
                    color = LiftTheme.colorScheme.no5,
                    shape = RoundedCornerShape(LiftTheme.space.space24)
                )
                .align(Alignment.Center)
                .padding(top = LiftTheme.space.space20, bottom = LiftTheme.space.space20)
                .zIndex(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
        ) {
            HeaderView(modifier, badge, createUserBadge)
            BadgeView(modifier, badge, badgeScreenState)
            ContentView(modifier, badge, createUserBadge)
        }
        Image(
            modifier = modifier
                .fillMaxSize()
                .zIndex(3f),
            painter = painterResource(id = BadgeCongrats),
            contentDescription = "badgeCongrats"
        )
    }
}


