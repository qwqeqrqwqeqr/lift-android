package com.gradation.lift.feature.badge.setting

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BadgeSettingRoute(
    modifier: Modifier = Modifier,
    navigateSettingToBadgeInBadgeGraph: ()-> Unit,
    viewModel: BadgeSettingViewModel = hiltViewModel(),
) {



    BadgeSettingScreen(
        modifier,

    )

}

@Composable
fun BadgeSettingScreen(
    modifier: Modifier = Modifier,

) {

}
