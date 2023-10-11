package com.gradation.lift.designsystem.keypad

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
                width = (if (focused) 0.5.dp else 0.dp),
                color = LiftTheme.colorScheme.no4,
                shape = RoundedCornerShape(6.dp)
            )
            .height(28.dp)
            .padding(4.dp),
        text = value,
        color = LiftTheme.colorScheme.no9,
        style = LiftTheme.typography.no4,
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