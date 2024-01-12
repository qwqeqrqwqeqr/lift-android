package com.gradation.lift.feature.myInfo.myInfo.navigation

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.myInfo.myInfo.data.MyInfoViewModel
import com.gradation.lift.feature.myInfo.myInfo.data.state.MyInfoScreenState
import com.gradation.lift.feature.myInfo.myInfo.data.state.SignOutState
import com.gradation.lift.feature.myInfo.myInfo.data.state.MyInfoUiState
import com.gradation.lift.feature.myInfo.myInfo.data.state.rememberMyInfoScreenState
import com.gradation.lift.feature.myInfo.myInfo.ui.MyInfoScreen


@Composable
fun MyInfoRoute(
    modifier: Modifier = Modifier,
    navigateMyInfoGraphToLoginGraph: () -> Unit,
    navigateMyInfoGraphToNoticeGraph: () -> Unit,
    navigateMyInfoToProfileInMyInfoGraph: () -> Unit,
    viewModel: MyInfoViewModel = hiltViewModel(),
    myInfoScreenState: MyInfoScreenState = rememberMyInfoScreenState(),
) {

    val myInfoUiState: MyInfoUiState by viewModel.myInfoUiState.collectAsStateWithLifecycle()
    val signOutState: SignOutState by viewModel.signOutState.collectAsStateWithLifecycle()

    val updateSignOutState: (SignOutState) -> Unit = viewModel.updateSignOutState()
    val signOut: () -> Unit = viewModel.signOut()


    when (val updateUserDetailStateResult: SignOutState = signOutState) {
        is SignOutState.Fail -> {
            LaunchedEffect(true) {
                myInfoScreenState.snackbarHostState.showSnackbar(
                    message = updateUserDetailStateResult.message, duration = SnackbarDuration.Short
                )
                updateSignOutState(SignOutState.None)
            }
        }

        SignOutState.None -> {}
        SignOutState.Success -> {
            LaunchedEffect(true) { navigateMyInfoGraphToLoginGraph() }
        }
    }

    MyInfoScreen(
        modifier,
        navigateMyInfoGraphToNoticeGraph,
        navigateMyInfoToProfileInMyInfoGraph,
        myInfoUiState,
        myInfoScreenState
    )
}