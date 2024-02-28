package com.gradation.lift.feature.myInfo.cancelMembership.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.myInfo.cancelMembership.data.CancelMembershipViewModel
import com.gradation.lift.feature.myInfo.cancelMembership.ui.CancelMembershipScreen
import com.gradation.lift.feature.myInfo.cancelMembership.ui.CancelReasonBottomSheet


@Composable
fun CancelMembershipRoute(
    modifier: Modifier = Modifier,
    navigateCancelMembershipToCancelMembershipConfirmInMyInfoGraph: (String) -> Unit,
    navigateCancelMembershipToProfileInMyInfoGraph: () -> Unit,
    viewModel: CancelMembershipViewModel = hiltViewModel(),
) {

    val selectedCancelReasonValue: String by viewModel.selectedCancelReasonValue.collectAsStateWithLifecycle()
    val etcText: String by viewModel.etcText.collectAsStateWithLifecycle()
    val selectedFlag: Boolean by viewModel.selectedFlag.collectAsStateWithLifecycle()
    val etcFlag: Boolean by viewModel.etcFlag.collectAsStateWithLifecycle()
    val bottomSheetView: Boolean by viewModel.bottomSheetView.collectAsStateWithLifecycle()


    val updateEtcText: (String) -> Unit = viewModel.updateEtcText
    val updateSelectedCancelReasonValue: (String) -> Unit =
        viewModel.updateSelectedCancelReasonValue
    val updateBottomSheetView: (Boolean) -> Unit = viewModel.updateBottomSheetView

    BackHandler(onBack = navigateCancelMembershipToProfileInMyInfoGraph)

    AnimatedVisibility(visible = bottomSheetView) {
        CancelReasonBottomSheet(
            modifier,
            selectedCancelReasonValue,
            updateSelectedCancelReasonValue,
            updateBottomSheetView,
        )
    }

    CancelMembershipScreen(
        modifier,
        selectedCancelReasonValue,
        etcText,
        selectedFlag,
        etcFlag,
        updateEtcText,
        updateBottomSheetView,
        navigateCancelMembershipToProfileInMyInfoGraph,
        navigateCancelMembershipToCancelMembershipConfirmInMyInfoGraph
    )
}