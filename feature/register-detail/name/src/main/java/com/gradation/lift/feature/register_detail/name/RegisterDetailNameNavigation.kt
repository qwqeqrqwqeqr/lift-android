package com.gradation.lift.feature.register_detail.name

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateRegisterDetailNameToGender
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
fun registerDetailNameScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    val navigateRegisterDetailNameToGender: () -> Unit =
        { navController.navigateRegisterDetailNameToGender() }


    navGraphBuilder.composable(Router.REGISTER_DETAIL_NAME_ROUTER_NAME) {
        RegisterDetailNameRoute(
            navController = navController,
            navigateRegisterDetailNameToGender = navigateRegisterDetailNameToGender
        )

    }

}



