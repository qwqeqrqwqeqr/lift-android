package com.gradation.lift.feature.work.complete.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.R
import com.gradation.lift.ui.extensions.noRippleClickable
import com.gradation.lift.designsystem.theme.LiftTheme

@Composable
fun EvaluationView(
    modifier:Modifier=Modifier,
    score:Int,
    updateScore: (Int)->Unit
){
    Column(modifier= modifier.fillMaxWidth().padding(horizontal =  16.dp)) {
        Text(
            text = "평가하기",
            style = LiftTheme.typography.no3,
            color = LiftTheme.colorScheme.no9,
            modifier = modifier.align(Alignment.Start)
        )
        Spacer(modifier = modifier.padding(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                8.dp,
                Alignment.CenterHorizontally
            ),
            modifier = modifier.fillMaxWidth(),
        ) {
            repeat(5) {
                Image(
                    painter = if (it < score) painterResource(R.drawable.star_on) else painterResource(
                        R.drawable.star_off
                    ),
                    contentDescription = "",
                    modifier = modifier
                        .size(36.dp)
                        .noRippleClickable
                        { updateScore(it + 1) }

                )
            }
        }
    }

}