package com.gradation.lift.feature.myInfo.myInfo.navigation

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.myInfo.myInfo.data.MyInfoViewModel
import com.gradation.lift.feature.myInfo.myInfo.data.state.SignOutState
import com.gradation.lift.feature.myInfo.myInfo.data.state.UserDetailUiState
import com.gradation.lift.feature.myInfo.myInfo.ui.MyInfoScreen


@Composable
fun MyInfoRoute(
    modifier: Modifier = Modifier,
    versionName: String,
    navigateMyInfoGraphToLoginGraph: () -> Unit,
    navigateProfileToUpdateProfilePictureInMyInfoGraph: () -> Unit,
    navigateMyInfoToUpdateInMyInfoGraph: () -> Unit,
    navigateMyInfoGraphToNotificationGraph: () -> Unit,
    navigateMyInfoGraphToBadgeGraph: () -> Unit,
    viewModel: MyInfoViewModel = hiltViewModel(),
) {

    val badgeCount: Int by viewModel.badgeCount.collectAsStateWithLifecycle()
    val workCount: Int by viewModel.workCount.collectAsStateWithLifecycle()
    val userDetailUiState: UserDetailUiState by viewModel.userDetailUiState.collectAsStateWithLifecycle()
    val signOutState: SignOutState by viewModel.signOutState.collectAsStateWithLifecycle()

    val updateUpdateUserDetailState: (SignOutState) -> Unit =
        viewModel.updateUpdateUserDetailState()
    val signOut: () -> Unit = viewModel.signOut()


    val snackbarHostState: SnackbarHostState by remember { mutableStateOf(SnackbarHostState()) }


    when (val updateUserDetailStateResult: SignOutState = signOutState) {
        is SignOutState.Fail -> {
            LaunchedEffect(true) {
                snackbarHostState.showSnackbar(
                    message = updateUserDetailStateResult.message, duration = SnackbarDuration.Short
                )
                updateUpdateUserDetailState(SignOutState.None)
            }
        }

        SignOutState.None -> {}
        SignOutState.Success -> {
            LaunchedEffect(true) {
                navigateMyInfoGraphToLoginGraph()
            }
        }
    }

    MyInfoScreen(
        modifier,
        versionName,
        badgeCount,
        workCount,
        userDetailUiState,
        signOut,
        navigateProfileToUpdateProfilePictureInMyInfoGraph,
        navigateMyInfoToUpdateInMyInfoGraph,
        navigateMyInfoGraphToNotificationGraph,
        navigateMyInfoGraphToBadgeGraph,
        snackbarHostState
    )
}