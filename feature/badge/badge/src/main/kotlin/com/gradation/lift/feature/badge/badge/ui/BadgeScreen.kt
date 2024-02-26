package com.gradation.lift.feature.badge.badge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.data.model.FilterType
import com.gradation.lift.feature.badge.badge.data.model.SortType
import com.gradation.lift.feature.badge.badge.data.model.UserBadge
import com.gradation.lift.feature.badge.badge.data.state.BadgeScreenState
import com.gradation.lift.feature.badge.badge.data.state.BadgeStoreState
import com.gradation.lift.feature.badge.badge.data.state.BadgeUiState

@Composable
fun BadgeScreen(
    modifier: Modifier,
    badgeUiState: BadgeUiState,
    sortType: SortType,
    filterType: FilterType,
    userBadgeList: List<UserBadge>,
    currentTotalBadgeCount: Int,
    acquiredBadgeCount: Int,
    unacquiredBadgeCount: Int,
    badgeStoreState: BadgeStoreState,
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
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no17)
                .padding(padding)
        ) {

        }

    }
}
