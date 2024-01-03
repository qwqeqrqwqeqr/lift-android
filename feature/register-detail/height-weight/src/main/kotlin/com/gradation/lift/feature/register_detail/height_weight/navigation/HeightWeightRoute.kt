package com.gradation.lift.feature.register_detail.height_weight.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.feature.registerDetail.common.data.RegisterDetailSharedViewModel
import com.gradation.lift.feature.registerDetail.common.ui.dialog.CancelDialog
import com.gradation.lift.feature.register_detail.height_weight.data.HeightWeightScreenState
import com.gradation.lift.feature.register_detail.height_weight.data.HeightWeightViewModel
import com.gradation.lift.feature.register_detail.height_weight.data.rememberHeightWeightScreenState
import com.gradation.lift.feature.register_detail.height_weight.ui.HeightWeightScreen
import com.gradation.lift.navigation.Router

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
internal fun HeightWeightRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateResisterDetailGraphToLoginGraph: () -> Unit,
    navigateToNameInRegisterDetailGraph: () -> Unit,
    navigateToGenderInRegisterDetailGraph: () -> Unit,
    navigateToProfilePictureInRegisterDetailGraph: () -> Unit,
    viewModel: HeightWeightViewModel = hiltViewModel(),
    sharedViewModel: RegisterDetailSharedViewModel = hiltViewModel(remember {
        navController.getBackStackEntry(
            Router.REGISTER_DETAIL_GRAPH_NAME
        )
    }),
    heightWeightScreenState: HeightWeightScreenState = rememberHeightWeightScreenState(),
) {


    val height: String by sharedViewModel.height.collectAsStateWithLifecycle()
    val weight: String by sharedViewModel.weight.collectAsStateWithLifecycle()
    val heightValidator: Validator by sharedViewModel.heightValidator.collectAsStateWithLifecycle()
    val weightValidator: Validator by sharedViewModel.weightValidator.collectAsStateWithLifecycle()

    val currentRegisterProgressNumber: Int by sharedViewModel.currentRegisterProgressNumber.collectAsStateWithLifecycle()
    val totalRegisterProgressNumber: Int by sharedViewModel.totalRegisterProgressNumber.collectAsStateWithLifecycle()
    val cancelDialogView: Boolean by sharedViewModel.cancelDialogView.collectAsStateWithLifecycle()

    val updateCurrentRegisterProgressNumber: (Int) -> Unit =
        sharedViewModel.updateCurrentRegisterProgressNumber()

    val updateHeight: (String) -> Unit = sharedViewModel.updateHeight()
    val updateWeight: (String) -> Unit = sharedViewModel.updateWeight()
    val updateCancelDialogView: (Boolean) -> Unit = sharedViewModel.updateCancelDialogView()

    val signOut: () -> Unit = sharedViewModel.signOut()


    AnimatedVisibility(visible = cancelDialogView) {
        CancelDialog(
            modifier = modifier,
            onClickDialogCancelButton = {
                signOut()
                navigateResisterDetailGraphToLoginGraph()
            },
            onClickDialogDismissButton = { updateCancelDialogView(false) }
        )
    }
    BackHandler(onBack = { updateCancelDialogView(true) })


    HeightWeightScreen(
        modifier,
        height,
        weight,
        heightValidator,
        weightValidator,
        currentRegisterProgressNumber,
        totalRegisterProgressNumber,
        updateHeight,
        updateWeight,
        updateCancelDialogView,
        updateCurrentRegisterProgressNumber,
        navigateToNameInRegisterDetailGraph,
        navigateToGenderInRegisterDetailGraph,
        navigateToProfilePictureInRegisterDetailGraph,
        heightWeightScreenState
    )
}