package com.gradation.lift.feature.login.sign_in.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun SignInView(
    modifier: Modifier = Modifier,
    signInDefault: () -> Unit,
    focusManager: FocusManager,
) {
    LiftButton(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            focusManager.clearFocus()
            signInDefault()
        },
    ) {
        Text(
            text = "로그인",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no5,
        )
    }
}