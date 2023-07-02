package com.gradation.lift.feature.my_info

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.navigation.navigation.navigateToCreateRoutineRoutineSet

@Composable
fun MyInfoRoute(
    navController: NavController,
) {
    MyInfoScreen(navController)
}

@Composable
fun MyInfoScreen(navController: NavController) {
    Box(

    ) {
        Text(
            text = "이거 왜이래",
            color = Color.Black
        )

    }
}