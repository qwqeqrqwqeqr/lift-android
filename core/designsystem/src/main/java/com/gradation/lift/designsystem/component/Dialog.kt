package com.gradation.lift.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bumptech.glide.Glide
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    modifier: Modifier = Modifier,
) {
    Dialog(onDismissRequest = onDismissRequest) {

        Column(
            modifier
                .width(280.dp)
                .height(300.dp)
                .padding(20.dp)
                .background(color = LiftTheme.colorScheme.no5, shape = RoundedCornerShape(size = 24.dp))

        ) {
            Glide.with(this).load().into()

            Spacer(
                modifier = Modifier
                    .height(12.dp)
                    .fillMaxWidth()
            )
            Text(
                "Kotlin World",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(),
                style = LiftTheme.typography.no1,
                color = LiftTheme.colorScheme.no11
            )
            Spacer(
                modifier = Modifier
                    .height(12.dp)
                    .fillMaxWidth()
            )
            LiftButton(
                modifier = modifier.fillMaxWidth(),
                onClick = {},
            ) {
                Text(
                    text = "다음",
                    style = LiftTheme.typography.no3,
                    color = LiftTheme.colorScheme.no5,
                )
            }
            Spacer(
                modifier = Modifier
                    .height(12.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview
fun LiftDialogPreview() {
    LiftMaterialTheme {
        LiftDialog({})
    }
}