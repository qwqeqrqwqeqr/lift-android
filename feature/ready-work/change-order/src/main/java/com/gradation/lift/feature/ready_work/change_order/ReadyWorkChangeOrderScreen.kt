package com.gradation.lift.feature.ready_work.change_order

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun ReadyWorkChangeOrderRoute(
    navController : NavController,
    modifier: Modifier = Modifier,
    viewModel: ReadyWorkChangeOrderViewModel = hiltViewModel()
) {
    ReadyWorkChangeOrderScreen()
}

@Composable
fun ReadyWorkChangeOrderScreen(){
    Box(

    ){
        Text(
            text="ReadyWorkChangeOrder",
            color = Color.Black
        )
    }
}