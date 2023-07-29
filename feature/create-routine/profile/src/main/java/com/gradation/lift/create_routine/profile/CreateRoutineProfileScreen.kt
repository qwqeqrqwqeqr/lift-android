package com.gradation.lift.create_routine.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CreateRoutineProfileRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineProfileViewModel = hiltViewModel(),
    ) {
    CreateRoutineProfileScreen()
}

@Composable
fun CreateRoutineProfileScreen() {
    Box(

    ) {
        Text(
            text = "CreateRoutineRoutineDetail",
            color = Color.Black
        )

    }
}