package com.gradation.lift.feature.badge.setting

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.badge.setting.component.success.ApplyView
import com.gradation.lift.feature.badge.setting.component.success.BadgeListView
import com.gradation.lift.feature.badge.setting.component.success.MainBadgeView
import com.gradation.lift.feature.badge.setting.data.BadgeSettingViewModel
import com.gradation.lift.feature.badge.setting.data.state.BadgeUiState
import com.gradation.lift.model.model.badge.UserBadge

@Composable
fun BadgeSettingRoute(
    modifier: Modifier = Modifier,
    navigateSettingToBadgeInBadgeGraph: () -> Unit,
    viewModel: BadgeSettingViewModel = hiltViewModel(),
) {

    val badgeUiState: BadgeUiState by viewModel.badgeUiState.collectAsStateWithLifecycle()

    val mainBadgeSet: Set<UserBadge> by viewModel.mainBadgeSet.collectAsStateWithLifecycle()
    val buttonCondition: Boolean by viewModel.buttonCondition.collectAsStateWithLifecycle()

    val appendBadgeInMain: (UserBadge) -> Unit = viewModel.appendBadgeInMain()
    val removeBadgeInMain: (UserBadge) -> Unit = viewModel.removeBadgeInMain()
    val updateUserBadgeMainFlag: () -> Unit = viewModel.updateUserBadgeMainFlag()

    BadgeSettingScreen(
        modifier,
        badgeUiState,
        mainBadgeSet,
        buttonCondition,
        appendBadgeInMain,
        removeBadgeInMain,
        updateUserBadgeMainFlag,
        navigateSettingToBadgeInBadgeGraph
    )

    BackHandler { navigateSettingToBadgeInBadgeGraph() }
}

@Composable
fun BadgeSettingScreen(
    modifier: Modifier = Modifier,
    badgeUiState: BadgeUiState,
    mainBadgeSet: Set<UserBadge>,
    buttonCondition: Boolean,
    appendBadgeInMain: (UserBadge) -> Unit,
    removeBadgeInMain: (UserBadge) -> Unit,
    updateUserBadgeMainFlag: () -> Unit,
    navigateSettingToBadgeInBadgeGraph: () -> Unit
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "대표뱃지 설정",
                onBackClickTopBar = navigateSettingToBadgeInBadgeGraph,
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

                }

                BadgeUiState.Loading -> {

                }

                is BadgeUiState.Success -> {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        MainBadgeView(modifier, mainBadgeSet, removeBadgeInMain)
                        BadgeListView(
                            modifier.weight(1f),
                            badgeUiState.badgeList,
                            mainBadgeSet,
                            appendBadgeInMain
                        )
                        ApplyView(modifier, buttonCondition, updateUserBadgeMainFlag)
                    }
                }
            }
        }
    }
}

