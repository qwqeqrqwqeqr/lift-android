package com.gradation.lift.feature.badge.badge

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BadgeBadgeRoute(
    modifier: Modifier = Modifier,
    navigateBadgeGraphToPreGraph: () -> Unit,
    navigateBadgeToSettingInBadgeGraph: ()-> Unit,
    viewModel: BadgeBadgeViewModel = hiltViewModel(),
) {



    BadgeBadgeScreen(
        modifier,

    )

}

@Composable
fun BadgeBadgeScreen(
    modifier: Modifier = Modifier,

) {

}
