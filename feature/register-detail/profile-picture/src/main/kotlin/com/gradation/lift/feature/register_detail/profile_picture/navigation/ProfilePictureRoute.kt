package com.gradation.lift.feature.register_detail.profile_picture.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.registerDetail.common.data.CreateUserDetailState
import com.gradation.lift.feature.registerDetail.common.data.RegisterDetailSharedViewModel
import com.gradation.lift.feature.registerDetail.common.ui.dialog.CancelDialog
import com.gradation.lift.feature.registerDetail.common.ui.dialog.CompleteDialog
import com.gradation.lift.feature.register_detail.profile_picture.data.ProfilePictureScreenState
import com.gradation.lift.feature.register_detail.profile_picture.data.ProfilePictureViewModel
import com.gradation.lift.feature.register_detail.profile_picture.data.rememberProfilePictureScreenState
import com.gradation.lift.feature.register_detail.profile_picture.ui.ProfilePictureScreen
import com.gradation.lift.model.model.picture.UserProfilePicture
import com.gradation.lift.navigation.Route

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
internal fun RegisterDetailProfilePictureRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateResisterDetailGraphToLoginGraph: () -> Unit,
    navigateRegisterDetailGraphToHomeGraph: () -> Unit,
    navigateToNameInRegisterDetailGraph: () -> Unit,
    navigateToGenderInRegisterDetailGraph: () -> Unit,
    navigateToHeightWeightInRegisterDetailGraph: () -> Unit,
    viewModel: ProfilePictureViewModel = hiltViewModel(),
    sharedViewModel: RegisterDetailSharedViewModel = hiltViewModel(remember {
        navController.getBackStackEntry(
            Route.REGISTER_DETAIL_GRAPH_NAME
        )
    }),
    profilePictureScreenState: ProfilePictureScreenState = rememberProfilePictureScreenState(),
) {


    val profilePictureList: List<UserProfilePicture> by viewModel.profilePictureList.collectAsStateWithLifecycle()
    val profilePicture: String by sharedViewModel.profilePicture.collectAsStateWithLifecycle()
    val currentRegisterProgressNumber: Int by sharedViewModel.currentRegisterProgressNumber.collectAsStateWithLifecycle()
    val totalRegisterProgressNumber: Int by sharedViewModel.totalRegisterProgressNumber.collectAsStateWithLifecycle()


    val createUserDetailState: CreateUserDetailState by sharedViewModel.createUserDetailState.collectAsStateWithLifecycle()
    val cancelDialogView: Boolean by sharedViewModel.cancelDialogView.collectAsStateWithLifecycle()
    val completeDialogView: Boolean by sharedViewModel.completeDialogView.collectAsStateWithLifecycle()


    val updateProfilePicture: (String) -> Unit = sharedViewModel.updateProfilePicture()

    val updateCancelDialogView: (Boolean) -> Unit = sharedViewModel.updateCancelDialogView()
    val updateCompleteDialogView: (Boolean) -> Unit = sharedViewModel.updateCompleteDialogView()

    val updateCurrentRegisterProgressNumber: (Int) -> Unit =
        sharedViewModel.updateCurrentRegisterProgressNumber()
    val updateCreateUserDetailState: (CreateUserDetailState) -> Unit =
        sharedViewModel.updateCreateUserDetailState()

    val createUserDetail: () -> Unit = sharedViewModel.createUserDetail()
    val signOut: () -> Unit = sharedViewModel.signOut()


    BackHandler(onBack = { updateCancelDialogView(true) })

    AnimatedVisibility(visible = cancelDialogView) {
        CancelDialog(modifier = modifier, onClickDialogCancelButton = {
            signOut()
            navigateResisterDetailGraphToLoginGraph()
        }, onClickDialogDismissButton = { updateCancelDialogView(false) })
    }
    AnimatedVisibility(visible = completeDialogView) {
        CompleteDialog(
            modifier = modifier,
            onComplete = navigateRegisterDetailGraphToHomeGraph,
        )
    }


    when (val createUserDetailResult = createUserDetailState) {
        is CreateUserDetailState.Fail -> {
            LaunchedEffect(true) {
                profilePictureScreenState.snackbarHostState.showSnackbar(
                    message = createUserDetailResult.message, duration = SnackbarDuration.Short
                )
                updateCreateUserDetailState(CreateUserDetailState.None)
            }
        }

        CreateUserDetailState.None -> {}
        CreateUserDetailState.Success -> {
            LaunchedEffect(true) {
                updateCompleteDialogView(true)
                updateCreateUserDetailState(CreateUserDetailState.None)
            }
        }
    }

    ProfilePictureScreen(
        modifier,
        profilePictureList,
        profilePicture,
        currentRegisterProgressNumber,
        totalRegisterProgressNumber,
        updateProfilePicture,
        updateCancelDialogView,
        updateCurrentRegisterProgressNumber,
        createUserDetail,
        navigateToNameInRegisterDetailGraph,
        navigateToGenderInRegisterDetailGraph,
        navigateToHeightWeightInRegisterDetailGraph,
        profilePictureScreenState
    )

}