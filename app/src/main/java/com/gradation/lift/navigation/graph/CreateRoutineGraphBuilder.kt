package com.gradation.lift.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.create_routine.profile.createRoutineProfileScreen
import com.gradation.lift.create_routine.routine.createRoutineRoutineScreen
import com.gradation.lift.feature.create_routine.createRoutineScreen
import com.gradation.lift.feature.create_routine.find_work_category.createRoutineFindWorkCategoryScreen
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_GRAPH_NAME
import com.gradation.lift.navigation.Router.CREATE_ROUTINE_ROUTER_NAME

@RequiresApi(Build.VERSION_CODES.O)
fun createRoutineGraphBuilder(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {



    navGraphBuilder.navigation(
        route = CREATE_ROUTINE_GRAPH_NAME,
        startDestination = CREATE_ROUTINE_ROUTER_NAME,
    ) {

        createRoutineScreen(navController, this)
        createRoutineRoutineScreen(navController, this)
        createRoutineProfileScreen(navController, this)
        createRoutineFindWorkCategoryScreen(navController, this)
    }
}




