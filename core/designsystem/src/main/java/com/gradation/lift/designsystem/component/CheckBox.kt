package com.gradation.lift.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme

//TODO check box 로직 수정
@Composable
fun LiftCircleCheckBox(
    modifier: Modifier = Modifier,
    checked: MutableState<Boolean>,
    onCheckedChange: ((Boolean) -> Unit)?,
) {
    Row {
        Card(
            modifier = modifier.background(Color.Transparent).clip(CircleShape),
            border = BorderStroke(1.5.dp, color = MaterialTheme.colorScheme.primary)
        ) {
            Box(
                modifier = modifier
                    .size(25.dp)
                    .background(if (checked.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background)
                    .clickable {
                        checked.value = !checked.value
                    },
                contentAlignment = Alignment.Center
            ) {
                if(checked.value)
                    Icon(Icons.Filled.Done, contentDescription = "", tint = MaterialTheme.colorScheme.background)
            }
        }
    }
}


@Preview
@Composable
fun LiftCircleCheckBoxPreview() {
    LiftTheme {
        LiftCircleCheckBox(
            checked = remember {
                mutableStateOf(false)
            },
            onCheckedChange = {}
        )
    }
}