package com.gradation.lift.feature.home.home.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.home.common.data.BadgeConditionState
import com.gradation.lift.feature.home.common.data.HomeSharedViewModel
import com.gradation.lift.feature.home.home.data.state.BadgeUiState
import com.gradation.lift.feature.home.home.data.state.HomeAnimationState
import com.gradation.lift.feature.home.home.data.state.HomeScreenState
import com.gradation.lift.feature.home.home.data.state.RoutineUiState
import com.gradation.lift.feature.home.home.data.state.UserDetailUiState
import com.gradation.lift.feature.home.home.data.state.rememberHomeAnimationState
import com.gradation.lift.feature.home.home.data.state.rememberHomeScreenState
import com.gradation.lift.feature.home.home.data.viewModel.HomeViewModel
import com.gradation.lift.feature.home.home.ui.HomeScreen
import com.gradation.lift.navigation.Route

@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
    navigateMainGraphToWorkGraph: () -> Unit,
    navigateHomeGraphToBadgeGraph: () -> Unit,
    navigateHomeToBadgeInHomeGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailRoutineRouter: (Int) -> Unit,
    navigateHomeGraphToBadgeSettingRouter: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    @SuppressLint("UnrememberedGetBackStackEntry") sharedViewModel: HomeSharedViewModel =
        hiltViewModel(remember { navController.getBackStackEntry(Route.HOME_GRAPH_NAME) }),
    homeScreenState: HomeScreenState = rememberHomeScreenState(),
    homeAnimationState: HomeAnimationState = rememberHomeAnimationState(),
) {

    val userDetailUiState: UserDetailUiState by viewModel.userDetailUiState.collectAsStateWithLifecycle()
    val badgeUiState: BadgeUiState by viewModel.badgeUiState.collectAsStateWithLifecycle()
    val routineUiState: RoutineUiState by viewModel.routineUiState.collectAsStateWithLifecycle()
    val badgeConditionState: BadgeConditionState by sharedViewModel.badgeConditionState.collectAsStateWithLifecycle()


    when (badgeConditionState) {
        is BadgeConditionState.Success -> LaunchedEffect(true) {
            navigateHomeToBadgeInHomeGraph()
        }

        BadgeConditionState.None -> {}
    }


    HomeScreen(
        modifier,
        userDetailUiState,
        badgeUiState,
        routineUiState,
        navigateMainGraphToCreateRoutineGraph,
        navigateMainGraphToWorkGraph,
        navigateHomeGraphToBadgeGraph,
        navigateHomeGraphToRoutineDetailGraph,
        navigateHomeGraphToRoutineDetailRoutineRouter,
        navigateHomeGraphToBadgeSettingRouter,
        homeScreenState,
        homeAnimationState
    )
}