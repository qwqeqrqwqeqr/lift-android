package com.gradation.lift.feature.badge.badge.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.badge.badge.data.BadgeViewModel
import com.gradation.lift.feature.badge.badge.data.model.FilterType
import com.gradation.lift.feature.badge.badge.data.model.SortType
import com.gradation.lift.feature.badge.badge.data.model.UserBadge
import com.gradation.lift.feature.badge.badge.data.state.BadgeScreenState
import com.gradation.lift.feature.badge.badge.data.state.BadgeStoreState
import com.gradation.lift.feature.badge.badge.data.state.BadgeUiState
import com.gradation.lift.feature.badge.badge.data.state.rememberBadgeScreenState
import com.gradation.lift.feature.badge.badge.ui.BadgeScreen
import com.gradation.lift.feature.badge.badge.ui.bottomSheet.FilterBottomSheetView
import com.gradation.lift.feature.badge.badge.ui.bottomSheet.SortBottomSheetView
import com.gradation.lift.feature.badge.badge.ui.dialog.BadgeDetailDialog

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BadgeBadgeRoute(
    modifier: Modifier = Modifier,
    initialPage: Int,
    navigateBadgeGraphToHomeGraph: () -> Unit,
    viewModel: BadgeViewModel = hiltViewModel(),
    badgeStoreState: BadgeStoreState = viewModel.badgeStoreState,
    badgeStoreScreenState: BadgeScreenState = rememberBadgeScreenState(initialPage = initialPage),
) {
    val badgeUiState: BadgeUiState by viewModel.badgeUiState.collectAsStateWithLifecycle()

    val sortType: SortType by badgeStoreState.sortType.collectAsStateWithLifecycle()
    val filterType: FilterType by badgeStoreState.filterType.collectAsStateWithLifecycle()
    val userBadgeList: List<UserBadge> by badgeStoreState.userBadgeList.collectAsStateWithLifecycle()
    val currentTotalBadgeCount: Int by badgeStoreState.currentTotalBadgeCount.collectAsStateWithLifecycle()
    val acquiredBadgeCount: Int by badgeStoreState.acquiredBadgeCount.collectAsStateWithLifecycle()
    val unacquiredBadgeCount: Int by badgeStoreState.unacquiredBadgeCount.collectAsStateWithLifecycle()



    AnimatedVisibility(visible = badgeStoreScreenState.filterBottomSheetView.value) {
        FilterBottomSheetView(modifier, filterType, badgeStoreState, badgeStoreScreenState)
    }
    AnimatedVisibility(visible = badgeStoreScreenState.sortBottomSheetView.value) {
        SortBottomSheetView(modifier, sortType, badgeStoreState, badgeStoreScreenState)
    }

    AnimatedVisibility(visible = badgeStoreScreenState.badgeDetailDialogView.value.first) {
        badgeStoreScreenState.badgeDetailDialogView.value.second?.let { userBadge ->
            BadgeDetailDialog(modifier, userBadge, badgeStoreScreenState)
        }
    }


    BadgeScreen(
        modifier,
        sortType,
        filterType,
        userBadgeList,
        currentTotalBadgeCount,
        acquiredBadgeCount,
        unacquiredBadgeCount,
        badgeStoreScreenState,
        navigateBadgeGraphToHomeGraph
    )

    BackHandler { navigateBadgeGraphToHomeGraph() }

}