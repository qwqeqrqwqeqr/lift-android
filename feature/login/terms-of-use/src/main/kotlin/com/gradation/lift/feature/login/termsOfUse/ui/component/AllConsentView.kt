package com.gradation.lift.feature.login.termsOfUse.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.checkBox.LiftCircleCheckbox
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun AllConsentView(
    modifier: Modifier = Modifier,
    contentText: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
    ) {
        LiftCircleCheckbox(
            modifier = modifier.size(LiftTheme.space.space20),
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        LiftText(
            modifier = modifier.weight(1f),
            textStyle = LiftTextStyle.No3,
            text = contentText,
            color = LiftTheme.colorScheme.no3,
            textAlign = TextAlign.Start
        )
    }
}