package com.gradation.lift.feature.login.sign_up.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
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
internal fun PasswordTextField(
    modifier: Modifier = Modifier,
    passwordText: State<String>,
    updatePasswordText: (String) -> Unit,
    focusManager: FocusManager,
    passwordVisualTransformation: State<VisualTransformation>,
    passwordVisible: State<Boolean>,
    onChangePasswordVisible: (Boolean) -> Unit,
    clearPassword: () -> Unit,
    passwordValidationSupportText: State<Validator>,
) {
    Text(
        text = "비밀번호",
        style = LiftTheme.typography.no3,
        color = LiftTheme.colorScheme.no3,
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = passwordText.value,
        onValueChange = updatePasswordText,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "영문, 숫자 조합 8~16자 이내",
                style = LiftTheme.typography.no6,
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        visualTransformation = passwordVisualTransformation.value,
        trailingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                ToggleVisible(
                    checked = passwordVisible.value,
                    onCheckedChange = onChangePasswordVisible,
                    modifier = modifier.size(24.dp)
                )
                Spacer(modifier = modifier.padding(4.dp))
                IconButton(
                    onClick = clearPassword,
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
    )
    if (!passwordValidationSupportText.value.status) {
        Text(
            text = passwordValidationSupportText.value.message,
            style = LiftTheme.typography.no7,
            color = LiftTheme.colorScheme.no12
        )
    }
}

