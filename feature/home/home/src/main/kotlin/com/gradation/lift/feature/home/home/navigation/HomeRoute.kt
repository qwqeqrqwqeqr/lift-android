package com.gradation.lift.feature.home.home.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
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
import com.gradation.lift.feature.home.home.data.state.WorkStampUiState
import com.gradation.lift.feature.home.home.data.state.rememberHomeAnimationState
import com.gradation.lift.feature.home.home.data.state.rememberHomeScreenState
import com.gradation.lift.feature.home.home.data.viewModel.HomeViewModel
import com.gradation.lift.feature.home.home.ui.HomeScreen
import com.gradation.lift.feature.home.home.ui.component.bottomSheet.WorkBottomSheet
import com.gradation.lift.navigation.Route
import com.gradation.lift.ui.extensions.showImmediatelySnackbar
import com.gradation.lift.ui.extensions.showToast

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
    navigateHomeGraphToBadgeBadgeRouter: (Int) -> Unit,
    navigateHomeToBadgeInHomeGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailRoutineRouter: (Int) -> Unit,
    navigateHomeGraphToWorkReadyRoutineSelectionRouter: () -> Unit,
    navigateHomeGraphToWorkReadyReadyRouter: () -> Unit,
    navigateHomeGraphToMyinfoProfileRouter: () -> Unit,
    navigateHomeGraphToInquiryGraph: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    @SuppressLint("UnrememberedGetBackStackEntry") sharedViewModel: HomeSharedViewModel =
        hiltViewModel(remember { navController.getBackStackEntry(Route.HOME_GRAPH_NAME) }),
    homeScreenState: HomeScreenState = rememberHomeScreenState(),
    homeAnimationState: HomeAnimationState = rememberHomeAnimationState(),
) {

    val userDetailUiState: UserDetailUiState by viewModel.userDetailUiState.collectAsStateWithLifecycle()
    val badgeUiState: BadgeUiState by viewModel.badgeUiState.collectAsStateWithLifecycle()
    val routineUiState: RoutineUiState by viewModel.routineUiState.collectAsStateWithLifecycle()
    val workStampUiState: WorkStampUiState by viewModel.workStampUiState.collectAsStateWithLifecycle()

    val badgeConditionState: BadgeConditionState by sharedViewModel.badgeConditionState.collectAsStateWithLifecycle()



    when (badgeConditionState) {
        is BadgeConditionState.Success -> LaunchedEffect(true) {
            navigateHomeToBadgeInHomeGraph()
        }

        BadgeConditionState.None -> {}
    }

    LaunchedEffect(badgeUiState) {
        if (badgeUiState is BadgeUiState.Fail) {
            homeScreenState.snackbarHostState.showImmediatelySnackbar(
                (badgeUiState as BadgeUiState.Fail).message
            )
        }
    }
    LaunchedEffect(routineUiState) {
        if (routineUiState is RoutineUiState.Fail) {
            homeScreenState.snackbarHostState.showImmediatelySnackbar(
                (routineUiState as RoutineUiState.Fail).message
            )
        }
    }



    BackHandler(onBack = {
        if (System.currentTimeMillis() - homeScreenState.terminateWaitTime.value >= 1500) {
            homeScreenState.terminateWaitTime.value = System.currentTimeMillis()
            showToast(homeScreenState.context, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.")
        } else {
            navController.popBackStack()
        }
    })


    AnimatedVisibility(visible = homeScreenState.workBottomSheetView) {
        WorkBottomSheet(
            modifier,
            navigateHomeGraphToWorkReadyRoutineSelectionRouter,
            navigateHomeGraphToWorkReadyReadyRouter,
            homeScreenState
        )
    }

    HomeScreen(
        modifier,
        userDetailUiState,
        badgeUiState,
        routineUiState,
        workStampUiState,
        navigateMainGraphToCreateRoutineGraph,
        navigateHomeGraphToBadgeBadgeRouter,
        navigateHomeGraphToRoutineDetailGraph,
        navigateHomeGraphToRoutineDetailRoutineRouter,
        navigateHomeGraphToMyinfoProfileRouter,
        navigateHomeGraphToInquiryGraph,
        homeScreenState,
        homeAnimationState
    )
}