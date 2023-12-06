package com.gradation.lift.feature.work.work.component.dialog

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
import com.gradation.lift.designsystem.component.LiftDialog
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
internal fun CompleteDialog(
    modifier: Modifier = Modifier,
    completeState : Boolean,
    onClickDialogCompleteButton: () -> Unit,
    onClickDialogDismissButton: () -> Unit,
) {
    LiftDialog(onDismissRequest = {}) {
        Column(
            modifier
                .background(
                    color = LiftTheme.colorScheme.no5,
                    shape = RoundedCornerShape(size = 24.dp)
                )
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 20.dp,
                    bottom = 10.dp
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            if(completeState){
            Text(
                text = buildAnnotatedString {
                    append("운동을 ")
                    withStyle(
                        style = SpanStyle(color = LiftTheme.colorScheme.no4),
                    ) {
                        append("완료")
                    }
                    append("하실건가요?")
                },
                textAlign = TextAlign.Center,
                style = LiftTheme.typography.no2,
                color = LiftTheme.colorScheme.no3
            )}
            else{
                Text(
                    text = buildAnnotatedString {
                        append("아직")
                        withStyle(
                            style = SpanStyle(color = LiftTheme.colorScheme.no4),
                        ) {
                            append(" 완료")
                        }
                        append("하지 않은 운동이 존재합니다.")
                    },
                    textAlign = TextAlign.Center,
                    style = LiftTheme.typography.no2,
                    color = LiftTheme.colorScheme.no3
                )
            }
            Spacer(
                modifier = modifier.padding(10.dp)
            )
            if(completeState) {
                Text(
                    "완료한 운동 기록을 저장하고 \n 운동 결과 화면으로 이동합니다",
                    textAlign = TextAlign.Center,
                    style = LiftTheme.typography.no4,
                    color = LiftTheme.colorScheme.no9
                )
            }
            else{
                Text(
                    "그래도 완료할 경우, 세트를 \n마무리 하지 않은 운동들은 삭제됩니다.",
                    textAlign = TextAlign.Center,
                    style = LiftTheme.typography.no4,
                    color = LiftTheme.colorScheme.no9
                )
            }
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

                LiftButton(
                    modifier = modifier.weight(1f),
                    onClick = onClickDialogCompleteButton,
                ) {
                    Text(
                        text = "완료",
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
        CompleteDialog(
            completeState=false,
            onClickDialogCompleteButton = {},
            onClickDialogDismissButton = {}
        )
    }
}