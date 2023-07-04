package com.gradation.lift.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun LiftToggleTextBox(
    checked: Boolean = true,
    text: String,
    modifier: Modifier = Modifier,
) {
    if(checked){
        Box(
            modifier = modifier
                .border(
                    width = 2.dp,
                    color = LiftTheme.colorScheme.no4,
                    shape = RoundedCornerShape(size = 30.dp)
                )
                .height(48.dp)
                .padding(start = 14.dp, top = 10.dp, end = 14.dp, bottom = 14.dp)
                .background(LiftTheme.colorScheme.no5,  shape = RoundedCornerShape(size = 30.dp))

            ,
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no4
            )

        }
    }
    else{
        Box(
            modifier = modifier
                .height(48.dp)
                .background(LiftTheme.colorScheme.no1, shape = RoundedCornerShape(size = 30.dp))
                .padding(start = 14.dp, top = 10.dp, end = 14.dp, bottom = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no2
            )

        }
    }


}


@Composable
@Preview
fun LiftToggleTextBoxPreview() {
    LiftMaterialTheme {
        Column {
            LiftToggleTextBox(checked = true, text = "리프트")
            LiftToggleTextBox(checked = false, text = "리프트")
        }

    }
}