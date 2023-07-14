package com.gradation.lift.feature.home.component

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
import com.gradation.lift.designsystem.theme.LiftMaterialTheme
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun CreateRoutineView(
    navigateCreateRoutineClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp)
            )
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.dumbbell),
                contentDescription = "덤벨",
                modifier.size(72.dp),
                alignment = Alignment.Center,
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(color = LiftTheme.colorScheme.no4),
                    ) {
                        append("루틴 리스트")
                    }
                    append(" 만들기")
                },
                style = LiftTheme.typography.no1,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = modifier.padding(4.dp))
            Text(
                text = "루틴리스트를 만들고 편하게 운동해요",
                style = LiftTheme.typography.no4,
                color = LiftTheme.colorScheme.no9,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = modifier.padding(4.dp))
            LiftButton(
                modifier = modifier.fillMaxWidth(),
                onClick =  navigateCreateRoutineClick
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
    LiftMaterialTheme {
        CreateRoutineView({})
    }
}