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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.gradation.lift.MainActivityViewModel
import com.gradation.lift.feature.routine.viewmodel.RoutineViewModel
import com.gradation.lift.navigation.navigation.navigateToCreateRoutineRoutineSet
import com.gradation.lift.navigation.navigation.navigateToHome
import com.gradation.lift.navigation.navigation.navigateToLoginGraph

@Composable
fun SplashRoute(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = hiltViewModel(),
    mainActivityViewModel: MainActivityViewModel,
    systemUiController: SystemUiController,
) {
    val splashUiState by viewModel.splashUiState.collectAsStateWithLifecycle()

    SplashScreen(
        navController = navController,
        modifier = modifier,
        mainActivityViewModel = mainActivityViewModel,
        systemUiController = systemUiController,
        splashUiState = splashUiState
    )
}

@Composable
internal fun SplashScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    mainActivityViewModel: MainActivityViewModel,
    systemUiController: SystemUiController,
    splashUiState: SplashUiState,
) {
    mainActivityViewModel.setSplashSystemUiController(systemUiController)

    when (splashUiState) {
        SplashUiState.Loading -> Surface(
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier.fillMaxSize()
        ) {
        }
        SplashUiState.Login -> {
            mainActivityViewModel.setDefaultSystemUiController(systemUiController)
            navController.navigateToLoginGraph()
        }
        SplashUiState.Main ->{
            mainActivityViewModel.setDefaultSystemUiController(systemUiController)
            navController.navigateToHome()
        }
    }


}

