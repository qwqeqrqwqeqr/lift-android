package com.gradation.lift.feature.login.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.gradation.lift.feature.login.resetPassword.navigation.resetPasswordScreen
import com.gradation.lift.feature.login.signIn.navigation.signInScreen
import com.gradation.lift.feature.login.signInDefault.navigation.signInDefaultScreen
import com.gradation.lift.feature.login.signUpCreateEmail.navigation.signUpCreateEmailScreen
import com.gradation.lift.feature.login.signUpCreatePassword.navigation.signUpCreatePasswordScreen
import com.gradation.lift.feature.login.termsOfUse.navigation.termsOfUseScreen
import com.gradation.lift.feature.login.termsOfUseDetail.navigation.termsOfUseDetailScreen
import com.gradation.lift.feature.login.verifyEmail.navigation.verifyEmailScreen
import com.gradation.lift.navigation.Route.LOGIN_GRAPH_NAME
import com.gradation.lift.navigation.Route.LOGIN_SIGN_IN_ROUTER_NAME


fun loginGraphBuilder(
    modifier: Modifier = Modifier,
    navController: NavController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.navigation(
        route = LOGIN_GRAPH_NAME,
        startDestination = LOGIN_SIGN_IN_ROUTER_NAME,
    ) {
        resetPasswordScreen(modifier, navController)
        signInScreen(modifier, navController)
        signInDefaultScreen(modifier, navController)
        signUpCreateEmailScreen(modifier, navController)
        signUpCreatePasswordScreen(modifier, navController)
        termsOfUseScreen(modifier, navController)
        termsOfUseDetailScreen(modifier, navController)
        verifyEmailScreen(modifier, navController)
    }

}