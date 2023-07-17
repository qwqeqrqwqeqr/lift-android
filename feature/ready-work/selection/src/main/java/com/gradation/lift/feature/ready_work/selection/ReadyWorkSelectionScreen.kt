package com.gradation.lift.feature.ready_work.selection

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
internal fun ReadyWorkSelectionRoute(
    navController : NavController,
    modifier: Modifier = Modifier,
    viewModel: ReadyWorkSelectionViewModel = hiltViewModel()
) {
    ReadyWorkSelectionScreen()
}

@Composable
internal fun ReadyWorkSelectionScreen(){
    Box(

    ){
        Text(
            text="ReadyWorkChangeOrder",
            color = Color.Black
        )
    }
}