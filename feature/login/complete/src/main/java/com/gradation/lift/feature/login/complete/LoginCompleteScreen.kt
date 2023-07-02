package com.gradation.lift.feature.login.complete

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun LoginCompleteRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginCompleteViewModel = hiltViewModel(),
) {


    LoginCompleteScreen(
        modifier = modifier
    )
}


@Composable
internal fun LoginCompleteScreen(
    modifier: Modifier = Modifier,
) {


}