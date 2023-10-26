package com.gradation.lift.feature.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.extensions.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.component.BadgeView
import com.gradation.lift.feature.home.component.BannerView
import com.gradation.lift.feature.home.component.RoutineView
import com.gradation.lift.feature.home.component.TopBar
import com.gradation.lift.feature.home.data.*
import com.gradation.lift.feature.home.data.state.BadgeConditionState
import com.gradation.lift.feature.home.data.state.BadgeUiState
import com.gradation.lift.feature.home.data.state.RoutineUiState
import com.gradation.lift.feature.home.data.state.UserDetailUiState

@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
    navigateMainGraphToWorkGraph: () -> Unit,
    navigateHomeGraphToBadgeGraph: () -> Unit,
    navigateHomeGraphToNewBadgeGraph: () -> Unit,
    navigateHomeGraphToNotificationGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailRoutineRouter: (Int) -> Unit,
    navigateHomeGraphToBadgeSettingRouter: () -> Unit,
) {
    val userDetailUiState: UserDetailUiState by viewModel.userDetailUiState.collectAsStateWithLifecycle()
    val badgeUiState: BadgeUiState by viewModel.badgeUiState.collectAsStateWithLifecycle()
    val routineUiState: RoutineUiState by viewModel.routineUiState.collectAsStateWithLifecycle()
    val badgeConditionState: BadgeConditionState by viewModel.badgeConditionState.collectAsStateWithLifecycle()

    val scrollState: ScrollState = rememberScrollState()

    when (badgeConditionState) {
        is BadgeConditionState.Success -> LaunchedEffect(true) {
            navigateHomeGraphToNewBadgeGraph()
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
        navigateHomeGraphToNotificationGraph,
        navigateHomeGraphToRoutineDetailGraph,
        navigateHomeGraphToRoutineDetailRoutineRouter,
        navigateHomeGraphToBadgeSettingRouter,
        scrollState
    )
}


@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    userDetailUiState: UserDetailUiState,
    badgeUiState: BadgeUiState,
    routineUiState: RoutineUiState,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
    navigateMainGraphToWorkGraph: () -> Unit,
    navigateHomeGraphToBadgeGraph: () -> Unit,
    navigateHomeGraphToNotificationGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailRoutineRouter: (Int) -> Unit,
    navigateHomeGraphToBadgeSettingRouter: () -> Unit,
    scrollState: ScrollState
) {
    Scaffold(
        modifier = modifier,
        topBar = { TopBar(modifier, userDetailUiState, navigateHomeGraphToNotificationGraph) },
        floatingActionButton = {
            Box(
                modifier = modifier

                    .border(
                        width = 2.dp,
                        color = LiftTheme.colorScheme.no5,
                        shape = RoundedCornerShape(size = 40.dp)
                    )
                    .background(LiftTheme.colorScheme.no4, RoundedCornerShape(size = 40.dp))
                    .size(72.dp)
                    .noRippleClickable { navigateMainGraphToWorkGraph() },
                contentAlignment = Alignment.Center

            ) {
                Icon(
                    modifier = modifier
                        .width(42.dp)
                        .height(28.dp),
                    painter = painterResource(id = LiftIcon.Work),
                    contentDescription = "work",
                    tint = LiftTheme.colorScheme.no5
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = LiftTheme.colorScheme.no31
        ) {
            Column(
                modifier = modifier
                    .verticalScroll(scrollState)
                    .padding(it)
                    .padding(16.dp), verticalArrangement = Arrangement.spacedBy(36.dp)
            ) {
                BannerView(modifier)
                BadgeView(
                    modifier,
                    badgeUiState,
                    navigateHomeGraphToBadgeGraph,
                    navigateHomeGraphToBadgeSettingRouter
                )
                RoutineView(
                    modifier,
                    routineUiState,
                    navigateMainGraphToCreateRoutineGraph,
                    navigateHomeGraphToRoutineDetailGraph,
                    navigateHomeGraphToRoutineDetailRoutineRouter
                )
            }
        }
    }
}


@Preview
@Composable
internal fun HomeScreenPreview() {
    LiftMaterialTheme {

    }
}
