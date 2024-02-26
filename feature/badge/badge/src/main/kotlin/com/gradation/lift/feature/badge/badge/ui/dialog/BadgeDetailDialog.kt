package com.gradation.lift.feature.badge.badge.ui.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.card.badge.LiftBadgeLargeCard
import com.gradation.lift.designsystem.component.card.badge.LiftDefaultBadgeLargeCard
import com.gradation.lift.designsystem.component.dialog.LiftDialog
import com.gradation.lift.feature.badge.badge.data.model.UserBadge
import com.gradation.lift.feature.badge.badge.data.state.BadgeScreenState

@Composable
internal fun BadgeDetailDialog(
    modifier: Modifier = Modifier,
    userBadge: UserBadge,
    badgeStoreScreenState: BadgeScreenState,
) {
    LiftDialog(onDismissRequest = { badgeStoreScreenState.updateBadgeDetailDialogView(false to null) }) {
        when (userBadge) {
            is UserBadge.AcquireBadge -> LiftBadgeLargeCard(
                modifier,
                userBadge.name,
                userBadge.description,
                userBadge.url,
                userBadge.color,
                userBadge.backgroundColor,
                userBadge.badgeTimeStamp
            ) { badgeStoreScreenState.updateBadgeDetailDialogView(false to null) }

            is UserBadge.UnacquiredBadge -> LiftDefaultBadgeLargeCard(
                modifier,
                userBadge.name,
                userBadge.hint
            ) { badgeStoreScreenState.updateBadgeDetailDialogView(false to null) }
        }

    }
}

