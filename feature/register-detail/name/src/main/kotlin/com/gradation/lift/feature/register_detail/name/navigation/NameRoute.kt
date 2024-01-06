package com.gradation.lift.feature.register_detail.name.navigation

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
import com.gradation.lift.feature.register_detail.name.data.NameScreenState
import com.gradation.lift.feature.register_detail.name.data.NameViewModel
import com.gradation.lift.feature.register_detail.name.data.rememberNameScreenState
import com.gradation.lift.feature.register_detail.name.ui.NameScreen
import com.gradation.lift.navigation.Route


@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun NameRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateToGenderInRegisterDetailGraph: () -> Unit,
    navigateResisterDetailGraphToLoginGraph: () -> Unit,
    viewModel: NameViewModel = hiltViewModel(),
    sharedViewModel: RegisterDetailSharedViewModel = hiltViewModel(remember {
        navController.getBackStackEntry(
            Route.REGISTER_DETAIL_GRAPH_NAME
        )
    }),
    nameScreenState: NameScreenState = rememberNameScreenState(),
) {

    val name: String by sharedViewModel.name.collectAsStateWithLifecycle()
    val nameValidator: Validator by sharedViewModel.nameValidator.collectAsStateWithLifecycle()

    val currentRegisterProgressNumber: Int by sharedViewModel.currentRegisterProgressNumber.collectAsStateWithLifecycle()
    val totalRegisterProgressNumber: Int by sharedViewModel.totalRegisterProgressNumber.collectAsStateWithLifecycle()

    val cancelDialogView: Boolean by sharedViewModel.cancelDialogView.collectAsStateWithLifecycle()

    val updateCurrentRegisterProgressNumber: (Int) -> Unit =
        sharedViewModel.updateCurrentRegisterProgressNumber()
    val updateName: (String) -> Unit = sharedViewModel.updateName()
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


    NameScreen(
        modifier,
        name,
        nameValidator,
        currentRegisterProgressNumber,
        totalRegisterProgressNumber,
        updateCurrentRegisterProgressNumber,
        updateName,
        updateCancelDialogView,
        navigateToGenderInRegisterDetailGraph,
        nameScreenState
    )
}
