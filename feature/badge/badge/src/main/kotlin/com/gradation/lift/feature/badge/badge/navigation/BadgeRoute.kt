package com.gradation.lift.feature.badge.badge.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.badge.badge.data.BadgeViewModel
import com.gradation.lift.feature.badge.badge.data.model.BadgeState
import com.gradation.lift.feature.badge.badge.data.model.FilterType
import com.gradation.lift.feature.badge.badge.data.model.SortType
import com.gradation.lift.feature.badge.badge.data.state.BadgeAnimationState
import com.gradation.lift.feature.badge.badge.data.state.BadgeCaseSnackbarState
import com.gradation.lift.feature.badge.badge.data.state.BadgeCaseState
import com.gradation.lift.feature.badge.badge.data.state.BadgeScreenState
import com.gradation.lift.feature.badge.badge.data.state.BadgeStoreState
import com.gradation.lift.feature.badge.badge.data.state.BadgeUiState
import com.gradation.lift.feature.badge.badge.data.state.rememberBadgeAnimationState
import com.gradation.lift.feature.badge.badge.data.state.rememberBadgeScreenState
import com.gradation.lift.feature.badge.badge.ui.BadgeScreen
import com.gradation.lift.feature.badge.badge.ui.bottomSheet.FilterBottomSheetView
import com.gradation.lift.feature.badge.badge.ui.bottomSheet.SortBottomSheetView
import com.gradation.lift.feature.badge.badge.ui.dialog.BadgeDetailDialog
import com.gradation.lift.feature.badge.badge.ui.dialog.UpdateCancelDialog
import com.gradation.lift.model.model.badge.UserBadge
import com.gradation.lift.ui.extensions.showImmediatelySnackbar
import kotlinx.coroutines.launch

@Composable
fun BadgeBadgeRoute(
    modifier: Modifier = Modifier,
    navigateBadgeGraphToHomeGraph: () -> Unit,
    viewModel: BadgeViewModel = hiltViewModel(),
    badgeStoreState: BadgeStoreState = viewModel.badgeStoreState,
    badgeCaseState: BadgeCaseState = viewModel.badgeCaseState,
    badgeScreenState: BadgeScreenState = rememberBadgeScreenState(initialPage = viewModel.initialPage),
    badgeAnimationState: BadgeAnimationState = rememberBadgeAnimationState(),
) {
    val badgeUiState: BadgeUiState by viewModel.badgeUiState.collectAsStateWithLifecycle()

    val sortType: SortType by badgeStoreState.sortType.collectAsStateWithLifecycle()
    val filterType: FilterType by badgeStoreState.filterType.collectAsStateWithLifecycle()
    val badgeStateList: List<BadgeState> by badgeStoreState.badgeStateList.collectAsStateWithLifecycle()
    val currentTotalBadgeCount: Int by badgeStoreState.currentTotalBadgeCount.collectAsStateWithLifecycle()
    val acquiredBadgeCount: Int by badgeStoreState.acquiredBadgeCount.collectAsStateWithLifecycle()
    val unacquiredBadgeCount: Int by badgeStoreState.unacquiredBadgeCount.collectAsStateWithLifecycle()


    val badgeCaseSnackbarState: BadgeCaseSnackbarState by badgeCaseState.badgeCaseSnackbarState.collectAsStateWithLifecycle()
    val searchText: String by badgeCaseState.searchText.collectAsStateWithLifecycle()
    val filteredUserBadgeList: List<UserBadge> by badgeCaseState.filteredUserBadgeList.collectAsStateWithLifecycle()
    val mainFlagBadgeSet: Set<UserBadge> by badgeCaseState.mainFlagBadgeSet.collectAsStateWithLifecycle()
    val mainFlagBadgeChangeListIsEmpty: Boolean by badgeCaseState.mainFlagBadgeChangeListIsEmpty.collectAsStateWithLifecycle()
    val updateBadgeCaseSnackbarState: (BadgeCaseSnackbarState) -> Unit =
        badgeCaseState.updateBadgeCaseSnackbarState



    AnimatedVisibility(visible = badgeScreenState.filterBottomSheetView.value) {
        FilterBottomSheetView(modifier, filterType, badgeStoreState, badgeScreenState)
    }
    AnimatedVisibility(visible = badgeScreenState.sortBottomSheetView.value) {
        SortBottomSheetView(modifier, sortType, badgeStoreState, badgeScreenState)
    }

    AnimatedVisibility(visible = badgeScreenState.badgeDetailDialogView.value.first) {
        badgeScreenState.badgeDetailDialogView.value.second?.let { userBadge ->
            BadgeDetailDialog(modifier, userBadge, badgeScreenState)
        }
    }
    AnimatedVisibility(visible = badgeScreenState.updateCancelDialog.value) {
        UpdateCancelDialog(modifier, navigateBadgeGraphToHomeGraph, badgeScreenState)
    }

    BackHandler(
        onBack = {
            if (mainFlagBadgeChangeListIsEmpty)
                navigateBadgeGraphToHomeGraph()
            else
                badgeScreenState.updateUpdateCancelDialog(true)
        }
    )

    LaunchedEffect(badgeUiState) {
        if (badgeUiState is BadgeUiState.Fail) {
            badgeScreenState.appCoroutineScope.launch {
                badgeScreenState.appSnackbarHostState.showImmediatelySnackbar(
                    (badgeUiState as BadgeUiState.Fail).message
                )
            }
        }
    }

    LaunchedEffect(badgeCaseSnackbarState) {
        when (val result = badgeCaseSnackbarState) {
            is BadgeCaseSnackbarState.Fail -> {
                badgeScreenState.appSnackbarHostState.showImmediatelySnackbar(
                    result.message
                )
                updateBadgeCaseSnackbarState(BadgeCaseSnackbarState.None)
            }

            is BadgeCaseSnackbarState.FillMaxed -> {
                badgeScreenState.snackbarHostState.showImmediatelySnackbar(
                    result.message
                )
                updateBadgeCaseSnackbarState(BadgeCaseSnackbarState.None)
            }

            BadgeCaseSnackbarState.None -> {}
            is BadgeCaseSnackbarState.UpdateCompleted -> {
                badgeScreenState.snackbarHostState.showImmediatelySnackbar(
                    result.message
                )
                updateBadgeCaseSnackbarState(BadgeCaseSnackbarState.None)
            }
        }
    }



    BadgeScreen(
        modifier,
        sortType,
        filterType,
        badgeStateList,
        currentTotalBadgeCount,
        acquiredBadgeCount,
        unacquiredBadgeCount,
        searchText,
        filteredUserBadgeList,
        mainFlagBadgeSet,
        mainFlagBadgeChangeListIsEmpty,
        badgeScreenState,
        badgeCaseState,
        badgeAnimationState,
        navigateBadgeGraphToHomeGraph
    )


}
