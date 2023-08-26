package com.gradation.lift.feature.my_info.update

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.my_info.update.data.state.UpdateUserDetailState
import com.gradation.lift.model.model.user.Gender

@Composable
fun MyInfoUpdateRoute(
    modifier: Modifier = Modifier,
    navigateUpdateToMyInfo: () -> Unit,
    viewModel: MyInfoUpdateViewModel = hiltViewModel(),
) {

    val updateUserDetailState: UpdateUserDetailState by viewModel.updateUserDetailState.collectAsStateWithLifecycle()


    val nameText: String by viewModel.nameState.nameText.collectAsStateWithLifecycle()
    val nameValidator: Validator by viewModel.nameState.nameValidator.collectAsStateWithLifecycle()
    val gender: Gender by viewModel.genderState.gender.collectAsStateWithLifecycle()
    val heightText: String by viewModel.heightWeightState.heightText.collectAsStateWithLifecycle()
    val weightText: String by viewModel.heightWeightState.weightText.collectAsStateWithLifecycle()
    val heightValidator: Validator by viewModel.heightWeightState.heightValidator.collectAsStateWithLifecycle()
    val weightValidator: Validator by viewModel.heightWeightState.weightValidator.collectAsStateWithLifecycle()


    val updateNameText: (String) -> Unit = viewModel.nameState.updateNameText()
    val updateHeightText: (String) -> Unit = viewModel.heightWeightState.updateHeightText()
    val updateWeightText: (String) -> Unit = viewModel.heightWeightState.updateWeightText()
    val updateMale: () -> Unit = viewModel.genderState.updateMale()
    val updateFemale: () -> Unit = viewModel.genderState.updateFemale()

    val updateUserDetail: () -> Unit = viewModel.updateUserDetail()
    val updateUpdateUserDetailState: (UpdateUserDetailState) -> Unit =
        viewModel.updateUpdateUserDetailState()

    val snackbarHostState: SnackbarHostState by remember { mutableStateOf(SnackbarHostState()) }

    BackHandler(onBack = { navigateUpdateToMyInfo() })


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
                navigateUpdateToMyInfo()
            }
        }
    }
}

@Composable
fun MyInfoUpdateScreen(modifier: Modifier = Modifier) {
    Surface(color = LiftTheme.colorScheme.no5, modifier = modifier.fillMaxSize()) {

    }
}


@Composable
@Preview
fun MyInfoUpdateScreenPreview() {
    LiftMaterialTheme {
        MyInfoUpdateScreen(

        )
    }
}