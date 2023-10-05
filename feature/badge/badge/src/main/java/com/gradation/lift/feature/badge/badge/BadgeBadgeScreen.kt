package com.gradation.lift.feature.badge.badge

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftIconButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.badge.component.bottom_sheet.FilterBottomSheet
import com.gradation.lift.feature.badge.badge.component.bottom_sheet.SortBottomSheet
import com.gradation.lift.feature.badge.badge.component.dialog.BadgeDialog
import com.gradation.lift.feature.badge.badge.component.dialog.LockBadgeDialog
import com.gradation.lift.feature.badge.badge.component.success.BadgeInfoView
import com.gradation.lift.feature.badge.badge.component.success.BadgeStoreView
import com.gradation.lift.feature.badge.badge.component.fail.FailBadgeInfoView
import com.gradation.lift.feature.badge.badge.component.fail.FailBadgeStoreView
import com.gradation.lift.feature.badge.badge.component.loading.LoadingBadgeInfoView
import com.gradation.lift.feature.badge.badge.component.loading.LoadingBadgeStoreView
import com.gradation.lift.feature.badge.badge.data.BadgeBadgeViewModel
import com.gradation.lift.feature.badge.badge.data.model.AllBadge
import com.gradation.lift.feature.badge.badge.data.model.FilterType
import com.gradation.lift.feature.badge.badge.data.model.SortType
import com.gradation.lift.feature.badge.badge.data.state.BadgeUiState

@Composable
fun BadgeBadgeRoute(
    modifier: Modifier = Modifier,
    navigateBadgeGraphToPreGraph: () -> Unit,
    navigateBadgeToSettingInBadgeGraph: () -> Unit,
    viewModel: BadgeBadgeViewModel = hiltViewModel(),
) {

    val sortType: SortType by viewModel.sortType.collectAsStateWithLifecycle()
    val filterType: FilterType by viewModel.filterType.collectAsStateWithLifecycle()
    val onVisibleFilterBottomSheet: Boolean by viewModel.onVisibleFilterBottomSheet.collectAsStateWithLifecycle()
    val onVisibleSortBottomSheet: Boolean by viewModel.onVisibleSortBottomSheet.collectAsStateWithLifecycle()
    val onVisibleBadgeDialog: Boolean by viewModel.onVisibleBadgeDialog.collectAsStateWithLifecycle()
    val selectedBadge: AllBadge by viewModel.selectedBadge.collectAsStateWithLifecycle()
    val badgeUiState: BadgeUiState by viewModel.badgeUiState.collectAsStateWithLifecycle()

    val updateSortType: (SortType) -> Unit = viewModel.updateSortType
    val updateFilterType: (FilterType) -> Unit = viewModel.updateFilterType
    val updateSelectedBadge: (AllBadge) -> Unit = viewModel.updateSelectedBadge
    val updateVisibleFilterBottomSheet: (Boolean) -> Unit = viewModel.updateVisibleFilterBottomSheet
    val updateVisibleSortBottomSheet: (Boolean) -> Unit = viewModel.updateVisibleSortBottomSheet
    val updateVisibleBadgeDialog: (Boolean) -> Unit = viewModel.updateVisibleBadgeDialog

    BadgeBadgeScreen(
        modifier,
        sortType,
        filterType,
        onVisibleFilterBottomSheet,
        onVisibleSortBottomSheet,
        onVisibleBadgeDialog,
        selectedBadge,
        badgeUiState,
        updateSortType,
        updateFilterType,
        updateSelectedBadge,
        updateVisibleFilterBottomSheet,
        updateVisibleSortBottomSheet,
        updateVisibleBadgeDialog,
        navigateBadgeGraphToPreGraph,
        navigateBadgeToSettingInBadgeGraph,
    )

    BackHandler { navigateBadgeGraphToPreGraph() }

}

@Composable
fun BadgeBadgeScreen(
    modifier: Modifier = Modifier,
    sortType: SortType,
    filterType: FilterType,
    onVisibleFilterBottomSheet: Boolean,
    onVisibleSortBottomSheet: Boolean,
    onVisibleBadgeDialog: Boolean,
    selectedBadge: AllBadge,
    badgeUiState: BadgeUiState,
    updateSortType: (SortType) -> Unit,
    updateFilterType: (FilterType) -> Unit,
    updateSelectedBadge: (AllBadge) -> Unit,
    updateVisibleFilterBottomSheet: (Boolean) -> Unit,
    updateVisibleSortBottomSheet: (Boolean) -> Unit,
    updateVisibleBadgeDialog: (Boolean) -> Unit,
    navigateBadgeGraphToPreGraph: () -> Unit,
    navigateBadgeToSettingInBadgeGraph: () -> Unit,
) {
    if (onVisibleBadgeDialog) {
        Surface(
            color = LiftTheme.colorScheme.no5.copy(alpha = 0.7f),
            modifier = modifier.fillMaxSize()
        ) {
            when (selectedBadge) {
                is AllBadge.AcquireBadge -> BadgeDialog(
                    modifier = modifier,
                    badge = selectedBadge,
                    updateVisibleBadgeDialog = updateVisibleBadgeDialog
                )

                is AllBadge.UnacquiredBadge -> LockBadgeDialog(
                    modifier = modifier,
                    badge = selectedBadge,
                    updateVisibleBadgeDialog = updateVisibleBadgeDialog
                )
            }
        }
    }
    if (onVisibleFilterBottomSheet) {
        FilterBottomSheet(
            modifier, filterType, updateFilterType, updateVisibleFilterBottomSheet
        )
    } else if (onVisibleSortBottomSheet) {
        SortBottomSheet(
            modifier, sortType, updateSortType, updateVisibleSortBottomSheet
        )
    }

    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "내 뱃지",
                onBackClickTopBar = navigateBadgeGraphToPreGraph,
                actions = {
                    LiftIconButton(
                        onClick = navigateBadgeToSettingInBadgeGraph,
                        modifier = modifier
                    ) {
                        Icon(
                            painter = painterResource(LiftIcon.BadgeAdd),
                            contentDescription = "",
                            tint = Color.Unspecified,
                        )
                    }
                }
            )
        },
    ) { padding ->

        Surface(
            modifier = modifier
                .padding(padding)
                .fillMaxSize(),
            color = LiftTheme.colorScheme.no17,
        ) {
            when (badgeUiState) {
                is BadgeUiState.Fail -> {
                    Column(
                        modifier = modifier,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        FailBadgeInfoView(modifier)
                        FailBadgeStoreView(modifier)
                    }
                }

                BadgeUiState.Loading -> {
                    Column(
                        modifier = modifier,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        LoadingBadgeInfoView(modifier)
                        LoadingBadgeStoreView(modifier)
                    }
                }

                is BadgeUiState.Success -> {
                    Column(
                        modifier = modifier,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        BadgeInfoView(modifier, badgeUiState.badgeState)
                        BadgeStoreView(
                            modifier,
                            badgeUiState.badgeState,
                            sortType,
                            filterType,
                            updateSelectedBadge,
                            updateVisibleBadgeDialog,
                            updateVisibleFilterBottomSheet,
                            updateVisibleSortBottomSheet
                        )
                    }
                }

            }
        }
    }
}
