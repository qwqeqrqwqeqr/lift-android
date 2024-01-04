package com.gradation.lift.feature.register_detail.gender.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.topBar.LiftTopBar
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.registerDetail.common.ui.component.ProgressNumberView
import com.gradation.lift.feature.register_detail.gender.ui.component.GenderSelectionView
import com.gradation.lift.feature.register_detail.gender.ui.component.HeaderView
import com.gradation.lift.model.model.user.Gender


@Composable
internal fun GenderScreen(
    modifier: Modifier = Modifier,
    gender: Gender,
    name: String,
    currentRegisterProgressNumber: Int,
    totalRegisterProgressNumber: Int,
    updateGender: (Gender) -> Unit,
    updateCurrentRegisterProgressNumber: (Int) -> Unit,
    updateCancelDialogView: (Boolean) -> Unit,
    navigateToNameInRegisterDetailGraph: () -> Unit,
    navigateToHeightWeightInRegisterDetailGraph: () -> Unit,
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
                }
            )
            Spacer(modifier = modifier.height(LiftTheme.space.space28))
            HeaderView(modifier, name)
            Spacer(modifier = modifier.height(LiftTheme.space.space32))
            GenderSelectionView(modifier, gender, updateGender)
            Spacer(modifier = modifier.height(LiftTheme.space.space36))
            LiftSolidButton(text = "다음") {
                updateCurrentRegisterProgressNumber(3)
                navigateToHeightWeightInRegisterDetailGraph()
            }
        }
    }
}

