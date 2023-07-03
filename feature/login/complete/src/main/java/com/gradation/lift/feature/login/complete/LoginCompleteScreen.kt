package com.gradation.lift.feature.login.complete

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.ui.DevicePreview

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
    Box(modifier.fillMaxSize()){
        Text(
            "어쩔티비",
            color = LiftTheme.colorScheme.no4
        )
    }


}

@DevicePreview
@Composable
internal fun LoginCompleteScreenPreview() {
    LiftMaterialTheme(isDarkTheme = false) {
        LoginCompleteScreen()
    }
}