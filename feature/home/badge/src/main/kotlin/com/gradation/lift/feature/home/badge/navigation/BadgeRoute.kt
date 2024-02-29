package com.gradation.lift.feature.home.badge.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.home.badge.data.state.BadgeScreenState
import com.gradation.lift.feature.home.badge.data.state.CreateUserBadgeState
import com.gradation.lift.feature.home.badge.data.state.rememberBadgeScreenState
import com.gradation.lift.feature.home.badge.data.viewModel.BadgeViewModel
import com.gradation.lift.feature.home.badge.ui.BadgeScreen
import com.gradation.lift.feature.home.common.data.BadgeConditionState
import com.gradation.lift.feature.home.common.data.HomeSharedViewModel
import com.gradation.lift.navigation.Route
import kotlinx.datetime.LocalDateTime

@Composable
fun BadgeRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateBadgeToHomeInHomeGraph: () -> Unit,
    viewModel: BadgeViewModel = hiltViewModel(),
    @SuppressLint("UnrememberedGetBackStackEntry") sharedViewModel: HomeSharedViewModel = hiltViewModel(
        remember {
            navController.getBackStackEntry(
                Route.HOME_GRAPH_NAME
            )
        }),
    badgeScreenState: BadgeScreenState = rememberBadgeScreenState(),
) {
    val badgeConditionState: BadgeConditionState by sharedViewModel.badgeConditionState.collectAsStateWithLifecycle()
    val createUserBadgeState: CreateUserBadgeState by viewModel.createUserBadgeState.collectAsStateWithLifecycle()
    val today: LocalDateTime = viewModel.today
    val createUserBadge: (Int) -> Unit = viewModel.createUserBadge()

    when (createUserBadgeState) {
        is CreateUserBadgeState.Fail -> {}
        CreateUserBadgeState.None -> {}
        CreateUserBadgeState.Success -> {
            LaunchedEffect(true) {
                navigateBadgeToHomeInHomeGraph()
            }
        }
    }

    when (val state = badgeConditionState) {
        BadgeConditionState.None -> {}
        is BadgeConditionState.Success -> {
            BadgeScreen(
                modifier,
                state.badge,
                today,
                createUserBadge,
                badgeScreenState
            )
        }
    }
    BackHandler(onBack = {}, enabled = false)
}