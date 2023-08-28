package com.gradation.lift.feature.my_info.update

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftBackTopBar
import com.gradation.lift.designsystem.component.LiftErrorSnackBar
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.my_info.update.data.component.GenderSelectionView
import com.gradation.lift.feature.my_info.update.data.component.HeightTextFieldView
import com.gradation.lift.feature.my_info.update.data.component.NameTextFieldView
import com.gradation.lift.feature.my_info.update.data.component.UpdateView
import com.gradation.lift.feature.my_info.update.data.component.WeightTextFieldView
import com.gradation.lift.feature.my_info.update.data.state.UpdateUserDetailState
import com.gradation.lift.model.model.user.Gender

@Composable
fun MyInfoUpdateRoute(
    modifier: Modifier = Modifier,
    navigateUpdateToMyInfo: () -> Unit,
    viewModel: MyInfoUpdateViewModel = hiltViewModel(),
) {


    val nameText: String by viewModel.nameState.nameText.collectAsStateWithLifecycle()
    val nameValidator: Validator by viewModel.nameState.nameValidator.collectAsStateWithLifecycle()
    val gender: Gender by viewModel.genderState.gender.collectAsStateWithLifecycle()
    val heightText: String by viewModel.heightWeightState.heightText.collectAsStateWithLifecycle()
    val weightText: String by viewModel.heightWeightState.weightText.collectAsStateWithLifecycle()
    val heightValidator: Validator by viewModel.heightWeightState.heightValidator.collectAsStateWithLifecycle()
    val weightValidator: Validator by viewModel.heightWeightState.weightValidator.collectAsStateWithLifecycle()
    val updateUserDetailState: UpdateUserDetailState by viewModel.updateUserDetailState.collectAsStateWithLifecycle()
    val updateCondition :Boolean by viewModel.updateCondition.collectAsStateWithLifecycle()

    val updateNameText: (String) -> Unit = viewModel.nameState.updateNameText()
    val updateHeightText: (String) -> Unit = viewModel.heightWeightState.updateHeightText()
    val updateWeightText: (String) -> Unit = viewModel.heightWeightState.updateWeightText()
    val updateMale: () -> Unit = viewModel.genderState.updateMale()
    val updateFemale: () -> Unit = viewModel.genderState.updateFemale()

    val updateUserDetail: () -> Unit = viewModel.updateUserDetail()
    val updateUpdateUserDetailState: (UpdateUserDetailState) -> Unit =
        viewModel.updateUpdateUserDetailState()

    val snackbarHostState: SnackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val focusManager: FocusManager = LocalFocusManager.current

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

    MyInfoUpdateScreen(
        modifier,
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
        navigateUpdateToMyInfo,
        snackbarHostState,
        focusManager
    )
}

@Composable
fun MyInfoUpdateScreen(
    modifier: Modifier = Modifier,
    nameText: String,
    nameValidator: Validator,
    gender: Gender,
    heightText: String,
    weightText: String,
    heightValidator: Validator,
    weightValidator: Validator,
    updateCondition: Boolean,
    updateNameText: (String) -> Unit,
    updateHeightText: (String) -> Unit,
    updateWeightText: (String) -> Unit,
    updateMale: () -> Unit,
    updateFemale: () -> Unit,
    updateUserDetail: () -> Unit,
    navigateUpdateToMyInfo: () -> Unit,
    snackbarHostState: SnackbarHostState,
    focusManager: FocusManager
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "내정보 수정",
                onBackClickTopBar = navigateUpdateToMyInfo
            )
        },
        snackbarHost = {
            LiftErrorSnackBar(
                modifier = modifier,
                snackbarHostState = snackbarHostState
            )
        }
    ) { padding ->
        Surface(
            modifier = modifier
                .padding(padding)
                .fillMaxSize(),
            color = LiftTheme.colorScheme.no5,
        ) {
            Column(modifier = modifier.padding(16.dp)) {
                Column(modifier=modifier.weight(1f)) {
                    Spacer(modifier = modifier.padding(16.dp))
                    NameTextFieldView(modifier, nameText, nameValidator, updateNameText, focusManager)
                    Spacer(modifier = modifier.padding(16.dp))
                    GenderSelectionView(modifier, gender, updateFemale, updateMale)
                    Spacer(modifier = modifier.padding(16.dp))
                    HeightTextFieldView(modifier, heightText, heightValidator, updateHeightText, focusManager)
                    Spacer(modifier = modifier.padding(16.dp))
                    WeightTextFieldView(modifier, weightText, weightValidator, updateWeightText, focusManager)
                }
                UpdateView(modifier, updateCondition, updateUserDetail)
            }
        }

    }
}


@Composable
@Preview
fun MyInfoUpdateScreenPreview() {
    LiftMaterialTheme {
        MyInfoUpdateScreen(
            nameText = "",
            nameValidator = Validator(false, ""),
            gender = Gender.Male(),
            heightText = "",
            weightText = "",
            heightValidator = Validator(false, ""),
            weightValidator = Validator(false, ""),
            updateCondition = false,
            updateNameText = {},
            updateHeightText = {},
            updateWeightText = {},
            updateMale = { },
            updateFemale = { },
            updateUserDetail = { },
            navigateUpdateToMyInfo = { },
            snackbarHostState = SnackbarHostState(),
            focusManager = LocalFocusManager.current
        )
    }
}