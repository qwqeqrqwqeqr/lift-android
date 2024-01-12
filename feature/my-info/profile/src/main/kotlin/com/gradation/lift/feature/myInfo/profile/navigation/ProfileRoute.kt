package com.gradation.lift.feature.myInfo.profile.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.myInfo.profile.ui.ProfileScreen
import com.gradation.lift.feature.myInfo.profile.data.ProfileViewModel
import com.gradation.lift.feature.myInfo.profile.data.state.ProfileScreenState
import com.gradation.lift.feature.myInfo.profile.data.state.ProfileUiState
import com.gradation.lift.feature.myInfo.profile.data.state.SignOutState
import com.gradation.lift.feature.myInfo.profile.data.state.rememberProfileScreenState
import com.gradation.lift.feature.myInfo.profile.ui.dialog.SignOutDialog

@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier,
    navigateProfileToMyInfoInMyInfoGraph: () -> Unit,
    navigateProfileToUpdateNameInMyInfoGraph: () -> Unit,
    navigateProfileToUpdateInfoInMyInfoGraph: () -> Unit,
    navigateProfileToUpdateProfilePictureInMyInfoGraph: () -> Unit,
    navigateMyInfoGraphToLoginGraph: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
    profileScreenState: ProfileScreenState = rememberProfileScreenState(),
) {

    val profileUiState: ProfileUiState by viewModel.profileUiState.collectAsStateWithLifecycle()
    val signOutState: SignOutState by viewModel.signOutState.collectAsStateWithLifecycle()

    val updateSignOutState: (SignOutState) -> Unit = viewModel.updateSignOutState()
    val signOut: () -> Unit = viewModel.signOut()


    when (val signOutStateResult: SignOutState =
        signOutState) {
        is SignOutState.Fail -> {
            LaunchedEffect(true) {
                profileScreenState.snackbarHostState.showSnackbar(
                    message = signOutStateResult.message, duration = SnackbarDuration.Short
                )
                updateSignOutState(SignOutState.None)
            }
        }

        SignOutState.None -> {}
        SignOutState.Success -> {
            LaunchedEffect(true) { navigateMyInfoGraphToLoginGraph() }
        }
    }

    BackHandler(onBack = navigateProfileToMyInfoInMyInfoGraph)

    AnimatedVisibility(profileScreenState.signOutDialogView) {
        SignOutDialog(
            modifier = modifier,
            onClickDialogSignOutButton = { signOut() },
            onClickDialogDismissButton = { profileScreenState.updateSignOutDialogView(false) },
        )
    }


    ProfileScreen(
        modifier,
        navigateProfileToMyInfoInMyInfoGraph,
        navigateProfileToUpdateNameInMyInfoGraph,
        navigateProfileToUpdateInfoInMyInfoGraph,
        navigateProfileToUpdateProfilePictureInMyInfoGraph,
        profileUiState,
        profileScreenState
    )
}

