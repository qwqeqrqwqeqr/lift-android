package com.gradation.lift.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.register_detail.complete.registerDetailCompleteScreen
import com.gradation.lift.feature.register_detail.gender.registerDetailGenderScreen
import com.gradation.lift.feature.register_detail.ht_wt.registerDetailHtWtScreen
import com.gradation.lift.feature.register_detail.name.registerDetailNameScreen
import com.gradation.lift.feature.register_detail.unit_of_weight.registerDetailUnitOfWeightScreen
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.route.register_detail.RegisterDetailGraph

fun registerDetailGraph(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    RegisterDetailGraph { route, graphStartDestination ->
        navGraphBuilder.navigation(
            route = route,
            startDestination = graphStartDestination,
        ) {
            registerDetailCompleteScreen(navController, this)
            registerDetailGenderScreen(navController, this)
            registerDetailHtWtScreen(navController, this)
            registerDetailNameScreen(navController, this)
            registerDetailUnitOfWeightScreen(navController, this)
        }
    }.registerDetailGraph(
        route = Router.REGISTER_GRAPH_ROUTER_NAME,
        startDestination = Router.REGISTER_DETAIL_NAME_ROUTER_NAME,
    )
}