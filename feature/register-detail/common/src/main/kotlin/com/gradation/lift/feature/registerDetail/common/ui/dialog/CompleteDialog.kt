package com.gradation.lift.feature.registerDetail.common.ui.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.gradation.lift.designsystem.component.button.LiftSolidButton
import com.gradation.lift.designsystem.component.dialog.LiftDialog
import com.gradation.lift.designsystem.component.text.LiftText
import com.gradation.lift.designsystem.component.text.LiftTextStyle
import com.gradation.lift.designsystem.resource.LiftImage
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun CompleteDialog(
    modifier: Modifier = Modifier,
    onComplete: () -> Unit,
) {
    LiftDialog(onDismissRequest = onComplete) {
        Column(
            modifier
                .background(
                    color = LiftTheme.colorScheme.no5,
                    shape = RoundedCornerShape(size = LiftTheme.space.space24)
                )
                .padding(LiftTheme.space.space20),
            verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space12),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Image(
                modifier = modifier
                    .size(LiftTheme.space.space108),
                painter = painterResource(id = LiftImage.Congrats),
                contentDescription = "Congrats",
            )
            Column(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(LiftTheme.space.space16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LiftText(
                    modifier = modifier,
                    text = "추가정보 등록을 완료하였어요",
                    textAlign = TextAlign.Center,
                    textStyle = LiftTextStyle.No2,
                    color = LiftTheme.colorScheme.no3,
                )

                LiftText(
                    modifier = modifier,
                    text = "추가정보 수정은 설정메뉴에서 다시 할 수 있어요",
                    textAlign = TextAlign.Center,
                    textStyle = LiftTextStyle.No4,
                    color = LiftTheme.colorScheme.no9,
                )

                LiftSolidButton(
                    modifier = modifier.fillMaxWidth(),
                    text = "완료",
                    onClick = onComplete,
                )
            }
        }
    }
}