package com.gradation.lift.feature.work.work.ui.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gradation.lift.designsystem.component.button.LiftDefaultButton
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.dialog.LiftDialog
import com.gradation.lift.designsystem.component.text.LiftMultiStyleText
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.component.text.TextWithStyle
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun AutoCompleteDialog(
    modifier: Modifier = Modifier,
    onClickDialogCompleteButton: () -> Unit,
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
                    TextWithStyle(text = "모든 운동을 "),
                    TextWithStyle(text = "완료", color = LiftTheme.colorScheme.no4),
                    TextWithStyle(text = "하였습니다."),
                )
            )

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = LiftTheme.space.space8)
            ) {
                LiftText(
                    textStyle = LiftTextStyle.No4,
                    text = "지금까지 진행한 운동을\n" +
                            "완료 후 저장할까요?",
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
                    text = "계속 진행",
                )

                LiftSolidButton(
                    modifier = modifier.weight(1f),
                    onClick = onClickDialogCompleteButton,
                    text = "저장"
                )
            }

        }

    }
}

@Preview
@Composable
fun AutoCompleteDialogPreview() {
    LiftMaterialTheme {
        AutoCompleteDialog(
            onClickDialogCompleteButton = {},
            onClickDialogDismissButton = {}
        )
    }
}