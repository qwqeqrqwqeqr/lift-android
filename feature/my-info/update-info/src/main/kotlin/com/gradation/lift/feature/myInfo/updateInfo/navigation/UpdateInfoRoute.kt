package com.gradation.lift.feature.myInfo.updateInfo.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.feature.myInfo.updateInfo.data.UpdateInfoViewModel
import com.gradation.lift.feature.myInfo.updateInfo.data.state.UpdateInfoScreenState
import com.gradation.lift.feature.myInfo.updateInfo.data.state.UpdateUserDetailInfoState
import com.gradation.lift.feature.myInfo.updateInfo.data.state.rememberUpdateInfoScreenState
import com.gradation.lift.feature.myInfo.updateInfo.ui.UpdateInfoScreen
import com.gradation.lift.model.model.user.Gender


@Composable
fun UpdateInfoRoute(
    modifier: Modifier = Modifier,
    navigateUpdateToMyInfoInMyInfoGraph: () -> Unit,
    viewModel: UpdateInfoViewModel = hiltViewModel(),
    updateInfoScreenState: UpdateInfoScreenState = rememberUpdateInfoScreenState(),
) {

    val gender: Gender by viewModel.gender.collectAsStateWithLifecycle()
    val heightText: String by viewModel.heightText.collectAsStateWithLifecycle()
    val weightText: String by viewModel.weightText.collectAsStateWithLifecycle()
    val heightValidator: Validator by viewModel.heightValidator.collectAsStateWithLifecycle()
    val weightValidator: Validator by viewModel.weightValidator.collectAsStateWithLifecycle()

    val isChangedInfo : Boolean by viewModel.isChangedInfo.collectAsStateWithLifecycle()

    val updateUserDetailInfoState: UpdateUserDetailInfoState by viewModel.updateUserDetailInfoState.collectAsStateWithLifecycle()

    val updateHeightText: (String) -> Unit = viewModel.updateHeightText()
    val updateWeightText: (String) -> Unit = viewModel.updateWeightText()
    val updateGender: (Gender) -> Unit = viewModel.updateGender()

    val updateUserDetailInfo: () -> Unit = viewModel.updateUserDetailInfo()
    val updateUpdateUserDetailState: (UpdateUserDetailInfoState) -> Unit =
        viewModel.updateUpdateUserDetailInfoState()



    BackHandler(onBack = navigateUpdateToMyInfoInMyInfoGraph)


    when (val updateUserDetailStateResult: UpdateUserDetailInfoState = updateUserDetailInfoState) {
        is UpdateUserDetailInfoState.Fail -> {
            LaunchedEffect(true) {
                updateInfoScreenState.snackbarHostState.showSnackbar(
                    message = updateUserDetailStateResult.message, duration = SnackbarDuration.Short
                )
                updateUpdateUserDetailState(UpdateUserDetailInfoState.None)
            }
        }

        UpdateUserDetailInfoState.None -> {}
        UpdateUserDetailInfoState.Success -> {
            LaunchedEffect(true) { navigateUpdateToMyInfoInMyInfoGraph() }
        }
    }

    UpdateInfoScreen(
        modifier,
        gender,
        heightText,
        weightText,
        heightValidator,
        weightValidator,
        isChangedInfo,
        updateHeightText,
        updateWeightText,
        updateGender,
        updateUserDetailInfo,
        navigateUpdateToMyInfoInMyInfoGraph,
        updateInfoScreenState
    )
}