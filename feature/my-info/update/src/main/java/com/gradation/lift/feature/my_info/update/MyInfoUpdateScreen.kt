package com.gradation.lift.feature.my_info.update

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun MyInfoUpdateRoute(
    modifier: Modifier =Modifier,
    viewModel: MyInfoUpdateViewModel = hiltViewModel(),
){

    MyInfoUpdateScreen(modifier)

}

@Composable
fun MyInfoUpdateScreen(modifier:Modifier=Modifier){
    Surface(color = LiftTheme.colorScheme.no5, modifier = modifier.fillMaxSize()) {

    }
}