package com.gradation.lift.feature.myInfo.cancelMembershipConfirm.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.feature.myInfo.cancelMembershipConfirm.data.CancelMembershipConfirmViewModel
import com.gradation.lift.feature.myInfo.cancelMembershipConfirm.data.DeleteUserState
import com.gradation.lift.feature.myInfo.cancelMembershipConfirm.ui.CancelMembershipConfirmScreen
import com.gradation.lift.ui.extensions.showImmediatelySnackbar
import com.gradation.lift.ui.provider.LocalAppScope
import com.gradation.lift.ui.provider.LocalInfoSnackbarHostState
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CancelMembershipConfirmRoute(
    modifier: Modifier = Modifier,
    navigateCancelMembershipConfirmToCancelMembershipInMyInfoGraph: () -> Unit,
    navigateMyInfoGraphToLoginGraph: () -> Unit,
    viewModel: CancelMembershipConfirmViewModel = hiltViewModel(),
) {

    val name: String by viewModel.name.collectAsStateWithLifecycle()
    val deleteUserState: DeleteUserState by viewModel.deleteUserState.collectAsStateWithLifecycle()

    val updateDeleteUserState: (DeleteUserState) -> Unit = viewModel.updateDeleteUserState
    val deleteUser: () -> Unit = viewModel.deleteUser

    val snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
    val infoAppSnackbarHostState = LocalInfoSnackbarHostState.current
    val appScope = LocalAppScope.current


    LaunchedEffect(deleteUserState) {
        when (val result = deleteUserState) {
            is DeleteUserState.Fail -> {
                snackbarHostState.showImmediatelySnackbar(result.message)
                updateDeleteUserState(DeleteUserState.None)
            }

            DeleteUserState.None -> {}
            DeleteUserState.Success -> {
                appScope.launch {
                    infoAppSnackbarHostState.showImmediatelySnackbar("리프트 탈퇴가 완료됐습니다. 다음에 다시 만나요. \uD83D\uDE01")
                }
                navigateMyInfoGraphToLoginGraph()
            }
        }
    }


    BackHandler(onBack = navigateCancelMembershipConfirmToCancelMembershipInMyInfoGraph)

    CancelMembershipConfirmScreen(
        modifier,
        name,
        deleteUser,
        snackbarHostState,
        navigateCancelMembershipConfirmToCancelMembershipInMyInfoGraph
    )
}