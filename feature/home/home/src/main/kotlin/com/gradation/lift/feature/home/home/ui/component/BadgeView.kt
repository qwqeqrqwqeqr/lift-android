package com.gradation.lift.feature.home.home.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.ui.modifier.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.home.data.state.BadgeUiState
import com.gradation.lift.feature.home.home.data.state.HomeAnimationState


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BadgeView(
    modifier: Modifier = Modifier,
    badgeUiState: BadgeUiState,
    navigateHomeGraphToBadgeGraph: () -> Unit,
    navigateHomeGraphToBadgeSettingRouter: () -> Unit,
    homeAnimationState: HomeAnimationState,
) {
    Column(verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16)) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
            ) {
                Icon(
                    painter = painterResource(LiftIcon.Badge),
                    contentDescription = "badge",
                    tint = Color.Unspecified,
                )
                LiftText(
                    textStyle = LiftTextStyle.No1,
                    text = "내 뱃지",
                    color = LiftTheme.colorScheme.no3,
                    textAlign = TextAlign.Start
                )
            }

            Row(
                modifier = modifier.noRippleClickable { navigateHomeGraphToBadgeGraph() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space2)
            ) {

                LiftText(
                    textStyle = LiftTextStyle.No6,
                    text = "전체보기",
                    color = LiftTheme.colorScheme.no2,
                    textAlign = TextAlign.Start
                )
                Icon(
                    modifier = modifier
                        .size(LiftTheme.space.space8),
                    painter = painterResource(LiftIcon.ChevronRightSharp),
                    contentDescription = "selectAllBadge",
                    tint = LiftTheme.colorScheme.no2,
                )
            }
        }

        Column {
            when (badgeUiState) {
                is BadgeUiState.Fail -> Spacer(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(LiftTheme.space.space96)
                )

                BadgeUiState.Loading ->
                    Spacer(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(LiftTheme.space.space96)
                            .background(
                                SkeletonBrush(),
                                RoundedCornerShape(LiftTheme.space.space12)
                            )
                    )


                is BadgeUiState.Success -> {
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
                                            homeAnimationState.backgroundBadgeEffectColor,
                                            homeAnimationState.backgroundBadgeEffectColor
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
                            badgeUiState.userBadge.forEach {
                                GlideImage(
                                    modifier = modifier
                                        .size(LiftTheme.space.space52)
                                        .weight(1f),
                                    model = it.badge.url,
                                    contentDescription = "${it.badge.url}Badge"
                                )
                            }

                            if (badgeUiState.userBadge.size < 5) {
                                Box(
                                    modifier = modifier
                                        .size(LiftTheme.space.space52)
                                        .padding(horizontal = LiftTheme.space.space8)
                                        .background(LiftTheme.colorScheme.no5, CircleShape)
                                        .noRippleClickable { navigateHomeGraphToBadgeSettingRouter() }
                                        .weight(1f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        modifier = modifier.size(LiftTheme.space.space24),
                                        painter = painterResource(id = LiftIcon.Plus),
                                        contentDescription = "addBadge",
                                        tint = LiftTheme.colorScheme.no32
                                    )
                                }
                            }
                            repeat(4 - badgeUiState.userBadge.size) {
                                Spacer(
                                    modifier = modifier
                                        .size(LiftTheme.space.space52)
                                        .weight(1f)
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
                        badgeUiState.userBadge.forEach {
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
                        repeat(5 - badgeUiState.userBadge.size) {
                            Spacer(
                                modifier = modifier
                                    .size(LiftTheme.space.space52)
                                    .weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}