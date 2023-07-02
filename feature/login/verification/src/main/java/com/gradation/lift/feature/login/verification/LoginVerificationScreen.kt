package com.gradation.lift.feature.login.verification

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun LoginVerificationRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginVerificationViewModel = hiltViewModel(),
) {

    Text(text = viewModel.test)
    LoginVerificationScreen(
        modifier=modifier
    )
}



@Composable
internal fun LoginVerificationScreen(
    modifier: Modifier =Modifier
){


}