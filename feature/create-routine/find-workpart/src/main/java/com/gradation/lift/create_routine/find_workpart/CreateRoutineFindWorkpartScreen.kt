package com.gradation.lift.create_routine.find_workpart

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CreateRoutineFindWorkpartRoute(
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineFindWorkpartViewModel = hiltViewModel()
) {
    CreateRoutineFindWorkpartScreen()
}

@Composable
fun CreateRoutineFindWorkpartScreen(){
    Box(

    ){
        Text(
            text="CreateRoutineFindWorkpart",
            color = Color.Black
        )
    }
}