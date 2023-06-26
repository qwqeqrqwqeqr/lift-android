package com.gradation.lift.splash

import android.app.Activity
import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.gradation.lift.MainActivityViewModel
import com.gradation.lift.feature.routine.viewmodel.RoutineViewModel

@Composable
fun SplashRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    mainActivityViewModel: MainActivityViewModel,
    systemUiController: SystemUiController,
) {
    SplashScreen(
        navController = navController,
        modifier = modifier,
        mainActivityViewModel = mainActivityViewModel,
        systemUiController = systemUiController
    )
}

@Composable
internal fun SplashScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    mainActivityViewModel: MainActivityViewModel,
    systemUiController: SystemUiController,
) {
    mainActivityViewModel.setSplashSystemUiController(systemUiController)

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.fillMaxSize()
    ) {
    }
}

