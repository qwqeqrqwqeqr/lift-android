package com.gradation.lift.feature.badge.badge.component.success

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
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
import com.gradation.lift.ui.extensions.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.model.AllBadge
import com.gradation.lift.feature.badge.badge.data.state.BadgeState

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BadgeListView(
    modifier: Modifier,
    badgeState: BadgeState,
    updateSelectedBadge: (AllBadge) -> Unit,
    updateVisibleBadgeDialog: (Boolean) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier.padding(16.dp),
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(badgeState.badgeList) { badge ->
            when (badge) {
                is AllBadge.AcquireBadge -> {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(
                            2.dp,
                            Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        GlideImage(
                            modifier = modifier
                                .size(72.dp)
                                .noRippleClickable {
                                    updateSelectedBadge(badge)
                                    updateVisibleBadgeDialog(true)
                                },
                            model = badge.url,
                            contentDescription = "acquiredBadge"
                        )
                        Text(
                            text = badge.name,
                            color = LiftTheme.colorScheme.no9,
                            style = LiftTheme.typography.no4,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = badge.badgeTimeStamp.date.toString()
                                .replace("-", "."),
                            color = LiftTheme.colorScheme.no6,
                            style = LiftTheme.typography.no7,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                is AllBadge.UnacquiredBadge -> {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = modifier
                                .size(72.dp)
                                .clip(CircleShape)
                                .background(LiftTheme.colorScheme.no1)
                                .noRippleClickable {
                                    updateSelectedBadge(badge)
                                    updateVisibleBadgeDialog(true)
                                },

                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = LiftIcon.LockFilled),
                                contentDescription = "lock",
                                tint = Color.Unspecified
                            )
                        }
                        Text(
                            text = badge.name,
                            color = LiftTheme.colorScheme.no6,
                            style = LiftTheme.typography.no4,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }


        }
    }
}