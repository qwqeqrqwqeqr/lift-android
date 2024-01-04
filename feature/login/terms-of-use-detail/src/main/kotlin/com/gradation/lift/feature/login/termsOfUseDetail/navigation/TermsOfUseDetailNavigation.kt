package com.gradation.lift.feature.login.termsOfUseDetail.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router
import com.gradation.lift.navigation.navigation.navigateTermsOfUseDetailToTermsOfUseInLoginGraph

fun NavGraphBuilder.termsOfUseDetailScreen(
    modifier: Modifier=Modifier,
    navController: NavController,
) {

    val navigateTermsOfUseDetailToTermsOfUseInLoginGraph:()-> Unit = {navController.navigateTermsOfUseDetailToTermsOfUseInLoginGraph()}


    composable(Router.LOGIN_TERMS_OF_USE_DETAIL_ROUTER_NAME) {
        TermsOfUseDetailRoute(
            modifier,navController,navigateTermsOfUseDetailToTermsOfUseInLoginGraph
        )
    }

}


