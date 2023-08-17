package com.gradation.lift.feature.login.sign_in.component

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
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.component.ToggleVisible
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun InputPasswordTextView(
    modifier : Modifier = Modifier,
    passwordText: String,
    passwordVisibleToggle: Boolean,
    passwordVisualTransformation: VisualTransformation,
    clearPasswordText: () -> Unit,
    updatePasswordText: (String) -> Unit,
    updatePasswordVisibleToggle: (Boolean) -> Unit,
    signInDefault: () -> Unit,
    focusManager: FocusManager,
){
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
                text = "비밀번호를 입력해주세요",
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
            signInDefault()
        }),
        visualTransformation = passwordVisualTransformation,
        trailingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if(passwordText.isNotBlank()){
                    ToggleVisible(
                        checked = passwordVisibleToggle,
                        onCheckedChange = updatePasswordVisibleToggle,
                        modifier = modifier.size(24.dp)
                    )
                    Spacer(modifier = modifier.padding(4.dp))
                    IconButton(
                        onClick = clearPasswordText,
                        modifier = modifier.size(24.dp)
                    ) {
                        Icon(
                            painter = painterResource(LiftIcon.Cancel),
                            contentDescription = "",
                            tint = Color.Unspecified,
                        )
                    }
                }
                Spacer(modifier = modifier.padding(8.dp))
            }
        }
    )
}