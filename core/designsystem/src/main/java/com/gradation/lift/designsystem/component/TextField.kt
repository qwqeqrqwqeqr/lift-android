package com.gradation.lift.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.theme.LiftTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiftTextField(
    value: String,
    onValueChange : (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    placeholder:  @Composable (() -> Unit)?
) {
    TextField(
        value =value,
        onValueChange =onValueChange,
        modifier=modifier,
        enabled=enabled,
        singleLine=singleLine,
        maxLines=maxLines,
        minLines=minLines,
        placeholder=placeholder,
        colors = TextFieldDefaults.textFieldColors(
            textColor= MaterialTheme.colorScheme.secondary,
            containerColor = MaterialTheme.colorScheme.surface,
            placeholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(32.dp)

    )
}


@Preview
@Composable
fun LiftTextFieldPreview(){
    LiftTheme {
        Box(Modifier.fillMaxSize().background(Color.White)) {
            LiftTextField(value = "", onValueChange = {},placeholder= { Text("힌트") })
        }

    }
}