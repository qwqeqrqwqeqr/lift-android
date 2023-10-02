package com.gradation.lift.feature.badge.setting.component.success

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.badge.UserBadge

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BadgeListView(
    modifier:Modifier=Modifier,
    badgeList: List<UserBadge>,
    mainBadgeSet: Set<UserBadge>,
    appendBadgeInMain: (UserBadge) -> Unit,
){
    Column(
        modifier = modifier
            .background(
                LiftTheme.colorScheme.no5,
                RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "획득한 뱃지",
            color = LiftTheme.colorScheme.no9,
            style = LiftTheme.typography.no3
        )
        LazyVerticalGrid(
            modifier = modifier.padding(16.dp),
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(badgeList) { badge ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(
                        2.dp,
                        Alignment.CenterVertically
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = modifier.size(72.dp)) {
                        GlideImage(
                            modifier = modifier
                                .size(72.dp)
                                .noRippleClickable {
                                    if (mainBadgeSet.count { it.badge.id == badge.badge.id } == 0) {
                                        appendBadgeInMain(badge)
                                    }
                                },
                            model = badge.badge.url,
                            contentDescription = "acquiredBadge"
                        )
                        if (mainBadgeSet.count { it.badge.id == badge.badge.id } == 1) {
                            Icon(
                                modifier = modifier
                                    .size(24.dp)
                                    .offset(y = (-24).dp, x = 24.dp)
                                    .align(Alignment.Center),
                                painter = painterResource(LiftIcon.CheckBoxChecked),
                                contentDescription = "",
                                tint = Color.Unspecified
                            )
                        }
                    }
                    Text(
                        text = badge.badge.name,
                        color = LiftTheme.colorScheme.no9,
                        style = LiftTheme.typography.no4,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}