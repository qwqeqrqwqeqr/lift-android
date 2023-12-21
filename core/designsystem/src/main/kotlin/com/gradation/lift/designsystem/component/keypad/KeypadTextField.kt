package com.gradation.lift.designsystem.component.keypad

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftKeypadTextField(
    modifier: Modifier = Modifier,
    value: String,
    focused: Boolean
) {
    Text(
        modifier = modifier
            .background(LiftTheme.colorScheme.no5, RoundedCornerShape(6.dp))
            .border(
                width = 1.dp,
                color = if(focused) LiftTheme.colorScheme.no4 else Color.Transparent,
                shape = RoundedCornerShape(6.dp)
            )
            .height(28.dp)
            .padding(4.dp),
        text = value,
        color = LiftTheme.colorScheme.no9,
        style = LiftTheme.typography.no3,
        textAlign = TextAlign.Center
    )
}

@Composable
@Preview
fun LiftKeyPadTextFieldPreview() {
    LiftMaterialTheme {
        LiftKeypadTextField(value = "13", focused = true)
    }

}