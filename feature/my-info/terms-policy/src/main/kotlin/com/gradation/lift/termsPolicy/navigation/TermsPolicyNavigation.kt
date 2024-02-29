package com.gradation.lift.termsPolicy.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gradation.lift.navigation.Route.MY_INFO_TERMS_POLICY_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateTermsPolicyToMyInfoInMyInfoGraph
import com.gradation.lift.navigation.navigation.navigateTermsPolicyToTermsPolicyDetailMyInfoGraph

fun NavGraphBuilder.termsPolicyScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {

    val navigateTermsPolicyToTermsPolicyDetailMyInfoGraph: (String, String) -> Unit =
        { title, content ->
            navController.navigateTermsPolicyToTermsPolicyDetailMyInfoGraph(title, content)
        }
    val navigateTermsPolicyToMyInfoInMyInfoGraph: () -> Unit = {
        navController.navigateTermsPolicyToMyInfoInMyInfoGraph()
    }

    composable(
        route = MY_INFO_TERMS_POLICY_ROUTER_NAME,
    ) {


        TermsPolicyRoute(
            modifier,
            navigateTermsPolicyToTermsPolicyDetailMyInfoGraph,
            navigateTermsPolicyToMyInfoInMyInfoGraph
        )
    }

}


