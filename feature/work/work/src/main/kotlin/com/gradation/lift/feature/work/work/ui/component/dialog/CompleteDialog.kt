package com.gradation.lift.feature.work.work.ui.component.dialog

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
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.component.LiftCancelButton
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
internal fun CompleteDialog(
    modifier: Modifier = Modifier,
    completeState: Boolean,
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
            if (completeState)
                LiftMultiStyleText(
                    defaultColor = LiftTheme.colorScheme.no3,
                    defaultTextStyle = LiftTextStyle.No2,
                    textAlign = TextAlign.Center,
                    textWithStyleList = listOf(
                        TextWithStyle(text = "운동을 "),
                        TextWithStyle(text = "완료", color = LiftTheme.colorScheme.no4),
                        TextWithStyle(text = "하실건가요?"),
                    )
                )
            else
                LiftMultiStyleText(
                    defaultColor = LiftTheme.colorScheme.no3,
                    defaultTextStyle = LiftTextStyle.No2,
                    textAlign = TextAlign.Center,
                    textWithStyleList = listOf(
                        TextWithStyle(text = "아직 "),
                        TextWithStyle(text = "완료", color = LiftTheme.colorScheme.no4),
                        TextWithStyle(text = "하지 않은 운동이 존재해요."),
                    )
                )


            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = LiftTheme.space.space8)
            ) {
                if (completeState)
                    LiftText(
                        textStyle = LiftTextStyle.No4,
                        text = "완료한 운동 기록을 저장하고 \n" +
                                "운동 결과 화면으로 이동합니다.",
                        color = LiftTheme.colorScheme.no9,
                        textAlign = TextAlign.Start
                    )
                else
                    LiftText(
                        textStyle = LiftTextStyle.No4,
                        text = "그래도 완료할 경우, 세트를 \n" +
                                "마무리 하지 않은 운동들은 삭제됩니다.",
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

                LiftSolidButton(
                    modifier = modifier.weight(1f),
                    onClick = onClickDialogCompleteButton,
                    text = "완료"
                )
            }
        }
    }
}

@Preview
@Composable
fun CompleteDialogPreview() {
    LiftMaterialTheme {
        CompleteDialog(
            completeState = false,
            onClickDialogCompleteButton = {},
            onClickDialogDismissButton = {}
        )
    }
}