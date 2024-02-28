package com.gradation.lift.feature.badge.badge.ui.component.badgeCase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.zIndex
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.component.icon.IconBoxSize
import com.gradation.lift.designsystem.component.icon.IconType
import com.gradation.lift.designsystem.component.icon.LiftIconBox
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.state.BadgeAnimationState
import com.gradation.lift.feature.badge.badge.data.state.BadgeCaseState
import com.gradation.lift.model.model.badge.UserBadge
import com.gradation.lift.ui.modifier.noRippleClickable

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BadgeCaseView(
    modifier: Modifier = Modifier,
    badgeCaseState: BadgeCaseState,
    mainFlagBadgeSet: Set<UserBadge>,
    badgeAnimationState: BadgeAnimationState,
) {
    Column(
        modifier = modifier
    ) {
        val badgeBackgroundColor = LiftTheme.colorScheme.no33

        Column(
            modifier = modifier
                .fillMaxWidth()

                .height(LiftTheme.space.space72)
                .drawBehind {
                    drawPath(
                        path = Path().apply {
                            moveTo(0f, size.height)
                            lineTo(size.width, size.height)
                            lineTo(
                                size.width - size.width / 50,
                                size.height - size.center.y / 2
                            )
                            lineTo(size.width / 50, size.height - size.center.y / 2)
                            close()
                        }, style = Fill, color = badgeBackgroundColor
                    )
                    drawPath(
                        path = Path().apply {
                            moveTo(size.width / 50, 0f)
                            lineTo(size.width - size.width / 50, 0f)
                            lineTo(
                                size.width - size.width / 50,
                                size.height - size.center.y / 2
                            )
                            lineTo(size.width / 50, size.height - size.center.y / 2)
                            close()
                        }, style = Fill,
                        brush = Brush.linearGradient(
                            start = Offset(y = 0f, x = center.x),
                            end = Offset(y = size.height, x = center.x),
                            colors = listOf(
                                Color.Transparent,
                                badgeAnimationState.backgroundBadgeEffectColor,
                                badgeAnimationState.backgroundBadgeEffectColor,
                            )
                        )
                    )
                },
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = modifier.offset(y = -LiftTheme.space.space8),
                verticalAlignment = Alignment.Bottom
            ) {
                mainFlagBadgeSet.forEach {
                    Box(modifier = modifier.weight(1f)) {
                        GlideImage(
                            modifier = modifier
                                .size(LiftTheme.space.space52)
                                .align(Alignment.Center),
                            model = it.badge.url,
                            contentDescription = "${it.badge.url}Badge"
                        )
                        LiftIconBox(
                            modifier = modifier
                                .align(Alignment.TopEnd)
                                .offset(x = -LiftTheme.space.space2)
                                .zIndex(1f)
                                .noRippleClickable {
                                    badgeCaseState.removeBadge(it)
                                },
                            icon = LiftIcon.Cancel,
                            iconType = IconType.Vector,
                            iconBoxSize = IconBoxSize.Size20
                        )
                    }
                }

                repeat(5 - mainFlagBadgeSet.size) {
                    LiftIconBox(
                        modifier = modifier.weight(1f),
                        icon = LiftIcon.BadgeCircle,
                        iconType = IconType.Vector,
                        iconBoxSize = IconBoxSize.Size52
                    )
                }
            }
        }


        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(LiftTheme.space.space32)
                .background(
                    LiftTheme.colorScheme.no5,
                    RoundedCornerShape(
                        LiftTheme.space.space0,
                        LiftTheme.space.space0,
                        LiftTheme.space.space12,
                        LiftTheme.space.space12
                    )
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            mainFlagBadgeSet.forEach {
                Box(
                    modifier = modifier
                        .size(LiftTheme.space.space52)
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No8,
                        text = it.badge.name,
                        color = Color(android.graphics.Color.parseColor(it.badge.color)),
                        textAlign = TextAlign.Center
                    )

                }
            }
            repeat(5 - mainFlagBadgeSet.size) {
                Box(
                    modifier = modifier
                        .size(LiftTheme.space.space52)
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    LiftText(
                        textStyle = LiftTextStyle.No8,
                        text = "미선택",
                        color = LiftTheme.colorScheme.no2,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}