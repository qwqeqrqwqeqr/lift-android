package com.gradation.lift.feature.register_detail.name

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.canvas.NumberCircle
import com.gradation.lift.ui.DevicePreview

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
    Row{
        NumberCircle(number = 1)
        NumberCircle(number = 1)
        NumberCircle(number = 1)

    }

}

@DevicePreview
@Composable
fun RegisterDetailNameScreenPreview(
    modifier: Modifier =Modifier
){
    RegisterDetailNameScreen(modifier)
}