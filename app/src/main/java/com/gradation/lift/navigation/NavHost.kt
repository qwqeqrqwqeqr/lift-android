package com.gradation.lift.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gradation.lift.*

@Composable
fun NavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = "home",
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {
            Screen1(navController)
        }
        composable("routine") {
            Screen2(navController)
        }
        composable("history") {
            Screen3(navController)
        }
        composable("my-info") {
            Screen4(navController)
        }
        composable("child1") {
            Child1(navController)
        }
    }
}