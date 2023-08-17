package com.gradation.lift.feature.register_detail.gender.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.model.model.user.Gender

@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    gender: Gender,
    currentRegisterProgressNumber: Int,
    updateCreateUserDetailGender: (Gender) -> Unit,
    updateCurrentRegisterProgressNumber: (Int) -> Unit,
    navigateGenderToHeightWeight: () -> Unit,
) {
    LiftButton(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            updateCreateUserDetailGender(gender)
            updateCurrentRegisterProgressNumber(currentRegisterProgressNumber + 1)
            navigateGenderToHeightWeight()
        },
    ) {
        Text(
            text = "다음",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no5,
        )
    }
}