package com.gradation.lift.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gradation.lift.designsystem.component.LiftButton
import com.gradation.lift.designsystem.resource.LiftIcon
import com.gradation.lift.designsystem.theme.LiftTheme


@Composable
fun StartWorkView(
    modifier: Modifier= Modifier,
    navigateMainGraphToWorkGraph: () -> Unit,
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(LiftTheme.colorScheme.no4)
    ) {
        LiftButton(
            onClick = navigateMainGraphToWorkGraph,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "운동시작하기",
                style = LiftTheme.typography.no3,
                color = LiftTheme.colorScheme.no5,
            )
            Spacer(modifier = modifier.padding(2.dp))
            Icon(
                painterResource(id = LiftIcon.ChevronRight),
                contentDescription = null,
                modifier = modifier
                    .fillMaxHeight()
                    .width(8.dp)
            )
        }
    }
}