package com.gradation.lift.feature.create_routine.routine_set.component

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
import org.junit.runner.Description

@Composable
fun DescriptionView(
    modifier:Modifier=Modifier,
    descriptionText : State<String>,
    updateDescriptionText : (String) -> Unit
){
    Text(
        text = "루틴리스트 설명",
        style = LiftTheme.typography.no3,
        color = LiftTheme.colorScheme.no3
    )
    Spacer(modifier = modifier.padding(4.dp))
    LiftTextField(
        value = descriptionText.value,
        onValueChange = updateDescriptionText,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "간단한 설명을 입력해주세요 (1-15 자)",
                style = LiftTheme.typography.no6,
            )
        },
        singleLine = true,
    )
}