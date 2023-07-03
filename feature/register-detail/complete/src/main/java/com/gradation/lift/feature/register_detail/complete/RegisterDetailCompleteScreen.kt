package com.gradation.lift.feature.register_detail.complete

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun RegisterDetailCompleteRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailCompleteViewModel = hiltViewModel(),
) {


    RegisterDetailCompleteScreen(
        modifier = modifier
    )
}


@Composable
internal fun RegisterDetailCompleteScreen(
    modifier: Modifier = Modifier,
) {


}