package com.gradation.lift.feature.badge.badge.ui.component.badgeCase

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.card.badge.LiftBadgeSmallCard
import com.gradation.lift.designsystem.component.textField.LiftSearchInputTextField
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.state.BadgeAnimationState
import com.gradation.lift.feature.badge.badge.data.state.BadgeCaseState
import com.gradation.lift.feature.badge.badge.data.state.BadgeScreenState
import com.gradation.lift.model.model.badge.UserBadge
import com.gradation.lift.ui.modifier.noRippleClickable

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BadgeCaseContentView(
    modifier: Modifier = Modifier,
    searchText: String,
    filteredUserBadgeList: List<UserBadge>,
    mainFlagBadgeSet: Set<UserBadge>,
    badgeScreenState: BadgeScreenState,
    badgeCaseState: BadgeCaseState,
    badgeAnimationState: BadgeAnimationState,
    keyboardOpenAsState: Boolean,
) {
    Column(
        modifier = modifier
            .background(LiftTheme.colorScheme.no5)
            .padding(
                start = LiftTheme.space.space20,
                end = LiftTheme.space.space20,
                bottom = LiftTheme.space.space12
            ),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space44)
    ) {
        AnimatedVisibility(visible = !keyboardOpenAsState) {
            BadgeCaseView(
                modifier,
                badgeCaseState,
                mainFlagBadgeSet,
                badgeAnimationState
            )
        }
        LiftSearchInputTextField(
            modifier = modifier,
            value = searchText,
            onValueChange = badgeCaseState.updateSearchText,
            placeHolderValue = "뱃지 이름 검색",
            keyboardActions = KeyboardActions(
                onSearch = {
                    badgeScreenState.focusManager.clearFocus()
                }
            )
        )
    }
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = LiftTheme.space.space20,
                start = LiftTheme.space.space20,
                end = LiftTheme.space.space20
            ),
        verticalArrangement = Arrangement.spacedBy(
            LiftTheme.space.space16,
        ),
        horizontalArrangement = Arrangement.spacedBy(
            LiftTheme.space.space16,
        )
    ) {
        filteredUserBadgeList.forEach {
            LiftBadgeSmallCard(
                modifier.noRippleClickable {
                    if (mainFlagBadgeSet.map { it.badge.id }.contains(it.badge.id))
                        badgeCaseState.removeBadge(it)
                    else
                        badgeCaseState.appendBadge(it)
                },
                isDefault = false,
                badgeName = it.badge.name,
                badgeUrl = it.badge.url,
                selected = mainFlagBadgeSet.map { badge -> badge.badge.id }
                    .contains(it.badge.id)
            )
        }
    }
}