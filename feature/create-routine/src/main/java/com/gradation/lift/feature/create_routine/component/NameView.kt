package com.gradation.lift.feature.create_routine.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun NameView(
    modifier:Modifier=Modifier,
    nameText: State<String>,
    updateNameText : (String) -> Unit
){
    Text(
        text = "루틴리스트 이름",
        style = LiftTheme.typography.no3,
        color = LiftTheme.colorScheme.no3
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = nameText.value,
        onValueChange = updateNameText,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "이름을 입력해주세요 (1-8 자)",
                style = LiftTheme.typography.no6,
            )
        },
        singleLine = true,
    )
}