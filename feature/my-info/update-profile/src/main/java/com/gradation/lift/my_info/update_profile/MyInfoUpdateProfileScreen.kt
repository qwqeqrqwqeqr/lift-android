package com.gradation.lift.my_info.update_profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun MyInfoUpdateProfileRoute(
    modifier: Modifier = Modifier,
    viewModel: MyInfoUpdateProfileViewModel = hiltViewModel(),
) {

    MyInfoUpdateProfileScreen(modifier)

}

@Composable
fun MyInfoUpdateProfileScreen(modifier: Modifier = Modifier) {
    Surface(color = LiftTheme.colorScheme.no5, modifier = modifier.fillMaxSize()) {

    }
}