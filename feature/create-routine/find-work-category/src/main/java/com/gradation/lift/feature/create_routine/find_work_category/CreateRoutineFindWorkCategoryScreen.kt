package com.gradation.lift.feature.create_routine.find_work_category

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CreateRoutineFindWorkCategoryRoute(
    navController : NavController,
    modifier: Modifier = Modifier,
    viewModel: CreateRoutineFindWorkCategoryViewModel = hiltViewModel()
) {
    CreateRoutineFindWorkCategoryScreen()
}

@Composable
fun CreateRoutineFindWorkCategoryScreen(){
    Box(

    ){
        Text(
            text="CreateRoutineFindWorkCategory",
            color = Color.Black
        )
    }
}