package com.gradation.lift.feature.login.termsOfUseDetail.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.login.common.data.LoginSharedViewModel
import com.gradation.lift.feature.login.common.data.TermsOfUseState
import com.gradation.lift.feature.login.termsOfUseDetail.data.TermsOfUseDetailScreenState
import com.gradation.lift.feature.login.termsOfUseDetail.data.TermsOfUseDetailViewModel
import com.gradation.lift.feature.login.termsOfUseDetail.data.rememberTermsOfUseDetailScreenState
import com.gradation.lift.feature.login.termsOfUseDetail.ui.TermsOfUseDetailScreen
import com.gradation.lift.navigation.Route

@Composable
fun TermsOfUseDetailRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateTermsOfUseDetailToTermsOfUseInLoginGraph: () -> Unit,
    viewModel: TermsOfUseDetailViewModel = hiltViewModel(),
    @SuppressLint("UnrememberedGetBackStackEntry") sharedViewModel: LoginSharedViewModel = hiltViewModel(
        remember { navController.getBackStackEntry(Route.LOGIN_GRAPH_NAME) }
    ),
    termsOfUseDetailScreenState: TermsOfUseDetailScreenState = rememberTermsOfUseDetailScreenState(),
) {


    val termsOfUseState: TermsOfUseState by sharedViewModel.termsOfUseState.collectAsStateWithLifecycle()
    lateinit var title: String
    lateinit var content: String


    when (val result = termsOfUseState) {
        is TermsOfUseState.Marketing -> {
            title = result.title
            content = result.content
        }

        is TermsOfUseState.PrivacyPolicy -> {
            title = result.title
            content = result.content
        }

        is TermsOfUseState.TermsOfUse -> {
            title = result.title
            content = result.content
        }
    }


    TermsOfUseDetailScreen(
        modifier,
        title,
        content,
        navigateTermsOfUseDetailToTermsOfUseInLoginGraph,
        termsOfUseDetailScreenState
    )

    BackHandler(onBack = navigateTermsOfUseDetailToTermsOfUseInLoginGraph)

}
