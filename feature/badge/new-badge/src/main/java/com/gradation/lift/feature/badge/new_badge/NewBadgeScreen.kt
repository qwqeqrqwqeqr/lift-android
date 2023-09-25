package com.gradation.lift.feature.badge.new_badge

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NewBadgeRoute(
    modifier: Modifier = Modifier,
    navigateNewBadgeGraphToHomeGraph: ()-> Unit,
    viewModel: NewBadgeViewModel = hiltViewModel(),
) {



    NewBadgeScreen(
        modifier,

    )

}

@Composable
fun NewBadgeScreen(
    modifier: Modifier = Modifier,

) {

}
