package com.gradation.lift.feature.login.complete

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CompleteRoute(
    navigateCompleteToSignInInLoginGraph: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CompleteScreen(
        modifier, navigateCompleteToSignInInLoginGraph
    )
}
