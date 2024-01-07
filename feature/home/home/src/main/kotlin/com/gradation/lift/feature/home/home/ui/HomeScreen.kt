package com.gradation.lift.feature.home.home.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.gradation.lift.ui.modifier.noRippleClickable
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.home.ui.component.BadgeView
import com.gradation.lift.feature.home.home.ui.component.BannerView
import com.gradation.lift.feature.home.home.ui.component.RoutineView
import com.gradation.lift.feature.home.home.ui.component.TopBar
import com.gradation.lift.feature.home.home.data.state.BadgeUiState
import com.gradation.lift.feature.home.home.data.state.RoutineUiState
import com.gradation.lift.feature.home.home.data.state.UserDetailUiState


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
    scrollState: ScrollState,
) {
    Scaffold(
        modifier = modifier,
        topBar = { TopBar(modifier, userDetailUiState, navigateHomeGraphToNotificationGraph) },
        floatingActionButton = {
            Box(
                modifier = modifier

                    .border(
                        width = LiftTheme.space.space2,
                        color = LiftTheme.colorScheme.no5,
                        shape = RoundedCornerShape(size = LiftTheme.space.space40)
                    )
                    .background(LiftTheme.colorScheme.no4, RoundedCornerShape(size = LiftTheme.space.space40))
                    .size(LiftTheme.space.space72)
                    .noRippleClickable { navigateMainGraphToWorkGraph() },
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
                    .padding(LiftTheme.space.space16), verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space36)
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

