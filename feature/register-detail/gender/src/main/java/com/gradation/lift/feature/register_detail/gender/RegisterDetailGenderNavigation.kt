package com.gradation.lift.feature.register_detail.gender

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateRegisterDetailGenderToHeightWeight
import com.gradation.lift.navigation.navigation.navigateRegisterDetailGenderToName
import com.gradation.lift.navigation.route.register_detail.RegisterDetailGenderRoute
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey
import com.gradation.lift.navigation.saved_state.getValueSavedStateHandle

fun registerDetailGenderScreen(
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    RegisterDetailGenderRoute { route ->
        val name =
            navController.getValueSavedStateHandle<String>(SavedStateHandleKey.RegisterDetailKey.NAME_KEY) ?: ""

        val navigateRegisterDetailGenderToHeightWeight =
            { navController.navigateRegisterDetailGenderToHeightWeight() }

        val navigateRegisterDetailGenderToName =
            { navController.navigateRegisterDetailGenderToName() }

        navGraphBuilder.composable(route) {
            RegisterDetailGenderRoute(
                navController = navController,
                name = name,
                navigateRegisterDetailGenderToHeightWeight = navigateRegisterDetailGenderToHeightWeight,
                navigateRegisterDetailGenderToName = navigateRegisterDetailGenderToName
            )
        }
    }.registerDetailGenderScreen(route = Router.REGISTER_DETAIL_GENDER_ROUTER_NAME)
}



