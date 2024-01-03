package com.gradation.lift.feature.register_detail.height_weight.ui.component

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
import com.gradation.lift.designsystem.component.textField.LiftWeightInputTextField
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.feature.register_detail.height_weight.data.HeightWeightScreenState

@Composable
internal fun WeightTextFieldView(
    modifier: Modifier = Modifier,
    weight: String,
    weightValidator: Validator,
    updateWeight: (String) -> Unit,
    heightWeightScreenState: HeightWeightScreenState,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
    ) {
        LiftText(
            modifier = modifier.fillMaxWidth(),
            textStyle = LiftTextStyle.No3,
            text = "몸무게",
            color = LiftTheme.colorScheme.no3,
            textAlign = TextAlign.Start
        )
        LiftWeightInputTextField(
            modifier = modifier,
            value = weight,
            onValueChange = { weight ->
                updateWeight(weight)
            },
            placeHolderValue = "몸무게 입력해주세요",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            isError = !weightValidator.status,
            keyboardActions = KeyboardActions(
                onDone = { heightWeightScreenState.focusManager.clearFocus() },
            )
        )
        LiftText(
            modifier = modifier.fillMaxWidth(),
            textStyle = LiftTextStyle.No7,
            text = weightValidator.message,
            color = LiftTheme.colorScheme.no12,
            textAlign = TextAlign.Start
        )
    }
}