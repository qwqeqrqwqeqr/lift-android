package com.gradation.lift.feature.my_info.update

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.gradation.lift.feature.my_info.update.data.state.UserDetailUiState
import com.gradation.lift.model.model.user.Gender

@Composable
fun MyInfoUpdateRoute(
    modifier: Modifier = Modifier,
    navigateUpdateToMyInfoInMyInfoGraph: () -> Unit,
    viewModel: MyInfoUpdateViewModel = hiltViewModel(),
) {

    val userDetailUiState :UserDetailUiState by viewModel.userDetailUiState.collectAsStateWithLifecycle()
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

    MyInfoUpdateScreen(
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

@Composable
fun MyInfoUpdateScreen(
    modifier: Modifier = Modifier,
    userDetailUiState: UserDetailUiState,
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
    navigateUpdateToMyInfoInMyInfoGraph: () -> Unit,
    snackbarHostState: SnackbarHostState,
    focusManager: FocusManager
) {
    Scaffold(
        topBar = {
            LiftBackTopBar(
                title = "내정보 수정",
                onBackClickTopBar = navigateUpdateToMyInfoInMyInfoGraph
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
            when(userDetailUiState){
                UserDetailUiState.Fail -> {}
                UserDetailUiState.Loading -> {}
                UserDetailUiState.Success -> {
                    Column(modifier = modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Column(modifier=modifier.weight(1f)) {
                            NameTextFieldView(modifier, nameText, nameValidator, updateNameText, focusManager)
                            GenderSelectionView(modifier, gender, updateFemale, updateMale)
                            HeightTextFieldView(modifier, heightText, heightValidator, updateHeightText, focusManager)
                            WeightTextFieldView(modifier, weightText, weightValidator, updateWeightText, focusManager)
                        }
                        UpdateView(modifier, updateCondition, updateUserDetail)
                    }
                }
            }

        }

    }
}


@Composable
@Preview
fun MyInfoUpdateScreenPreview() {
    LiftMaterialTheme {
        MyInfoUpdateScreen(
            userDetailUiState = UserDetailUiState.Success,
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
            navigateUpdateToMyInfoInMyInfoGraph = { },
            snackbarHostState = SnackbarHostState(),
            focusManager = LocalFocusManager.current
        )
    }
}