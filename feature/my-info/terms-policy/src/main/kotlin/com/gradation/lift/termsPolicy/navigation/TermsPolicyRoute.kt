package com.gradation.lift.termsPolicy.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.termsPolicy.ui.TermsPolicyScreen


@Composable
fun TermsPolicyRoute(
    modifier: Modifier = Modifier,
    navigateTermsPolicyToTermsPolicyDetailMyInfoGraph: (String, String) -> Unit,
    navigateTermsPolicyToMyInfoInMyInfoGraph: () -> Unit,
) {
    BackHandler(onBack = navigateTermsPolicyToMyInfoInMyInfoGraph)

    TermsPolicyScreen(
        modifier,
        navigateTermsPolicyToTermsPolicyDetailMyInfoGraph,
        navigateTermsPolicyToMyInfoInMyInfoGraph
    )
}