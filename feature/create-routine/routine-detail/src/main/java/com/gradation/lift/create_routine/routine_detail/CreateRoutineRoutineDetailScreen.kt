package com.gradation.lift.create_routine.routine_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon

@Composable
fun CreateRoutineRoutineDetailRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineRoutineDetailViewModel = hiltViewModel(),
    ) {
    CreateRoutineRoutineDetailScreen()
}

@Composable
fun CreateRoutineRoutineDetailScreen() {
    Box(

    ) {
        Text(
            text = "CreateRoutineRoutineDetail",
            color = Color.Black
        )

    }
}