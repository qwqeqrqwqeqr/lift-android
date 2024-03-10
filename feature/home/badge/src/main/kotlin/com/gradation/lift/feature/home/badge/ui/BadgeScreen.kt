package com.gradation.lift.feature.home.badge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import com.gradation.lift.designsystem.component.card.badge.LiftBadgeLargeCard
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftImage.BadgeHalo
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.badge.data.state.BadgeScreenState
import com.gradation.lift.model.model.badge.Badge
import com.gradation.lift.ui.modifier.noRippleClickable
import kotlinx.datetime.LocalDateTime


@Composable
fun BadgeScreen(
    modifier: Modifier = Modifier,
    badge: Badge,
    today: LocalDateTime,
    createUserBadge: (Int) -> Unit,
    badgeScreenState: BadgeScreenState,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LiftTheme.colorScheme.no30)
            .noRippleClickable { createUserBadge(badge.id) }
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
            modifier = modifier
                .zIndex(2f)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)
        ) {
            LiftText(
                textStyle = LiftTextStyle.No1,
                text = "뱃지를 획득했어요",
                color = LiftTheme.colorScheme.no5,
                textAlign = TextAlign.Center
            )

            LiftBadgeLargeCard(
                modifier = modifier,
                badgeName = badge.name,
                description = badge.description ?: "",
                badgeUrl = badge.url,
                color = Color(android.graphics.Color.parseColor(badge.color)),
                backgroundColor = Color(android.graphics.Color.parseColor(badge.backgroundColor)),
                badgeTimeStamp = today
            ) { createUserBadge(badge.id) }
        }
    }
}


