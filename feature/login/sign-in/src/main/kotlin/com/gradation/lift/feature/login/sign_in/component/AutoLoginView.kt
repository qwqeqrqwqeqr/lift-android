package com.gradation.lift.feature.login.sign_in.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.ToggleCheckbox
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun AutoLoginView(
    modifier: Modifier = Modifier,
    autoLoginCheckToggle: Boolean,
    updateAutoLoginCheckToggle: (Boolean) -> Unit,
) {
    Row {
        ToggleCheckbox(
            checked = autoLoginCheckToggle,
            onCheckedChange = updateAutoLoginCheckToggle,
            modifier = modifier.size(18.dp)
        )
        Spacer(modifier = modifier.padding(2.dp))
        Text(
            text = "자동 로그인",
            style = LiftTheme.typography.no7,
            color = LiftTheme.colorScheme.no7,
        )
    }
}






