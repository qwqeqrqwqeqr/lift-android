package com.gradation.lift.feature.home.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.resource.LiftImage.WorkLogo
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.home.data.state.BadgeUiState
import com.gradation.lift.feature.home.home.data.state.HomeAnimationState
import com.gradation.lift.feature.home.home.data.state.HomeScreenState
import com.gradation.lift.feature.home.home.data.state.RoutineUiState
import com.gradation.lift.feature.home.home.data.state.UserDetailUiState
import com.gradation.lift.feature.home.home.ui.component.BadgeView
import com.gradation.lift.feature.home.home.ui.component.BannerView
import com.gradation.lift.feature.home.home.ui.component.TopBar
import com.gradation.lift.feature.home.home.ui.component.routineList.emptyRoutineListView
import com.gradation.lift.feature.home.home.ui.component.routineList.failRoutineListView
import com.gradation.lift.feature.home.home.ui.component.routineList.loadingRoutineListView
import com.gradation.lift.feature.home.home.ui.component.routineList.successRoutineListView
import com.gradation.lift.ui.extensions.isScrollingUp
import com.gradation.lift.ui.modifier.noRippleClickable


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    userDetailUiState: UserDetailUiState,
    badgeUiState: BadgeUiState,
    routineUiState: RoutineUiState,
    navigateMainGraphToCreateRoutineGraph: () -> Unit,
    navigateHomeGraphToBadgeGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailGraph: () -> Unit,
    navigateHomeGraphToRoutineDetailRoutineRouter: (Int) -> Unit,
    navigateHomeGraphToBadgeSettingRouter: () -> Unit,
    homeScreenState: HomeScreenState,
    homeAnimationState: HomeAnimationState,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AnimatedVisibility(
                visible = homeScreenState.lazyListState.isScrollingUp(),
                enter = expandVertically(spring()),
                exit = shrinkVertically(spring(stiffness = 0f))
            ) {
                TopBar(
                    modifier,
                    userDetailUiState
                )
            }
        },
        floatingActionButton = {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space4)
            ) {
                GlideImage(
                    modifier = modifier.width(LiftTheme.space.space72),
                    model = WorkLogo,
                    contentDescription = ""
                )

                Box(
                    modifier = modifier
                        .shadow(
                            elevation = LiftTheme.space.space4,
                            spotColor = LiftTheme.colorScheme.no36,
                            ambientColor = LiftTheme.colorScheme.no36,
                            shape = CircleShape
                        )
                        .border(
                            width = LiftTheme.space.space2,
                            color = LiftTheme.colorScheme.no5,
                            shape = CircleShape
                        )
                        .background(
                            LiftTheme.colorScheme.no4,
                            CircleShape
                        )
                        .size(LiftTheme.space.space72)
                        .noRippleClickable { homeScreenState.updateWorkBottomSheetView(true) },
                    contentAlignment = Alignment.Center

                ) {
                    Icon(
                        modifier = modifier
                            .width(LiftTheme.space.space42)
                            .height(LiftTheme.space.space28),
                        painter = painterResource(id = LiftIcon.Work),
                        contentDescription = "work",
                        tint = LiftTheme.colorScheme.no5
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) {
        LazyColumn(
            state = homeScreenState.lazyListState,
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no31)
                .padding(it)
                .padding(
                    start = LiftTheme.space.space20,
                    end = LiftTheme.space.space20,
                ),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space40),
        ) {
            item { BannerView(modifier) }

            item {
                BadgeView(
                    modifier,
                    badgeUiState,
                    navigateHomeGraphToBadgeGraph,
                    navigateHomeGraphToBadgeSettingRouter,
                    homeAnimationState
                )
            }


            when (routineUiState) {
                is RoutineUiState.Fail -> {
                    failRoutineListView(
                        modifier,
                        navigateHomeGraphToRoutineDetailGraph
                    )
                }

                RoutineUiState.Empty -> {
                    emptyRoutineListView(
                        modifier,
                        navigateHomeGraphToRoutineDetailGraph,
                        navigateMainGraphToCreateRoutineGraph
                    )
                }

                RoutineUiState.Loading -> {
                    loadingRoutineListView(
                        modifier,
                        navigateHomeGraphToRoutineDetailGraph
                    )
                }

                is RoutineUiState.Success -> {
                    successRoutineListView(
                        modifier,
                        routineUiState.routineList,
                        navigateHomeGraphToRoutineDetailGraph,
                        navigateMainGraphToCreateRoutineGraph,
                        navigateHomeGraphToRoutineDetailRoutineRouter,
                    )

                }
            }
        }
    }
}

