package com.gradation.lift.feature.badge.badge.component.success

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.model.AllBadge
import com.gradation.lift.feature.badge.badge.data.state.BadgeState


@Composable
fun BadgeStoreView(
    modifier: Modifier,
    badgeState: BadgeState,
    updateSelectedBadge: (AllBadge) -> Unit,
    updateVisibleBadgeDialog: (Boolean) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                LiftTheme.colorScheme.no5,
                RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
    ) {
        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = "뱃지보관함",
                color = LiftTheme.colorScheme.no9,
                style = LiftTheme.typography.no3
            )
        }
        BadgeFilterView(modifier, badgeState)
        BadgeListView(modifier, badgeState, updateSelectedBadge, updateVisibleBadgeDialog)
    }
}
