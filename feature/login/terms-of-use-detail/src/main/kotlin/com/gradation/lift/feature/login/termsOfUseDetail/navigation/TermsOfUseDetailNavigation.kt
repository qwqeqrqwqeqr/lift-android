package com.gradation.lift.feature.login.termsOfUseDetail.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Router

fun termsOfUseDetailScreen(
    modifier: Modifier=Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {


    navGraphBuilder.composable(Router.LOGIN_TERMS_OF_USE_DETAIL_ROUTER_NAME) {
        TermsOfUseDetailRoute()
    }

}


