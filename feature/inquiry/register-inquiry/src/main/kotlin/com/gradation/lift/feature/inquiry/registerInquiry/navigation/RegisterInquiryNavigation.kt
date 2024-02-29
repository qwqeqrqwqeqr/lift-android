package com.gradation.lift.feature.inquiry.registerInquiry.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.INQUIRY_REGISTER_INQUIRY_ROUTER_NAME

fun NavGraphBuilder.registerInquiryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val navigatePreGraph: () -> Unit = { navController.popBackStack() }

    composable(
        route = INQUIRY_REGISTER_INQUIRY_ROUTER_NAME,
    ) {


        RegisterInquiryRoute(
            modifier,
            navigatePreGraph
        )
    }

}


