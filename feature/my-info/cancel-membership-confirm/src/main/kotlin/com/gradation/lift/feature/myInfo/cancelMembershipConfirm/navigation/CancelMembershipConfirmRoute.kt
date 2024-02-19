package com.gradation.lift.feature.myInfo.cancelMembershipConfirm.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.feature.myInfo.cancelMembershipConfirm.ui.CancelMembershipConfirmScreen


@Composable
fun CancelMembershipConfirmRoute(
    modifier: Modifier = Modifier,
    navigateCancelMembershipConfirmToCancelMembershipInMyInfoGraph: () -> Unit,
    navigateMyInfoGraphToLoginGraph: () -> Unit,
) {
    BackHandler(onBack = {})

    CancelMembershipConfirmScreen(
        modifier,
    )
}