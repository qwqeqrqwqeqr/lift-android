package com.gradation.lift.feature.inquiry.registerInquiry.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.MY_INFO_CANCEL_MEMBERSHIP_ROUTER_NAME

fun NavGraphBuilder.registerInquiryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {


    val navigatePreGraph: () -> Unit = { navController.popBackStack() }

    composable(
        route = MY_INFO_CANCEL_MEMBERSHIP_ROUTER_NAME,
    ) {


        RegisterInquiryRoute(
            modifier,
        )
    }

}


