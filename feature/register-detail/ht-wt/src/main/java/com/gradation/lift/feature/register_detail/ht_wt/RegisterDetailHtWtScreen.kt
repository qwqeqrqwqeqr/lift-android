package com.gradation.lift.feature.register_detail.ht_wt

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun RegisterHtWtNameRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailHtWtViewModel = hiltViewModel(),
) {


    RegisterDetailHtWtScreen(
        modifier = modifier
    )
}


@Composable
internal fun RegisterDetailHtWtScreen(
    modifier: Modifier = Modifier,
) {


}