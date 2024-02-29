package com.gradation.lift.myInfo.termsPolicyDetail.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gradation.lift.navigation.Route.MY_INFO_TERMS_POLICY_DETAIL_ROUTER_NAME
import com.gradation.lift.navigation.navigation.navigateTermsPolicyDetailToTermsPolicyMyInfoGraph
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.MyInfo.TERMS_POLICY_CONTENTS_KEY
import com.gradation.lift.navigation.saved_state.SavedStateHandleKey.MyInfo.TERMS_POLICY_TITLE_KEY

fun NavGraphBuilder.termsPolicyDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    composable(
        route = "$MY_INFO_TERMS_POLICY_DETAIL_ROUTER_NAME/{$TERMS_POLICY_TITLE_KEY}/{$TERMS_POLICY_CONTENTS_KEY}",
        arguments = listOf(
            navArgument(TERMS_POLICY_TITLE_KEY) { type = NavType.StringType },
            navArgument(TERMS_POLICY_CONTENTS_KEY) { type = NavType.StringType }
        )
    ) {

        val navigateTermsPolicyDetailToTermsPolicyMyInfoGraph: () -> Unit = {
            navController.navigateTermsPolicyDetailToTermsPolicyMyInfoGraph()
        }
        val title: String = it.arguments?.getString(TERMS_POLICY_TITLE_KEY) ?: ""
        val content: String = it.arguments?.getString(TERMS_POLICY_CONTENTS_KEY) ?: ""


        TermsPolicyDetailRoute(
            modifier,
            title,
            content,
            navigateTermsPolicyDetailToTermsPolicyMyInfoGraph
        )
    }

}
