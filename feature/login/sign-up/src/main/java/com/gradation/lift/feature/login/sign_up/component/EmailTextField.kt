package com.gradation.lift.feature.login.sign_up.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftTextField

@Composable
internal fun EmailTextField(
    modifier: Modifier = Modifier,
    emailText: String,
    updateEmailText: (String) -> Unit,
    focusManager: FocusManager,
    emailValidationSupportText: Validator,
) {
    Text(
        text = "이메일",
        style = MaterialTheme.typography.titleLarge,
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = emailText,
        onValueChange = updateEmailText,
        modifier = modifier.fillMaxWidth(),
        placeholder = { Text("사용할 이메일을 입력해주세요.") },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            },
        )
    )
    if (!emailValidationSupportText.status) {
        Text(
            text = emailValidationSupportText.message,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.error
        )
    }
}