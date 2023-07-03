package com.gradation.lift.feature.register_detail.gender

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun RegisterDetailGenderRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailGenderViewModel = hiltViewModel(),
) {


    RegisterDetailGenderScreen(
        modifier = modifier
    )
}


@Composable
internal fun RegisterDetailGenderScreen(
    modifier: Modifier = Modifier,
) {


}