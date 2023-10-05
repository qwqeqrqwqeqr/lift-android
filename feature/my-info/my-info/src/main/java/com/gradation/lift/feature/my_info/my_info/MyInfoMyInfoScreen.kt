package com.gradation.lift.feature.my_info.my_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.designsystem.component.LiftErrorSnackBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.my_info.my_info.component.MyInfoListView
import com.gradation.lift.feature.my_info.my_info.component.ProfileView
import com.gradation.lift.feature.my_info.my_info.data.MyInfoMyInfoViewModel
import com.gradation.lift.feature.my_info.my_info.data.state.SignOutState
import com.gradation.lift.feature.my_info.my_info.data.state.UserDetailUiState
import com.gradation.lift.model.utils.ModelDataGenerator.User.userDetailModel

@Composable
fun MyInfoMyInfoRoute(
    modifier: Modifier = Modifier,
    versionName: String,
    navigateMyInfoGraphToLoginGraph: () -> Unit,
    navigateMyInfoToUpdateProfileInMyInfoGraph: () -> Unit,
    navigateMyInfoToUpdateInMyInfoGraph: () -> Unit,
    navigateMyInfoGraphToNotificationGraph: () -> Unit,
    navigateMyInfoGraphToBadgeGraph: () -> Unit,
    viewModel: MyInfoMyInfoViewModel = hiltViewModel(),
) {

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

    MyInfoMyInfoScreen(
        modifier,
        versionName,
        workCount,
        userDetailUiState,
        signOut,
        navigateMyInfoToUpdateProfileInMyInfoGraph,
        navigateMyInfoToUpdateInMyInfoGraph,
        navigateMyInfoGraphToNotificationGraph,
        navigateMyInfoGraphToBadgeGraph,
        snackbarHostState
    )
}

@Composable
fun MyInfoMyInfoScreen(
    modifier: Modifier = Modifier,
    versionName: String,
    workCount: Int,
    userDetailUiState: UserDetailUiState,
    signOut: () -> Unit,
    navigateMyInfoToUpdateProfileInMyInfoGraph: () -> Unit,
    navigateMyInfoToUpdateInMyInfoGraph: () -> Unit,
    navigateMyInfoGraphToNotificationGraph: () -> Unit,
    navigateMyInfoGraphToBadgeGraph: () -> Unit,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        snackbarHost = {
            LiftErrorSnackBar(
                modifier = modifier,
                snackbarHostState = snackbarHostState
            )
        }
    ) { padding ->
        Surface(
            modifier = modifier.padding(padding),
            color = LiftTheme.colorScheme.no17,
        ) {
            Column(modifier = modifier.fillMaxSize()) {
                ProfileView(
                    modifier,
                    userDetailUiState,
                    workCount,
                    signOut,
                    navigateMyInfoToUpdateProfileInMyInfoGraph,
                    navigateMyInfoGraphToNotificationGraph,
                navigateMyInfoGraphToBadgeGraph
                )
                Spacer(modifier = modifier.padding(9.dp))
                MyInfoListView(modifier, versionName, navigateMyInfoToUpdateInMyInfoGraph)

            }
        }
    }
}


@Preview
@Composable
fun MyInfoMyInfoScreenPreview() {
    LiftMaterialTheme {
        MyInfoMyInfoScreen(
            workCount = 13,
            versionName = "1.0.0",
            userDetailUiState = UserDetailUiState.Success(userDetailModel),
            signOut = {},
            navigateMyInfoToUpdateProfileInMyInfoGraph = {},
            navigateMyInfoToUpdateInMyInfoGraph = {},
            navigateMyInfoGraphToNotificationGraph={},
            navigateMyInfoGraphToBadgeGraph={},
            snackbarHostState = SnackbarHostState()
        )
    }
}