package com.gradation.lift.feature.login.sign_up.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun SignUpView(
    modifier: Modifier= Modifier,
    signUpCondition: Boolean,
    signUp: () -> Unit,
){
    LiftButton(
        modifier = modifier.fillMaxWidth(),
        onClick = signUp,
        enabled = signUpCondition
    ) {
        Text(
            text = "회원가입",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no5,
        )
    }
}