package com.gradation.lift.feature.badge.badge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.bottomBar.LiftDefaultBottomBar
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.snackbar.SnackBarCategory
import com.gradation.lift.designsystem.component.tab.LiftDoubleTab
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.model.BadgeState
import com.gradation.lift.feature.badge.badge.data.model.FilterType
import com.gradation.lift.feature.badge.badge.data.model.SortType
import com.gradation.lift.feature.badge.badge.data.state.BadgeAnimationState
import com.gradation.lift.feature.badge.badge.data.state.BadgeCaseState
import com.gradation.lift.feature.badge.badge.data.state.BadgeScreenState
import com.gradation.lift.feature.badge.badge.ui.component.badgeCase.BadgeCaseContentView
import com.gradation.lift.feature.badge.badge.ui.component.badgeStore.BadgeStoreContentView
import com.gradation.lift.model.model.badge.UserBadge
import com.gradation.lift.ui.extensions.focusClearManager
import com.gradation.lift.ui.state.keyboardOpenAsState

@Composable
fun BadgeScreen(
    modifier: Modifier,
    sortType: SortType,
    filterType: FilterType,
    badgeStateList: List<BadgeState>,
    currentTotalBadgeCount: Int,
    acquiredBadgeCount: Int,
    unacquiredBadgeCount: Int,
    searchText: String,
    filteredUserBadgeList: List<UserBadge>,
    mainFlagBadgeSet: Set<UserBadge>,
    mainFlagBadgeChangeListIsEmpty: Boolean,
    badgeScreenState: BadgeScreenState,
    badgeCaseState: BadgeCaseState,
    badgeAnimationState: BadgeAnimationState,
    navigateBadgeGraphToHomeGraph: () -> Unit,
) {
    val keyboardOpenAsState: Boolean by keyboardOpenAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = "내 뱃지",
                onClick = {
                    if (mainFlagBadgeChangeListIsEmpty)
                        navigateBadgeGraphToHomeGraph()
                    else
                        badgeScreenState.updateUpdateCancelDialog(true)
                }
            )
        },
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarHostState = badgeScreenState.snackbarHostState,
                snackbarCategory = SnackBarCategory.Info
            )
        },
        bottomBar = {
            if (badgeScreenState.selectedPage.value == 1 && !keyboardOpenAsState) {
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
                .focusClearManager(badgeScreenState.focusManager)
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
                    BadgeCaseContentView(
                        modifier,
                        searchText,
                        filteredUserBadgeList,
                        mainFlagBadgeSet,
                        badgeScreenState,
                        badgeCaseState,
                        badgeAnimationState,
                        keyboardOpenAsState
                    )
                }
            }
        }
    }
}

