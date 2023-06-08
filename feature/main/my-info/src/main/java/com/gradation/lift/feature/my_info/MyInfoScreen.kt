package com.gradation.lift.feature.my_info

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.navigation.navigation.navigateToCreateRoutine
import com.gradation.lift.navigation.navigation.navigateToCreateRoutineRoutineSet

@Composable
fun MyInfoRoute(
    navController : NavController,
    modifier: Modifier = Modifier) {
    MyInfoScreen(navController)
}

@Composable
fun MyInfoScreen(navController: NavController){
    Box(

    ){
        Text(
            text="내정보화면",
            color = Color.Black
        )

        LiftButton(
            modifier = Modifier,
            onClick = { navController.navigateToCreateRoutineRoutineSet()},
        ) {
            Text(
                text = "테스트용",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}