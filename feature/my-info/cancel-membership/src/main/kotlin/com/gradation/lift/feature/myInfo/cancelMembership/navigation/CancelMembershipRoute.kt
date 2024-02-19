package com.gradation.lift.feature.myInfo.cancelMembership.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.feature.myInfo.cancelMembership.ui.CancelMembershipScreen


@Composable
fun CancelMembershipRoute(
    modifier: Modifier = Modifier,
    navigateCancelMembershipToCancelMembershipConfirmInMyInfoGraph: (String) -> Unit,
    navigateCancelMembershipToProfileInMyInfoGraph: () -> Unit,
) {
    BackHandler(onBack = {})

    CancelMembershipScreen(
        modifier,
    )
}