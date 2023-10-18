package com.gradation.lift.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.home.data.*

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

//    when (badgeConditionState) {
//        is BadgeConditionState.Success -> LaunchedEffect(true) {
//            navigateHomeGraphToNewBadgeGraph()
//        }
//
//        BadgeConditionState.None -> {}
//    }



    HomeScreen(
        modifier,
    )


}


@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
) {

    Surface(
        modifier = modifier.fillMaxSize(),
        color = LiftTheme.colorScheme.no17
    ) {
    }
}


@Preview
@Composable
internal fun HomeScreenPreview() {
    LiftMaterialTheme {
        HomeScreen(
            modifier = Modifier,
        )
    }
}
