package com.gradation.lift.feature.createRoutine.routineSet.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.button.LiftErrorButton
import com.gradation.lift.designsystem.component.dialog.LiftDialog
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
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
                    shape = RoundedCornerShape(size = LiftTheme.space.space20)
                )
                .padding(LiftTheme.space.space20),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LiftMultiStyleText(
                defaultColor = LiftTheme.colorScheme.no3,
                defaultTextStyle = LiftTextStyle.No2,
                textAlign = TextAlign.Center,
                textWithStyleList = listOf(
                    TextWithStyle(text = "루틴 제작을"),
                    TextWithStyle(text = " 완료", color = LiftTheme.colorScheme.no12),
                    TextWithStyle(text = "하실건가요?"),
                )
            )

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = LiftTheme.space.space8)
            ) {
                LiftText(
                    textStyle = LiftTextStyle.No4,
                    text = "이전 화면으로 돌아갈 시 \n열심히 제작한 루틴이 삭제됩니다",
                    color = LiftTheme.colorScheme.no9,
                    textAlign = TextAlign.Start
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    LiftTheme.space.space8,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                LiftDefaultButton(
                    modifier = modifier.weight(1f),
                    onClick = onClickDialogDismissButton,
                    text = "취소",
                )

                LiftErrorButton(
                    modifier = modifier.weight(1f),
                    onClick = onClickDialogSuspendButton,
                    text = "중단"
                )
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