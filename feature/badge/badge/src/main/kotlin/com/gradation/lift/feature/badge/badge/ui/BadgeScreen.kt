package com.gradation.lift.feature.badge.badge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.bottomBar.LiftDefaultBottomBar
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.card.badge.LiftBadgeSmallCard
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.tab.LiftDoubleTab
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.model.BadgeState
import com.gradation.lift.feature.badge.badge.data.model.FilterType
import com.gradation.lift.feature.badge.badge.data.model.SortType
import com.gradation.lift.feature.badge.badge.data.state.BadgeCaseState
import com.gradation.lift.feature.badge.badge.data.state.BadgeScreenState
import com.gradation.lift.feature.badge.badge.ui.component.badgeCase.BadgeCaseView
import com.gradation.lift.feature.badge.badge.ui.component.badgeStore.BadgeStoreContentView
import com.gradation.lift.model.model.badge.UserBadge
import com.gradation.lift.ui.modifier.noRippleClickable

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BadgeScreen(
    modifier: Modifier,
    sortType: SortType,
    filterType: FilterType,
    badgeStateList: List<BadgeState>,
    currentTotalBadgeCount: Int,
    acquiredBadgeCount: Int,
    unacquiredBadgeCount: Int,
    userBadgeList: List<UserBadge>,
    mainFlagUserBadgeList: List<UserBadge>,
    mainFlagBadgeChangeListIsEmpty: Boolean,
    badgeScreenState: BadgeScreenState,
    badgeCaseState: BadgeCaseState,
    navigateBadgeGraphToHomeGraph: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = "내 뱃지",
                onClick = navigateBadgeGraphToHomeGraph
            )
        },
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarHostState = badgeScreenState.snackbarHostState
            )
        },
        bottomBar = {
            if (badgeScreenState.selectedPage.value == 1) {
                LiftDefaultBottomBar {
                    LiftSolidButton(
                        text = "적용하기",
                        enabled = !mainFlagBadgeChangeListIsEmpty,
                        onClick = badgeCaseState.updateUserBadgeMainFlag
                    )
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no17)
                .padding(padding)
        ) {
            item {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(LiftTheme.colorScheme.no5)
                        .padding(
                            top = LiftTheme.space.space16,
                            start = LiftTheme.space.space20,
                            end = LiftTheme.space.space20,
                            bottom = LiftTheme.space.space28
                        )
                ) {
                    LiftDoubleTab(
                        valueList = listOf("뱃지보관함", "뱃지케이스"),
                        selectedIndex = badgeScreenState.selectedPage.value,
                        updateSelected = badgeScreenState.updateSelectedPage
                    )
                }
            }
            item {
                if (badgeScreenState.selectedPage.value == 0)
                    BadgeStoreContentView(
                        modifier,
                        sortType,
                        filterType,
                        badgeStateList,
                        currentTotalBadgeCount,
                        acquiredBadgeCount,
                        unacquiredBadgeCount,
                        badgeScreenState
                    )
                else {
                    Column(
                        modifier = modifier
                            .background(LiftTheme.colorScheme.no5)
                            .padding(horizontal = LiftTheme.space.space20),
                        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space44)
                    ) {
                        BadgeCaseView(modifier, badgeCaseState, mainFlagUserBadgeList)
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
                            LiftTheme.space.space8,
                        ),
                        horizontalArrangement = Arrangement.spacedBy(
                            LiftTheme.space.space4,
                        )
                    ) {
                        userBadgeList.forEach {
                            LiftBadgeSmallCard(
                                modifier.noRippleClickable {
                                    if (mainFlagUserBadgeList.contains(it))
                                        badgeCaseState.removeBadge(it.badge.id)
                                    else
                                        badgeCaseState.appendBadge(it.badge.id)
                                },
                                isDefault = false,
                                badgeName = it.badge.name,
                                badgeUrl = it.badge.url,
                                selected = mainFlagUserBadgeList.contains(it)
                            )
                        }
                    }
                }
            }
        }
    }
}

