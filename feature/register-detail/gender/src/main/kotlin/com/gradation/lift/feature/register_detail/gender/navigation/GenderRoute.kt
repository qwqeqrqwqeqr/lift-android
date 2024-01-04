package com.gradation.lift.feature.register_detail.gender.navigation

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
import com.gradation.lift.feature.registerDetail.common.data.RegisterDetailSharedViewModel
import com.gradation.lift.feature.registerDetail.common.ui.dialog.CancelDialog
import com.gradation.lift.feature.register_detail.gender.data.GenderViewModel
import com.gradation.lift.feature.register_detail.gender.ui.GenderScreen
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.navigation.Router

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun GenderRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateToNameInRegisterDetailGraph: () -> Unit,
    navigateToHeightWeightInRegisterDetailGraph: () -> Unit,
    navigateResisterDetailGraphToLoginGraph: () -> Unit,
    viewModel: GenderViewModel = hiltViewModel(),
    sharedViewModel: RegisterDetailSharedViewModel = hiltViewModel(remember {
        navController.getBackStackEntry(
            Router.REGISTER_DETAIL_GRAPH_NAME
        )
    }),
) {
    val gender: Gender by sharedViewModel.gender.collectAsStateWithLifecycle()
    val name: String by sharedViewModel.name.collectAsStateWithLifecycle()

    val currentRegisterProgressNumber: Int by sharedViewModel.currentRegisterProgressNumber.collectAsStateWithLifecycle()
    val totalRegisterProgressNumber: Int by sharedViewModel.totalRegisterProgressNumber.collectAsStateWithLifecycle()

    val updateGender: (Gender) -> Unit =
        sharedViewModel.updateGender()
    val updateCurrentRegisterProgressNumber: (Int) -> Unit =
        sharedViewModel.updateCurrentRegisterProgressNumber()

    val cancelDialogView: Boolean by sharedViewModel.cancelDialogView.collectAsStateWithLifecycle()
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

    GenderScreen(
        modifier,
        gender,
        name,
        currentRegisterProgressNumber,
        totalRegisterProgressNumber,
        updateGender,
        updateCurrentRegisterProgressNumber,
        updateCancelDialogView,
        navigateToNameInRegisterDetailGraph,
        navigateToHeightWeightInRegisterDetailGraph
    )
}

