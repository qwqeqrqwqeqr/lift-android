package com.gradation.lift.feature.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
    modifier: Modifier = Modifier,
    navigateMainGraphToWorkGraph: () -> Unit,
) {
    Surface(color = LiftTheme.colorScheme.no5) {
        Column(
            modifier.padding(16.dp)
        ) {
            LiftButton(
                onClick = navigateMainGraphToWorkGraph,
                modifier = modifier
                    .fillMaxWidth()
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
}