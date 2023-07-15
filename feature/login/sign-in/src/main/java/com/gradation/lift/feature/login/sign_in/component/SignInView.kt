package com.gradation.lift.feature.login.sign_in.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.component.ToggleVisible
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.login.sign_in.component.detail.SignInHelperView
import com.gradation.lift.feature.login.sign_in.component.detail.SignUpView

@Composable
fun SignInView(
    modifier: Modifier = Modifier,
    emailText: String,
    updateEmailText: (String) -> Unit,
    passwordText: String,
    updatePasswordText: (String) -> Unit,
    onClickFindEmail: () -> Unit,
    onClickFindPassword: () -> Unit,
    onClickSignUp: () -> Unit,
    onClickSignIn: () -> Unit,
    autoLoginChecked: Boolean,
    onChangeAutoLoginChecked: (Boolean) -> Unit,
    passwordVisible: Boolean,
    onChangePasswordVisible: (Boolean) -> Unit,
    passwordVisualTransformation: VisualTransformation,
    clearPassword: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Text(
        text = "이메일",
        style = LiftTheme.typography.no3,
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = emailText,
        onValueChange = updateEmailText,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text="이메일을 입력해주세요.",
                style = LiftTheme.typography.no6,
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        })
    )

    Spacer(modifier = modifier.padding(8.dp))
    Text(
        text = "비밀번호",
        style = LiftTheme.typography.no3,
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = passwordText,
        onValueChange = updatePasswordText,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                style = LiftTheme.typography.no6,
                text = "비밀번호를 입력해주세요.",
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
            onClickSignIn()
        }),
        visualTransformation = passwordVisualTransformation,
        trailingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if(passwordText.isNotBlank()){
                    ToggleVisible(
                        checked = passwordVisible,
                        onCheckedChange = onChangePasswordVisible,
                        modifier = modifier.size(24.dp)
                    )
                }
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
    Spacer(modifier = modifier.padding(4.dp))

    SignInHelperView(
        modifier = modifier,
        onClickFindEmail = onClickFindEmail,
        onClickFindPassword = onClickFindPassword,
        autoLoginChecked = autoLoginChecked,
        onChangeAutoLoginChecked = onChangeAutoLoginChecked
    )
    Spacer(modifier = modifier.padding(18.dp))

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LiftButton(
            modifier = modifier.fillMaxWidth(),
            onClick = {
                focusManager.clearFocus()
                onClickSignIn()
            },
        ) {
            Text(
                text = "로그인",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no5,
            )
        }
        SignUpView(modifier = modifier, onClickSignUp = onClickSignUp)
    }
}

