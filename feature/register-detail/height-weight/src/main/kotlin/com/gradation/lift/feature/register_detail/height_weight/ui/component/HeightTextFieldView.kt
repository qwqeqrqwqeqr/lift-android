package com.gradation.lift.feature.register_detail.height_weight.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.textField.LiftHeightInputTextField
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.register_detail.height_weight.data.HeightWeightScreenState

@Composable
internal fun HeightTextFieldView(
    modifier: Modifier = Modifier,
    height: String,
    heightValidator: Validator,
    updateHeight: (String) -> Unit,
    heightWeightScreenState: HeightWeightScreenState,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
    ) {
        LiftText(
            modifier = modifier.fillMaxWidth(),
            textStyle = LiftTextStyle.No3,
            text = "키",
            color = LiftTheme.colorScheme.no3,
            textAlign = TextAlign.Start
        )
        LiftHeightInputTextField(
            modifier = modifier,
            value = height,
            onValueChange = { height ->
                updateHeight(height)
            },
            placeHolderValue = "키를 입력해주세요",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            isError = !heightValidator.status,
            keyboardActions = KeyboardActions(
                onNext = { heightWeightScreenState.focusManager.moveFocus(FocusDirection.Down) },
            )
        )
        LiftText(
            modifier = modifier.fillMaxWidth(),
            textStyle = LiftTextStyle.No7,
            text = heightValidator.message,
            color = LiftTheme.colorScheme.no12,
            textAlign = TextAlign.Start
        )
    }
}

