package com.gradation.lift.feature.myInfo.updateInfo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.gradation.lift.feature.my_info.update.data.state.UserDetailUiState
import com.gradation.lift.model.model.user.Gender


@Composable
fun UpdateInfoScreen(
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
                UserDetailUiState.None -> {}
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
fun UpdateInfoScreenPreview() {
    LiftMaterialTheme {
        UpdateInfoScreen(
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