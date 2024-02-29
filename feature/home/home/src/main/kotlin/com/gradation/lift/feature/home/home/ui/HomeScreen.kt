package com.gradation.lift.feature.home.home.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.button.LiftStartWorkButton
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.home.data.state.BadgeUiState
import com.gradation.lift.feature.home.home.data.state.HomeAnimationState
import com.gradation.lift.feature.home.home.data.state.HomeScreenState
import com.gradation.lift.feature.home.home.data.state.RoutineUiState
import com.gradation.lift.feature.home.home.data.state.UserDetailUiState
import com.gradation.lift.feature.home.home.data.state.WorkStampUiState
import com.gradation.lift.feature.home.home.data.state.rememberHomeAnimationState
import com.gradation.lift.feature.home.home.data.state.rememberHomeScreenState
import com.gradation.lift.feature.home.home.ui.component.BadgeView
import com.gradation.lift.feature.home.home.ui.component.BannerView
import com.gradation.lift.feature.home.home.ui.component.HeaderView
import com.gradation.lift.feature.home.home.ui.component.WorkStampView
import com.gradation.lift.feature.home.home.ui.component.routineList.RoutineListView


@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    userDetailUiState: UserDetailUiState,
    badgeUiState: BadgeUiState,
    routineUiState: RoutineUiState,
    workStampUiState: WorkStampUiState,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
    navigateHomeGraphToBadgeBadgeRouter: (Int) -> Unit,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailRoutineRouter: (Int) -> Unit,

    navigateHomeGraphToMyinfoProfileRouter: () -> Unit,
    navigateHomeGraphToInquiryGraph: () -> Unit,
    homeScreenState: HomeScreenState,
    homeAnimationState: HomeAnimationState,
) {
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            LiftStartWorkButton(modifier = modifier) {
                homeScreenState.updateWorkBottomSheetView(true)
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) {
        LazyColumn(
            state = homeScreenState.lazyListState,
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no17)
                .padding(it),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space36),
        ) {

            item {
                HeaderView(
                    modifier,
                    userDetailUiState,
                    navigateHomeGraphToMyinfoProfileRouter
                )
                BannerView(modifier, navigateHomeGraphToInquiryGraph, homeScreenState)
            }
            item {
                BadgeView(
                    modifier,
                    badgeUiState,
                    navigateHomeGraphToBadgeBadgeRouter,
                    homeAnimationState
                )
            }
            item {
                WorkStampView(
                    modifier,
                    workStampUiState
                )
            }
            item {
                RoutineListView(
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun HomeScreenPreview() {
    LiftMaterialTheme {
        HomeScreen(
            userDetailUiState = UserDetailUiState.Loading,
            badgeUiState = BadgeUiState.Loading,
            routineUiState = RoutineUiState.Loading,
            workStampUiState = WorkStampUiState.Loading,
            navigateMainGraphToCreateRoutineGraph = { },
            navigateHomeGraphToBadgeBadgeRouter = { },
            navigateHomeGraphToRoutineDetailGraph = { },
            navigateHomeGraphToRoutineDetailRoutineRouter = { },
            navigateHomeGraphToMyinfoProfileRouter = { },
            navigateHomeGraphToInquiryGraph = {},
            homeScreenState = rememberHomeScreenState(),
            homeAnimationState = rememberHomeAnimationState()
        )
    }
}
