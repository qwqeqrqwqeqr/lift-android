package com.gradation.lift.feature.home.component.profile_view.badge_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.brush.SkeletonBrush
import com.gradation.lift.designsystem.component.LiftOutlineButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.data.state.BadgeUiState


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
internal fun BadgeView(
    modifier: Modifier = Modifier,
    badgeUiState: BadgeUiState,
    navigateHomeGraphToNewBadgeGraph: () -> Unit,
    navigateHomeGraphToBadgeSettingRouter: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        when (badgeUiState) {
            is BadgeUiState.Fail -> {
                Spacer(modifier = modifier.height(64.dp))
            }

            BadgeUiState.Loading -> {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Box(
                        modifier = modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(SkeletonBrush())
                    )
                    Box(
                        modifier = modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(SkeletonBrush())
                    )
                    Box(
                        modifier = modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(SkeletonBrush())
                    )
                    Box(
                        modifier = modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(SkeletonBrush())
                    )
                    Box(
                        modifier = modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(SkeletonBrush())
                    )
                }
            }

            is BadgeUiState.Success -> {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    items(badgeUiState.userBadge) {
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            GlideImage(
                                modifier = modifier.size(56.dp),
                                model = it.badge.url,
                                contentDescription = "badgeImage"
                            )
                            Text(
                                text = it.badge.name,
                                style = LiftTheme.typography.no4,
                                color = LiftTheme.colorScheme.no9,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    item {
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Box(
                                modifier = modifier
                                    .background(
                                        color = LiftTheme.colorScheme.no1,
                                        shape = RoundedCornerShape(96.dp)
                                    )
                                    .size(56.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                IconButton(onClick = navigateHomeGraphToBadgeSettingRouter) {
                                    Icon(
                                        painter = painterResource(LiftIcon.Plus),
                                        contentDescription = "",
                                        tint = LiftTheme.colorScheme.no6,
                                        modifier = modifier.size(18.dp)
                                    )
                                }
                            }
                            Text(
                                text = "뱃지추가",
                                style = LiftTheme.typography.no4,
                                color = LiftTheme.colorScheme.no9,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                }
            }
        }

        Spacer(modifier = modifier.padding(8.dp))

        LiftOutlineButton(
            modifier = modifier
                .height(32.dp)
                .align(Alignment.CenterHorizontally),
            contentPadding = PaddingValues(
                start = 15.dp, top = 0.dp, end = 15.dp, bottom = 0.dp
            ),
            shape = RoundedCornerShape(6.dp),
            onClick = navigateHomeGraphToNewBadgeGraph,
        ) {
            Text(
                text = "전체보기",
                style = LiftTheme.typography.no5,
                color = LiftTheme.colorScheme.no4,
            )
            Spacer(modifier = modifier.padding(2.dp))
            Icon(
                painterResource(id = LiftIcon.ChevronRight),
                contentDescription = null,
            )
        }
    }
}

