package com.gradation.lift.feature.badge.badge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.tab.LiftDoubleTab
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.model.FilterType
import com.gradation.lift.feature.badge.badge.data.model.SortType
import com.gradation.lift.feature.badge.badge.data.model.UserBadge
import com.gradation.lift.feature.badge.badge.data.state.BadgeScreenState
import com.gradation.lift.feature.badge.badge.ui.component.BadgeStoreContentView

@Composable
fun BadgeScreen(
    modifier: Modifier,
    sortType: SortType,
    filterType: FilterType,
    userBadgeList: List<UserBadge>,
    currentTotalBadgeCount: Int,
    acquiredBadgeCount: Int,
    unacquiredBadgeCount: Int,
    badgeScreenState: BadgeScreenState,
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
                        userBadgeList,
                        currentTotalBadgeCount,
                        acquiredBadgeCount,
                        unacquiredBadgeCount,
                        badgeScreenState
                    )
                else {

                }
            }
        }
    }
}
