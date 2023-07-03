package com.gradation.lift.feature.register_detail.unit_of_weight

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun LoginVerificationRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailUnitOfWeightViewModel = hiltViewModel(),
) {


    RegisterDetailUnitOfWeightScreen(
        modifier = modifier
    )
}


@Composable
internal fun RegisterDetailUnitOfWeightScreen(
    modifier: Modifier = Modifier,
) {


}