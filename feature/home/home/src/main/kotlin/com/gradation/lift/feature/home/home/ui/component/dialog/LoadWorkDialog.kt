package com.gradation.lift.feature.home.home.ui.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
internal fun LoadWorkDialog(
    modifier: Modifier = Modifier,
    clearWork: () -> Unit,
    navigateHomeGraphToWorkGraph: () -> Unit,
) {
    LiftDialog(onDismissRequest = {}) {
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
                    TextWithStyle(text = "완료하지 않은 운동", color = LiftTheme.colorScheme.no4),
                    TextWithStyle(text = "이 있어요"),
                )
            )

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = LiftTheme.space.space8)
            ) {
                LiftText(
                    textStyle = LiftTextStyle.No4,
                    text = "운동을 다시 불러와 이어서 진행해 볼까요?",
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
                    onClick = clearWork,
                    text = "취소",
                )

                LiftSolidButton(
                    modifier = modifier.weight(1f),
                    onClick = navigateHomeGraphToWorkGraph,
                    text = "이어서 하기"
                )
            }

        }

    }
}

@Preview
@Composable
fun LoadWorkDialogPreview() {
    LiftMaterialTheme {
        LoadWorkDialog(
            clearWork = {},
            navigateHomeGraphToWorkGraph = {}
        )
    }
}