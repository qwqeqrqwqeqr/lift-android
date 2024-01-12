package com.gradation.lift.feature.myInfo.profile.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.gradation.lift.feature.myInfo.profile.ui.ProfileScreen
import com.gradation.lift.feature.myInfo.profile.data.ProfileViewModel

@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    BackHandler(onBack = {  })

    ProfileScreen(
        modifier,
    )
}

