package com.gradation.lift.feature.register_detail.name

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateNameToGenderInRegisterDetailGraph
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
fun registerDetailNameScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateNameToGenderInRegisterDetailGraph: () -> Unit =
        { navController.navigateNameToGenderInRegisterDetailGraph() }


    navGraphBuilder.composable(Router.REGISTER_DETAIL_NAME_ROUTER_NAME) {
        RegisterDetailNameRoute(
            navController = navController,
            navigateNameToGenderInRegisterDetailGraph = navigateNameToGenderInRegisterDetailGraph
        )
    }

}



