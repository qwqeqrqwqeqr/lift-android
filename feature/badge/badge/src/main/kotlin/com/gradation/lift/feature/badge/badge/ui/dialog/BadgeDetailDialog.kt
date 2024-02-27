package com.gradation.lift.feature.badge.badge.ui.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.card.badge.LiftBadgeLargeCard
import com.gradation.lift.designsystem.component.card.badge.LiftDefaultBadgeLargeCard
import com.gradation.lift.designsystem.component.dialog.LiftDialog
import com.gradation.lift.feature.badge.badge.data.model.BadgeState
import com.gradation.lift.feature.badge.badge.data.state.BadgeScreenState

@Composable
internal fun BadgeDetailDialog(
    modifier: Modifier = Modifier,
    badgeState: BadgeState,
    badgeStoreScreenState: BadgeScreenState,
) {
    LiftDialog(onDismissRequest = { badgeStoreScreenState.updateBadgeDetailDialogView(false to null) }) {
        when (badgeState) {
            is BadgeState.AcquireBadge -> LiftBadgeLargeCard(
                modifier,
                badgeState.name,
                badgeState.description,
                badgeState.url,
                badgeState.color,
                badgeState.backgroundColor,
                badgeState.badgeTimeStamp
            ) { badgeStoreScreenState.updateBadgeDetailDialogView(false to null) }

            is BadgeState.UnacquiredBadge -> LiftDefaultBadgeLargeCard(
                modifier,
                badgeState.name,
                badgeState.hint
            ) { badgeStoreScreenState.updateBadgeDetailDialogView(false to null) }
        }

    }
}

