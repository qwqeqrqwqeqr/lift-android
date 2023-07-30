package com.gradation.lift.create_routine.find_work_part

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CreateRoutineFindWorkPartRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineFindWorkPartViewModel = hiltViewModel()
) {
    CreateRoutineFindWorkPartScreen(navController=navController)
}

@Composable
fun CreateRoutineFindWorkPartScreen(navController:NavController){
    Box(

    ){
        Text(
            text="CreateRoutineFindWorkpart",
            color = Color.Black
        )
    }
}