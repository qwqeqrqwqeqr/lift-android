package com.gradation.lift.feature.login.sign_up.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.component.ToggleVisible
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun PasswordVerificationTextField(
    modifier: Modifier = Modifier,
    passwordVerificationText: String,
    passwordVerificationVisibleToggle: Boolean,
    passwordVerificationValidator: Validator,
    passwordVerificationVisualTransformation: VisualTransformation,
    updatePasswordVerificationText: (String) -> Unit,
    updatePasswordVerificationVisibleToggle: (Boolean) -> Unit,
    clearPasswordVerificationText: () -> Unit,
    focusManager: FocusManager,
) {
    Text(
        text = "비밀번호 확인",
        style = LiftTheme.typography.no3,
        color = LiftTheme.colorScheme.no3,
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = passwordVerificationText,
        onValueChange = updatePasswordVerificationText,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "비밀번호를 한번 더 입력해주세요",
                style = LiftTheme.typography.no6,
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }),
        visualTransformation = passwordVerificationVisualTransformation,
        trailingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if(passwordVerificationText.isNotBlank()) {
                    ToggleVisible(
                        checked = passwordVerificationVisibleToggle,
                        onCheckedChange = updatePasswordVerificationVisibleToggle,
                        modifier = modifier.size(24.dp)
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    IconButton(
                        onClick = clearPasswordVerificationText,
                        modifier = modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(LiftIcon.Cancel),
                            contentDescription = "",
                            tint = Color.Unspecified,
                        )
                    }
                    Spacer(modifier = modifier.padding(8.dp))
                }
            }
        }

    )
    if (!passwordVerificationValidator.status) {
        Text(
            text = passwordVerificationValidator.message,
            style = LiftTheme.typography.no7,
            color = LiftTheme.colorScheme.no12
        )
    }
}
