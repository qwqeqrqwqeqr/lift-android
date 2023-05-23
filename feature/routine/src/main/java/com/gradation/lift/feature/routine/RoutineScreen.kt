package com.gradation.lift.feature.routine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun RoutineRoute(
    modifier: Modifier = Modifier,
    viewModel: RoutineViewModel = hiltViewModel()
){
   RoutineScreen(
       modifier=modifier
   )
}


@Composable
internal fun RoutineScreen(
    modifier: Modifier = Modifier
){
    Text("text")

}


