package com.gradation.lift.feature.register_detail.name

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun RegisterDetailNameRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailNameViewModel = hiltViewModel(),
) {


    RegisterDetailNameScreen(
        modifier = modifier
    )
}


@Composable
internal fun RegisterDetailNameScreen(
    modifier: Modifier = Modifier,
) {


}