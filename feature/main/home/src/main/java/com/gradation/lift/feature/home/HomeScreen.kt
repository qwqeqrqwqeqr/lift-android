package com.gradation.lift.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(
        modifier = modifier,
    )
}


@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
) {

}

