package com.gradation.lift.feature.work.complete.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun HeaderView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.halo),
            contentDescription = "",
            modifier = modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(R.drawable.trophy),
            contentDescription = "",
            modifier = modifier
                .size(160.dp)
                .align(Alignment.BottomCenter)
        )
    }
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {
        Text(
            text = buildAnnotatedString {
                append("운동을 ")
                withStyle(
                    style = SpanStyle(
                        color = LiftTheme.colorScheme.no4,
                        fontWeight = FontWeight.Bold
                    ),
                ) {
                    append("완료")
                }
                append("하였습니다!")
            },
            style = LiftTheme.typography.no1.copy(
                fontWeight = FontWeight(400)
            ),
            color = LiftTheme.colorScheme.no9,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )

    }
}