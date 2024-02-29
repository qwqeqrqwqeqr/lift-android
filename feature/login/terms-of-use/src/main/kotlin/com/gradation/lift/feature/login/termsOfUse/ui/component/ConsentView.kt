package com.gradation.lift.feature.login.termsOfUse.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.checkBox.LiftCircleCheckBoxSize
import com.gradation.lift.designsystem.component.checkBox.LiftCircleCheckbox
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme
import com.gradation.lift.ui.modifier.noRippleClickable

@Composable
fun ConsentView(
    modifier: Modifier = Modifier,
    contentText: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LiftTheme.space.space8)
    ) {
        LiftCircleCheckbox(
            modifier = modifier,
            liftCircleCheckBoxSize = LiftCircleCheckBoxSize.Size24,
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        LiftText(
            modifier = modifier.weight(1f),
            textStyle = LiftTextStyle.No5,
            text = contentText,
            color = LiftTheme.colorScheme.no3,
            textAlign = TextAlign.Start
        )
        Icon(
            modifier = modifier
                .size(LiftTheme.space.space16)
                .noRippleClickable { onClick() },
            painter = painterResource(id = LiftIcon.ChevronRight),
            contentDescription = "TermsOfUseDetail",
            tint = LiftTheme.colorScheme.no9
        )
    }
}