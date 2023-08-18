package com.gradation.lift.feature.register_detail.height_weight.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    navigateCondition: Boolean,
    currentRegisterProgressNumber: Int,
    weightText: String,
    heightText: String,
    updateCreateUserDetailHeight: (Float) -> Unit,
    updateCreateUserDetailWeight: (Float) -> Unit,
    updateCurrentRegisterProgressNumber: (Int) -> Unit,
    navigateHeightWeightToProfilePicture: () -> Unit,
) {
    LiftButton(
        enabled = navigateCondition,
        modifier = modifier.fillMaxWidth(),
        onClick = {
            updateCreateUserDetailHeight(heightText.toFloat())
            updateCreateUserDetailWeight(weightText.toFloat())
            updateCurrentRegisterProgressNumber(currentRegisterProgressNumber+1)
            navigateHeightWeightToProfilePicture()
        },
    ) {
        Text(
            text = "다음",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no5,
        )
    }
}