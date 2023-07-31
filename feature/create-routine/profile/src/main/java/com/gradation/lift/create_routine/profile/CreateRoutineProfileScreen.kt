package com.gradation.lift.create_routine.profile

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.create_routine.data.CreateRoutineSharedViewModel
import com.gradation.lift.navigation.Router

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun CreateRoutineProfileRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val crateRoutineBackStackEntry =
        remember { navController.getBackStackEntry(Router.CREATE_ROUTINE_GRAPH_NAME) }
    val viewModel: CreateRoutineSharedViewModel = hiltViewModel(crateRoutineBackStackEntry)

    val name = viewModel.name.collectAsStateWithLifecycle()

    Log.d("test", name.value)
    CreateRoutineProfileScreen(
        name = name
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