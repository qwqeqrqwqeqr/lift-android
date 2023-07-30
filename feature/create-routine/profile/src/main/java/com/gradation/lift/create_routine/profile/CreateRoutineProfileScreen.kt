package com.gradation.lift.create_routine.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.create_routine.CreateRoutineSharedViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CreateRoutineProfileRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    sharedViewModel: CreateRoutineSharedViewModel
) {


    CreateRoutineProfileScreen(
        name = sharedViewModel.name.collectAsStateWithLifecycle()
    )
}

@Composable
fun CreateRoutineProfileScreen(name: State<String>) {
    Box(

    ) {
        Text(
            text = name.value,
            color = Color.Black
        )

    }
}