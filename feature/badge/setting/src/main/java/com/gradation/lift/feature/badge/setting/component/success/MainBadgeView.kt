package com.gradation.lift.feature.badge.setting.component.success

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.badge.UserBadge

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MainBadgeView(
    modifier: Modifier = Modifier,
    mainBadgeSet: Set<UserBadge>,
    removeBadgeInMain: (UserBadge) -> Unit,
) {
    Column(
        modifier = modifier
            .background(
                LiftTheme.colorScheme.no5,
                RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
            )
            .padding(16.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            mainBadgeSet.forEach { badge ->
                Column(
                    modifier = modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = modifier.size(56.dp)) {
                        GlideImage(
                            modifier = modifier
                                .size(56.dp)
                                .align(Alignment.Center),
                            model = badge.badge.url,
                            contentDescription = "mainBadge"
                        )
                        IconButton(
                            modifier = modifier
                                .offset(y = (-26).dp, x = 26.dp)
                                .align(Alignment.Center),
                            onClick = { removeBadgeInMain(badge) }
                        ) {
                            Icon(
                                painter = painterResource(LiftIcon.Cancel),
                                contentDescription = "",
                                tint = Color.Unspecified,
                            )
                        }
                    }
                    Text(
                        text = badge.badge.name,
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no6,
                        textAlign = TextAlign.Center
                    )
                }
            }
            repeat(5 - mainBadgeSet.size) {
                Column(
                    modifier = modifier
                        .weight(1f)
                ) {
                    Spacer(
                        modifier = modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(LiftTheme.colorScheme.no1)
                    )
                }
            }
        }
    }
}