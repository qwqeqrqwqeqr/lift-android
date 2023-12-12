package com.gradation.lift.feature.createRoutine.routineSet.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftCancelButton
import com.gradation.lift.designsystem.component.LiftDialog
import com.gradation.lift.designsystem.component.LiftErrorButton
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun CancelDialog(
    modifier: Modifier = Modifier,
    onClickDialogSuspendButton: () -> Unit,
    onClickDialogDismissButton: () -> Unit,
) {
    LiftDialog(onDismissRequest = onClickDialogDismissButton) {
        Column(
            modifier
                .background(
                    color = LiftTheme.colorScheme.no5,
                    shape = RoundedCornerShape(size = 24.dp)
                )
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 8.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Text(
                text = buildAnnotatedString {
                    append("루틴 제작을")
                    withStyle(
                        style = SpanStyle(color = LiftTheme.colorScheme.no12),
                    ) {
                        append(" 중단")
                    }
                    append("하실건가요?")
                },
                textAlign = TextAlign.Center,
                style = LiftTheme.typography.no2,
                color = LiftTheme.colorScheme.no3
            )
            Spacer(
                modifier = modifier.padding(10.dp)
            )
            Text(
                "이전 화면으로 돌아갈 시 \n" +
                        "열심히 제작한 루틴이 삭제됩니다",
                textAlign = TextAlign.Center,
                style = LiftTheme.typography.no4,
                color = LiftTheme.colorScheme.no9
            )
            Spacer(
                modifier = modifier.padding(10.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                LiftCancelButton(
                    modifier = modifier.weight(1f),
                    onClick = onClickDialogDismissButton,
                ) {
                    Text(
                        text = "취소",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }

                LiftErrorButton(
                    modifier = modifier.weight(1f),
                    onClick = onClickDialogSuspendButton,
                ) {
                    Text(
                        text = "중단하기",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
            }

        }

    }
}

@Preview
@Composable
fun CompleteDialogPreview() {
    LiftMaterialTheme {
        CancelDialog(
            onClickDialogSuspendButton = {},
            onClickDialogDismissButton = {}
        )
    }
}