package com.gradation.lift.feature.register_detail.name

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateRegisterDetailNameToGender
import com.gradation.lift.navigation.route.register_detail.RegisterDetailNameRoute
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
fun registerDetailNameScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    RegisterDetailNameRoute { route ->

        val navigateRegisterDetailNameToGender: () -> Unit =
            { navController.navigateRegisterDetailNameToGender() }


        navGraphBuilder.composable(route) {
            RegisterDetailNameRoute(
                navController = navController,
                navigateRegisterDetailNameToGender = navigateRegisterDetailNameToGender
            )

        }
    }.registerDetailNameScreen(route = Router.REGISTER_DETAIL_NAME_ROUTER_NAME)
}



