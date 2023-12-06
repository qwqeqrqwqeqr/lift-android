package com.gradation.lift.feature.work.complete.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gradation.lift.common.utils.Validator
import com.gradation.lift.designsystem.component.LiftTextField
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun CommentView(
    modifier: Modifier = Modifier,
    comment: String,
    commentValidator: Validator,
    updateComment: (String) -> Unit,
    focusManager: FocusManager,
) {
    Column(modifier.padding(horizontal =  16.dp)) {

        Text(
            text = "한 줄 메모",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no9,
            modifier = modifier.align(Alignment.Start)
        )

        Spacer(modifier = modifier.padding(4.dp))
        LiftTextField(
            value = comment,
            onValueChange = updateComment,
            modifier = modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "간단한 메모를 남겨주세요.",
                    style = LiftTheme.typography.no6,
                    color = LiftTheme.colorScheme.no9.copy(alpha = 0.7f)
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
        )
        if (!commentValidator.status) {
            Text(
                text = commentValidator.message,
                style = LiftTheme.typography.no7,
                color = LiftTheme.colorScheme.no12
            )
        } else {
            Text(
                text = commentValidator.message,
                style = LiftTheme.typography.no7,
                color = Color.Transparent
            )
        }
    }
}