package com.gradation.lift.feature.register_detail.name.ui

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
import com.gradation.lift.feature.register_detail.name.ui.component.HeaderView
import com.gradation.lift.feature.register_detail.name.ui.component.NameTextFieldView
import com.gradation.lift.feature.register_detail.name.data.NameScreenState


@Composable
internal fun NameScreen(
    modifier: Modifier = Modifier,
    name: String,
    nameValidator: Validator,
    currentRegisterProgressNumber: Int,
    totalRegisterProgressNumber: Int,
    updateCurrentRegisterProgressNumber: (Int) -> Unit,
    updateName: (String) -> Unit,
    updateCancelDialogView: (Boolean) -> Unit,
    navigateToGenderInRegisterDetailGraph: () -> Unit,
    nameScreenState: NameScreenState,
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
            )
            Spacer(modifier = modifier.height(LiftTheme.space.space28))
            HeaderView(modifier)
            Spacer(modifier = modifier.height(LiftTheme.space.space32))
            NameTextFieldView(
                modifier,
                name,
                nameValidator,
                updateName,
                nameScreenState
            )
            Spacer(modifier = modifier.height(LiftTheme.space.space36))
            AnimatedVisibility(
                visible = name.isNotEmpty()
            ) {
                LiftSolidButton(
                    text = "다음",
                    enabled = name.length>=2 && nameValidator.status
                ) {
                    updateCurrentRegisterProgressNumber(2)
                    navigateToGenderInRegisterDetailGraph()
                }
            }
        }
    }
}

