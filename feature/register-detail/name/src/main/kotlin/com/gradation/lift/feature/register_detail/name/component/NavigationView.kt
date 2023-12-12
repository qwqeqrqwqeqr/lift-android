package com.gradation.lift.feature.register_detail.name.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun NavigationView(
    modifier: Modifier = Modifier,
    navigateCondition: Boolean,
    nameText: String,
    currentRegisterProgressNumber: Int,
    updateCreateUserDetailName: (String) -> Unit,
    updateCurrentRegisterProgressNumber: (Int) -> Unit,
    navigateNameToGenderInRegisterDetailGraph: () -> Unit,
    focusManager: FocusManager
) {
    LiftButton(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            focusManager.clearFocus()
            updateCreateUserDetailName(nameText)
            updateCurrentRegisterProgressNumber(currentRegisterProgressNumber + 1)
            navigateNameToGenderInRegisterDetailGraph()
        },
        enabled = navigateCondition,
    ) {
        Text(
            text = "다음",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no5,
        )
    }
}