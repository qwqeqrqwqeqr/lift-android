package com.gradation.lift.feature.register_detail.name.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.textField.LiftDefaultInputTextField
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.register_detail.name.data.NameScreenState

@Composable
fun NameTextFieldView(
    modifier: Modifier = Modifier,
    name: String,
    nameValidator: Validator,
    updateNameText: (String) -> Unit,
    nameScreenState: NameScreenState,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
    ) {
        LiftText(
            modifier = modifier.fillMaxWidth(),
            textStyle = LiftTextStyle.No3,
            text = "닉네임",
            color = LiftTheme.colorScheme.no3,
            textAlign = TextAlign.Start
        )
        LiftDefaultInputTextField(
            value = name,
            onValueChange = updateNameText,
            modifier = modifier,
            placeHolderValue = "닉네임을 입력해주세요",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            isError = !nameValidator.status,
            keyboardActions = KeyboardActions(
                onDone = { nameScreenState.focusManager.clearFocus() },
            )
        )
        LiftText(
            modifier = modifier.fillMaxWidth(),
            textStyle = LiftTextStyle.No7,
            text = nameValidator.message,
            color = LiftTheme.colorScheme.no12,
            textAlign = TextAlign.Start
        )
    }
}