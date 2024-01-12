package com.gradation.lift.feature.myInfo.updateInfo.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.feature.myInfo.updateInfo.data.UpdateInfoViewModel
import com.gradation.lift.feature.myInfo.updateInfo.ui.UpdateInfoScreen
import com.gradation.lift.feature.my_info.update.data.state.UpdateUserDetailState
import com.gradation.lift.feature.my_info.update.data.state.UserDetailUiState
import com.gradation.lift.model.model.user.Gender


@Composable
fun UpdateInfoRoute(
    modifier: Modifier = Modifier,
    navigateUpdateToMyInfoInMyInfoGraph: () -> Unit,
    viewModel: UpdateInfoViewModel = hiltViewModel(),
) {

    val userDetailUiState : UserDetailUiState by viewModel.userDetailUiState.collectAsStateWithLifecycle()
    val nameText: String by viewModel.nameText.collectAsStateWithLifecycle()
    val nameValidator: Validator by viewModel.nameValidator.collectAsStateWithLifecycle()
    val gender: Gender by viewModel.gender.collectAsStateWithLifecycle()
    val heightText: String by viewModel.heightText.collectAsStateWithLifecycle()
    val weightText: String by viewModel.weightText.collectAsStateWithLifecycle()
    val heightValidator: Validator by viewModel.heightValidator.collectAsStateWithLifecycle()
    val weightValidator: Validator by viewModel.weightValidator.collectAsStateWithLifecycle()
    val updateUserDetailState: UpdateUserDetailState by viewModel.updateUserDetailState.collectAsStateWithLifecycle()
    val updateCondition :Boolean by viewModel.updateCondition.collectAsStateWithLifecycle()

    val updateNameText: (String) -> Unit = viewModel.updateNameText()
    val updateHeightText: (String) -> Unit = viewModel.updateHeightText()
    val updateWeightText: (String) -> Unit = viewModel.updateWeightText()
    val updateMale: () -> Unit = viewModel.updateMale()
    val updateFemale: () -> Unit = viewModel.updateFemale()

    val updateUserDetail: () -> Unit = viewModel.updateUserDetail()
    val updateUpdateUserDetailState: (UpdateUserDetailState) -> Unit =
        viewModel.updateUpdateUserDetailState()

    val snackbarHostState: SnackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val focusManager: FocusManager = LocalFocusManager.current

    BackHandler(onBack = { navigateUpdateToMyInfoInMyInfoGraph() })


    when (val updateUserDetailStateResult: UpdateUserDetailState = updateUserDetailState) {
        is UpdateUserDetailState.Fail -> {
            LaunchedEffect(true) {
                snackbarHostState.showSnackbar(
                    message = updateUserDetailStateResult.message, duration = SnackbarDuration.Short
                )
                updateUpdateUserDetailState(UpdateUserDetailState.None)
            }
        }

        UpdateUserDetailState.None -> {}
        UpdateUserDetailState.Success -> {
            LaunchedEffect(true) {
                navigateUpdateToMyInfoInMyInfoGraph()
            }
        }
    }

    UpdateInfoScreen(
        modifier,
        userDetailUiState,
        nameText,
        nameValidator,
        gender,
        heightText,
        weightText,
        heightValidator,
        weightValidator,
        updateCondition,
        updateNameText,
        updateHeightText,
        updateWeightText,
        updateMale,
        updateFemale,
        updateUserDetail,
        navigateUpdateToMyInfoInMyInfoGraph,
        snackbarHostState,
        focusManager
    )
}