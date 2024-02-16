package com.gradation.lift.feature.myInfo.myInfo.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.gradation.lift.feature.myInfo.myInfo.data.MyInfoViewModel
import com.gradation.lift.feature.myInfo.myInfo.data.state.MyInfoScreenState
import com.gradation.lift.feature.myInfo.myInfo.data.state.MyInfoUiState
import com.gradation.lift.feature.myInfo.myInfo.data.state.rememberMyInfoScreenState
import com.gradation.lift.feature.myInfo.myInfo.ui.MyInfoScreen
import com.gradation.lift.ui.extensions.showImmediatelySnackbar
import com.gradation.lift.ui.extensions.showToast


@Composable
fun MyInfoRoute(
    modifier: Modifier = Modifier,
    navController: NavController,
    navigateMyInfoGraphToNoticeGraph: () -> Unit,
    navigateMyInfoToProfileInMyInfoGraph: () -> Unit,
    navigateMyInfoToTermsPolicyInMyInfoGraph: () -> Unit,
    navigateToOssScreen: () -> Unit,
    viewModel: MyInfoViewModel = hiltViewModel(),
    myInfoScreenState: MyInfoScreenState = rememberMyInfoScreenState(),
) {

    val myInfoUiState: MyInfoUiState by viewModel.myInfoUiState.collectAsStateWithLifecycle()

    LaunchedEffect(myInfoUiState) {
        when (val result = myInfoUiState) {
            is MyInfoUiState.Fail -> {
                myInfoScreenState.snackbarHostState.showImmediatelySnackbar(result.message)
            }

            MyInfoUiState.Loading -> {}
            is MyInfoUiState.Success -> {}
        }
    }

    BackHandler(onBack = {
        if (System.currentTimeMillis() - myInfoScreenState.terminateWaitTime.value >= 1500) {
            myInfoScreenState.terminateWaitTime.value = System.currentTimeMillis()
            showToast(myInfoScreenState.context, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.")
        } else {
            navController.popBackStack()
        }
    })

    MyInfoScreen(
        modifier,
        navigateMyInfoGraphToNoticeGraph,
        navigateMyInfoToProfileInMyInfoGraph,
        navigateMyInfoToTermsPolicyInMyInfoGraph,
        navigateToOssScreen,
        myInfoUiState,
        myInfoScreenState
    )
}