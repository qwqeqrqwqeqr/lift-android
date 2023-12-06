package com.gradation.lift.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gradation.lift.designsystem.R
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun BannerView(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF5BADFF),
                        Color(0xFF943AED)
                    ),
                ),
                RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier
            ) {
                Surface(
                    modifier = modifier
                        .background(
                            color = Color(0xFF0080FF),
                            shape = RoundedCornerShape(size = 4.dp)
                        )
                        .padding(4.dp),
                    color = LiftTheme.colorScheme.no4,
                ) {
                    Text(
                        modifier = modifier,
                        text = "LIFT Tip!",
                        color = LiftTheme.colorScheme.no5,
                        style = LiftTheme.typography.no7.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    )

                }
                Text(
                    text = "리프트가 처음이신가요?",
                    color = LiftTheme.colorScheme.no5,
                    style = LiftTheme.typography.no1
                )
                Text(
                    text = "쉽게 사용하도록 도와드릴게요",
                    color = LiftTheme.colorScheme.no5,
                    style = LiftTheme.typography.no6
                )
            }
            Image(
                modifier = modifier
                    .offset(
                        x = this@BoxWithConstraints.maxWidth / 20,
                        y = -this@BoxWithConstraints.maxHeight / 4
                    ),
                painter = painterResource(id = R.drawable.dumbbell_banner),
                contentDescription = "dumbbell_banner"
            )
        }
    }
}