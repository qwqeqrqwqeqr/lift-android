package com.gradation.lift.myInfo.termsPolicyDetail.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.myInfo.termsPolicyDetail.ui.TermsPolicyDetailScreen


@Composable
fun TermsPolicyDetailRoute(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    navigateTermsPolicyDetailToTermsPolicyMyInfoGraph: () -> Unit,
) {
    BackHandler(onBack = navigateTermsPolicyDetailToTermsPolicyMyInfoGraph)

    val scrollState: ScrollState = rememberScrollState()

    TermsPolicyDetailScreen(
        modifier, title, content, navigateTermsPolicyDetailToTermsPolicyMyInfoGraph, scrollState
    )
}