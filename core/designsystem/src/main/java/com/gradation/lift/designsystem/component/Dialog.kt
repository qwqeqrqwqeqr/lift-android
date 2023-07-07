package com.gradation.lift.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun LiftDialog(
    onDismissRequest: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        content()
    }
}

@Composable
@Preview
fun LiftDialogPreview() {
    LiftMaterialTheme {
        LiftDialog(onDismissRequest = {}) {
            Column(
                Modifier
                    .background(
                        color = LiftTheme.colorScheme.no5,
                        shape = RoundedCornerShape(size = 24.dp)
                    )
                    .width(280.dp)
                    .height(300.dp)
                    .padding(20.dp),

                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Image(
                    painter = painterResource(R.drawable.congrats),
                    contentDescription = "congrats",
                    modifier = Modifier.size(96.dp)
                )
                Spacer(
                    modifier = Modifier.padding(6.dp)
                )
                Text(
                    "추가정보 등록이 다 되었어요!",
                    textAlign = TextAlign.Center,
                    style = LiftTheme.typography.no2,
                    color = LiftTheme.colorScheme.no3
                )
                Spacer(
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    "이제 운동하러 가볼까요?",
                    textAlign = TextAlign.Center,
                    style = LiftTheme.typography.no4,
                    color = LiftTheme.colorScheme.no9
                )
                Text(
                    "추가정보는 설정탭에서 수정 가능해요",
                    textAlign = TextAlign.Center,
                    style = LiftTheme.typography.no4,
                    color = LiftTheme.colorScheme.no9
                )
                Spacer(
                    modifier = Modifier.padding(10.dp)
                )

                LiftButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {  },
                ) {
                    Text(
                        text = "다음",
                        style = LiftTheme.typography.no3,
                        color = LiftTheme.colorScheme.no5,
                    )
                }
            }

        }
    }
}