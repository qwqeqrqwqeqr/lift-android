package com.gradation.lift.feature.register_detail.profile_picture

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun RegisterDetailProfilePictureRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RegisterDetailProfilePictureViewModel = hiltViewModel(),
) {
    RegisterDetailProfilePictureScreen(
        modifier=modifier
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegisterDetailProfilePictureScreen(
    modifier: Modifier = Modifier,
) {



}



