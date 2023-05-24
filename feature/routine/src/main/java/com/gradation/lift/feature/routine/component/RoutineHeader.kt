package com.gradation.lift.feature.routine.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun RoutineHeader(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(32.dp)
            )
            .fillMaxWidth()
            .padding(32.dp, 16.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.dumbbell),
                contentDescription = "덤벨",
                Modifier.size(64.dp),
                alignment = Alignment.Center,
            )
            Text(
                text = buildAnnotatedString {
                    append("나만의 ")
                    withStyle(
                        style = SpanStyle(color = MaterialTheme.colorScheme.primary),
                    ) {
                        append("루틴")
                    }
                    append("을 만들어봐요!")
                },
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
            )
            Text(
                text = "나만의 루틴을 만들어 \n" +
                        "간편하게 사용해보세요",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center,
            )
            LiftButton(
                modifier = modifier.fillMaxWidth(),
                onClick = onClick
            ) {
                Text(
                    text = "루틴 리스트 만들기",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                Icon(
                    imageVector = LiftIcon.ChevronRight,
                    contentDescription = null,
                )
            }
        }
    }
}


@Preview
@Composable
internal fun RoutineHeaderPreview() {
    LiftTheme {
        RoutineHeader(onClick = {},)
    }
}