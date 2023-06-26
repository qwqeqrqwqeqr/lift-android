package com.gradation.lift.feature.login.sign_in

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun LoginSignInRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: LoginSignInViewModel = hiltViewModel()
) {
    LoginSignInScreen()
}


@Composable
fun LoginSignInScreen(){
    Box(

    ){
        Text(
            text="CreateRoutineRoutine",
            color = Color.Black
        )
    }
}