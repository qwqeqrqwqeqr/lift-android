package com.gradation.lift.feature.myInfo.updateInfo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.snackbar.LiftSnackBar
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.myInfo.updateInfo.data.state.UpdateInfoScreenState
import com.gradation.lift.feature.myInfo.updateInfo.ui.component.GenderSelectionView
import com.gradation.lift.feature.myInfo.updateInfo.ui.component.HeightTextFieldView
import com.gradation.lift.feature.myInfo.updateInfo.ui.component.WeightTextFieldView
import com.gradation.lift.model.model.user.Gender
import com.gradation.lift.ui.extensions.focusClearManager


@Composable
fun UpdateInfoScreen(
    modifier: Modifier,
    gender: Gender,
    heightText: String,
    weightText: String,
    heightValidator: Validator,
    weightValidator: Validator,
    isChangedInfo: Boolean,
    updateHeightText: (String) -> Unit,
    updateWeightText: (String) -> Unit,
    updateGender: (Gender) -> Unit,
    updateUserDetailInfo: () -> Unit,
    navigateUpdateToMyInfoInMyInfoGraph: () -> Unit,
    updateInfoScreenState: UpdateInfoScreenState,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = "내 정보 수정",
                onClick = navigateUpdateToMyInfoInMyInfoGraph
            )
        },
        snackbarHost = {
            LiftSnackBar(
                modifier = modifier,
                snackbarHostState = updateInfoScreenState.snackbarHostState
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .focusClearManager(updateInfoScreenState.focusManager)
                .background(LiftTheme.colorScheme.no5)
                .padding(padding)
                .padding(
                    start = LiftTheme.space.space20,
                    end = LiftTheme.space.space20,
                    top = LiftTheme.space.space48
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space36)
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
            ) {
                GenderSelectionView(modifier, gender, updateGender)
                Spacer(modifier = modifier.height(LiftTheme.space.space8))
                HeightTextFieldView(
                    modifier,
                    heightText,
                    heightValidator,
                    updateHeightText,
                    updateInfoScreenState
                )
                WeightTextFieldView(
                    modifier,
                    weightText,
                    weightValidator,
                    updateWeightText,
                    updateInfoScreenState
                )
            }
            LiftSolidButton(
                text = "저장하기",
                enabled = (isChangedInfo &&
                        heightText.isNotEmpty() &&
                        weightText.isNotEmpty() &&
                        heightValidator.status &&
                        weightValidator.status),
                onClick = updateUserDetailInfo
            )
        }
    }
}

