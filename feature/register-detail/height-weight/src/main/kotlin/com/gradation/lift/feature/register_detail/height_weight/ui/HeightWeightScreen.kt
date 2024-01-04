package com.gradation.lift.feature.register_detail.height_weight.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.registerDetail.common.ui.component.ProgressNumberView
import com.gradation.lift.feature.register_detail.height_weight.data.HeightWeightScreenState
import com.gradation.lift.feature.register_detail.height_weight.ui.component.HeaderView
import com.gradation.lift.feature.register_detail.height_weight.ui.component.HeightTextFieldView
import com.gradation.lift.feature.register_detail.height_weight.ui.component.WeightTextFieldView


@Composable
internal fun HeightWeightScreen(
    modifier: Modifier = Modifier,
    height: String,
    weight: String,
    heightValidator: Validator,
    weightValidator: Validator,
    currentRegisterProgressNumber: Int,
    totalRegisterProgressNumber: Int,
    updateHeight: (String) -> Unit,
    updateWeight: (String) -> Unit,
    updateCancelDialogView: (Boolean) -> Unit,
    updateCurrentRegisterProgressNumber: (Int) -> Unit,
    navigateToNameInRegisterDetailGraph: () -> Unit,
    navigateToGenderInRegisterDetailGraph: () -> Unit,
    navigateToProfilePictureInRegisterDetailGraph: () -> Unit,
    heightWeightScreenState: HeightWeightScreenState,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            LiftTopBar(
                modifier = modifier,
                title = "추가정보 입력",
                onClick = { updateCancelDialogView(true) }
            )
        },

        ) { it ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(LiftTheme.colorScheme.no5)
                .padding(it)
                .padding(
                    start = LiftTheme.space.space20,
                    end = LiftTheme.space.space20,
                    top = LiftTheme.space.space48
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProgressNumberView(
                modifier,
                currentRegisterProgressNumber,
                totalRegisterProgressNumber,
                navigateToNameInRegisterDetailGraph = {
                    updateCurrentRegisterProgressNumber(1)
                    navigateToNameInRegisterDetailGraph()
                },
                navigateToGenderInRegisterDetailGraph = {
                    updateCurrentRegisterProgressNumber(2)
                    navigateToGenderInRegisterDetailGraph()
                },
            )
            Spacer(modifier = modifier.height(LiftTheme.space.space28))
            HeaderView(modifier)
            Spacer(modifier = modifier.height(LiftTheme.space.space32))
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
            ) {
                HeightTextFieldView(
                    modifier,
                    height,
                    heightValidator,
                    updateHeight,
                    heightWeightScreenState
                )
                WeightTextFieldView(
                    modifier,
                    weight,
                    weightValidator,
                    updateWeight,
                    heightWeightScreenState
                )
            }
            Spacer(modifier = modifier.height(LiftTheme.space.space36))
            AnimatedVisibility(
                visible = height.isNotEmpty() && weight.isNotEmpty()
            ) {
                LiftSolidButton(
                    text = "다음",
                    enabled = height.isNotEmpty() && heightValidator.status &&
                            weight.isNotEmpty() && weightValidator.status
                ) {
                    updateCurrentRegisterProgressNumber(4)
                    navigateToProfilePictureInRegisterDetailGraph()
                }
            }
        }
    }
}

