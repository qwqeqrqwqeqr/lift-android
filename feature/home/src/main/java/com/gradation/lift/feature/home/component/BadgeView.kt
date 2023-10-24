package com.gradation.lift.feature.home.component

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.data.state.BadgeUiState


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BadgeView(modifier: Modifier = Modifier, badgeUiState: BadgeUiState) {
    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(LiftIcon.Badge),
                    contentDescription = "badge",
                    tint = Color.Unspecified,
                )
                Text(
                    text = "내 뱃지",
                    color = LiftTheme.colorScheme.no9,
                    style = LiftTheme.typography.no1
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {

                Text(
                    text = "전체보기",
                    color = LiftTheme.colorScheme.no2,
                    style = LiftTheme.typography.no6
                )
                Icon(
                    modifier = modifier
                        .width(8.dp)
                        .height(8.dp),
                    painter = painterResource(LiftIcon.ChevronRightSharp),
                    contentDescription = "badgeAll",
                    tint = LiftTheme.colorScheme.no2,
                )
            }
        }

        Column {
            when (badgeUiState) {
                is BadgeUiState.Fail -> {
                    Spacer(modifier = modifier
                        .fillMaxWidth()
                        .height(96.dp))
                }

                BadgeUiState.Loading -> {
                    Spacer(modifier = modifier
                        .fillMaxWidth()
                        .height(96.dp)
                        .background(SkeletonBrush(), RoundedCornerShape(12.dp)))
                }

                is BadgeUiState.Success -> {
                    val badgeBackgroundColor = LiftTheme.colorScheme.no33
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .drawBehind {
                                drawPath(
                                    path = Path().apply {
                                        moveTo(
                                            0f,
                                            size.height
                                        )
                                        lineTo(
                                            size.width,
                                            size.height
                                        )
                                        lineTo(size.width - size.width / 30, size.height / 2)
                                        lineTo(size.width / 30, size.height / 2)
                                        close()
                                    }, style = Fill, color = badgeBackgroundColor
                                )
                            }
                    ) {
                        Row(
                            modifier = modifier,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            badgeUiState.userBadge.forEach {
                                GlideImage(
                                    modifier = modifier
                                        .size(52.dp)
                                        .offset(y = (-6).dp),
                                    model = it.badge.url,
                                    contentDescription = "Badge"
                                )
                            }

                            if (badgeUiState.userBadge.size < 5) {
                                Box(
                                    modifier = modifier
                                        .size(52.dp)
                                        .offset(y = (-6).dp)
                                        .background(
                                            LiftTheme.colorScheme.no5,
                                            CircleShape
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        modifier = modifier.size(22.dp),
                                        painter = painterResource(id = LiftIcon.Plus),
                                        contentDescription = "addBadge",
                                        tint = LiftTheme.colorScheme.no32
                                    )
                                }
                            }
                            repeat(4 - badgeUiState.userBadge.size) {
                                Spacer(modifier = modifier.size(52.dp))
                            }
                        }
                    }
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .background(
                                LiftTheme.colorScheme.no5,
                                RoundedCornerShape(0.dp, 0.dp, 12.dp, 12.dp)
                            )
                            .padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        badgeUiState.userBadge.forEach {
                            Box(
                                modifier = modifier.width(52.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = it.badge.name,
                                    style = LiftTheme.typography.no7.copy(fontWeight = FontWeight.Bold),
                                    color = Color(android.graphics.Color.parseColor(it.badge.color))
                                )
                            }
                        }
                        repeat(5 - badgeUiState.userBadge.size) {
                            Spacer(modifier = modifier.width(52.dp))
                        }
                    }
                }
            }
        }
    }
}